package com.vsc.website.service;

import com.vsc.website.common.ApiException;
import com.vsc.website.common.MessageCode;
import com.vsc.website.dao.DictMapper;
import com.vsc.website.entity.Dict;
import com.vsc.website.vo.common.CDictVo;
import com.vsc.website.vo.frontend.FDictKindVo;
import com.vsc.website.vo.frontend.FProgramNamesVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
     * @param language
     * @return
     */
    public List<CDictVo> getDict(String type, Integer language){
        List<CDictVo> dictVos=dictMapper.getDict(type,language);
        return dictVos;
    }


    /**
     * 构建首页行业-方案
     * @param language
     * @return
     */
    public List<FDictKindVo> kindVoList(Integer language){
        List<FDictKindVo> vos=dictMapper.kindVoList(language);
        List<FProgramNamesVo> programs=programService.getProgramAll(language);
        Map<Integer,List<FProgramNamesVo>> programsMap=programs.stream().collect(Collectors.groupingBy(FProgramNamesVo::getKindId));
        for (FDictKindVo vo:vos){
            vo.setProgramNamesVos(programsMap.get(vo.getId()));
        }
        return vos;
    }

    /**
     * 确认下拉
     * @param id
     * @param language
     */
    public void sureDict(Integer id,Integer language){
        Dict dict=dictMapper.selectByPrimaryKey(id);
        if (dict==null){
            throw new ApiException(MessageCode.CODE_NO_PULL_DOWN,language);
        }
    }
}
