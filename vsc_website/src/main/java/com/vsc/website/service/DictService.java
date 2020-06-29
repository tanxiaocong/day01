package com.vsc.website.service;

import com.vsc.website.dao.DictMapper;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.vo.common.CDictVo;
import com.vsc.website.vo.frontend.FDictIndustryVo;
import com.vsc.website.vo.frontend.FProgramNamesVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DictService {

    @Resource
    private DictMapper dictMapper;

    @Resource
    private ProgramService programService;

    /**
     * 下拉列表
     * @param type
     * @param loginTokens
     * @return
     */
    public List<CDictVo> getDict(String type, LoginTokens loginTokens){
        List<CDictVo> dictVos=dictMapper.getDict(type,loginTokens.getLanguage());
        return dictVos;
    }


    /**
     * 构建首页行业-方案
     * @param language
     * @return
     */
    public List<FDictIndustryVo> industryVoList(Integer language){
        List<FDictIndustryVo> vos=dictMapper.industryVoList(language);
        List<FProgramNamesVo> programs=programService.getProgramAll(language);
        Map<Integer,List<FProgramNamesVo>> programsMap=programs.stream().collect(Collectors.groupingBy(FProgramNamesVo::getIndustryId));
        for (FDictIndustryVo industryVo:vos){
            industryVo.setProgramNamesVos(programsMap.get(industryVo.getId()));
        }
        return vos;
    }
}
