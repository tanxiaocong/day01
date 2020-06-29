package com.vsc.website.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.website.common.ApiException;
import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.config.Config;
import com.vsc.website.dao.ProgramsMapper;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.entity.Programs;
import com.vsc.website.qo.backend.BProgramAddQo;
import com.vsc.website.vo.backend.BProgramDetailVo;
import com.vsc.website.vo.backend.BProgramListVo;
import com.vsc.website.vo.frontend.FProgramNamesVo;
import com.vsc.website.vo.frontend.FProgramViewVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ProgramService {

    @Resource
    private ProgramsMapper programsMapper;
    @Resource
    private ProgramsNamesService programsNamesService;
    @Resource
    private AttachmentService attachmentService;
    @Resource
    private DictService dictService;

    /**
     * 添加解决方案
     *
     * @param qo
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BProgramAddQo qo, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        if (qo.getProgramNamesQos().size()!= Constant.ONE){
            throw new ApiException(MessageCode.CODE_PARAMETER_ERROR,loginTokens.getLanguage());
        }
        dictService.sureDict(qo.getIndustryId(),loginTokens.getLanguage());
        Programs programs = new Programs();
        PropertyUtils.copyProperties(programs, qo);
        String s = attachmentService.moveTmpFile(qo.getIconUrl(), Config.attachmentFolder);
        programs.setIconUrl(s);
        //校验是否显示在首页
        if(qo.getShowFlag().equals(Constant.SHOW_FLAG_OFF)){
            programs.setHomeLocation(null);
        }
        //校验首页位置
        if (qo.getHomeLocation() != null) {
            if (checkHomeLocation(qo.getHomeLocation(),qo.getProgramNamesQos().get(0).getLanguage(),null)) {
                programsMapper.updateByHomeLocation(qo.getHomeLocation(),qo.getProgramNamesQos().get(0).getLanguage());
            }
        }
        programs.setCreateAt(new Date());
        programs.setCreateUser(loginTokens.getUserId());
        programs.setUpdateAt(new Date());
        programs.setUpdateUser(loginTokens.getUserId());
        programsMapper.insert(programs);
        programsNamesService.add(qo, programs.getId(), loginTokens);
    }

    /**
     * 更新解决方案
     *
     * @param qo
     * @param id
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void update(BProgramAddQo qo, Integer id, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        if (qo.getProgramNamesQos().size()!= Constant.ONE){
            throw new ApiException(MessageCode.CODE_PARAMETER_ERROR,loginTokens.getLanguage());
        }
        Programs programs = sure(id, loginTokens.getLanguage());
        dictService.sureDict(qo.getIndustryId(),loginTokens.getLanguage());
        PropertyUtils.copyProperties(programs, qo);
        programs.setIconUrl(attachmentService.moveTmpFile(qo.getIconUrl(), Config.attachmentFolder));
        //校验是否显示在首页
        if(qo.getShowFlag().equals(Constant.SHOW_FLAG_OFF)){
            programs.setHomeLocation(null);
        }
        //校验首页位置
        if (qo.getHomeLocation() != null) {
            if (checkHomeLocation(qo.getHomeLocation(),qo.getProgramNamesQos().get(0).getLanguage(),id)) {
                programsMapper.updateByHomeLocation(qo.getHomeLocation(),qo.getProgramNamesQos().get(0).getLanguage());
            }
        }
        programs.setUpdateAt(new Date());
        programs.setUpdateUser(loginTokens.getUserId());
        programsMapper.updateByPrimaryKey(programs);
        programsNamesService.add(qo, id, loginTokens);
    }

    /**
     * 解决方案列表
     *
     * @param keywords
     * @param page
     * @param size
     * @param language
     * @return
     */
    public PageBean<BProgramListVo> list(String keywords, int page, int size, Integer language) {
        PageHelper.startPage(page, size);
        List<BProgramListVo> vos = programsMapper.list(keywords,language);
        PageInfo<BProgramListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    /**
     * 解决方案详情
     *
     * @param id
     * @param language
     * @return
     */
    public BProgramDetailVo detail(Integer id,Integer language) {
        sure(id,language);
        BProgramDetailVo vo = programsMapper.detail(id);
        vo.setProgramNamesVos(programsNamesService.getByProgramId(id));
        return vo;
    }

    /**
     * 删除解决方案
     *
     * @param id
     * @param loginTokens
     */
    public void delete(Integer id, LoginTokens loginTokens) {
        sure(id, loginTokens.getLanguage());
        programsMapper.deleteByPrimaryKey(id);
        programsNamesService.delete(id);
    }

    /**
     * 确认解决方案
     *
     * @param id
     * @param language
     */
    public Programs sure(Integer id,Integer language) {
        Programs programs = programsMapper.selectByPrimaryKey(id);
        if (programs == null) {
            throw new ApiException(MessageCode.CODE_NO_PROGRAM, language);
        }
        return programs;
    }

    /**
     * 检查首页位置是否被占用
     *
     * @param homeLocation
     * @param programId
     * @return true 此首页位置有数据 false 此首页位置无数据
     */
    public boolean checkHomeLocation(Integer homeLocation,Integer language,Integer programId) {
        Programs programs = programsMapper.selectByHomeLocation(homeLocation,language);
        if (programs != null && (programId == null || !programs.getId().equals(programId))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得所有方案的id和名字
     * @param language
     * @return
     */
    public List<FProgramNamesVo> getProgramAll(Integer language){
        List<FProgramNamesVo> vos=programsMapper.getProgramAll(language);
        return vos;
    }

    /**
     * 首页展示方案
     * @param language
     * @return
     */
    public List<FProgramViewVo> getHomeView(Integer language){
        List<FProgramViewVo> vos=programsMapper.getHomeView(language);
        return vos;
    }
}
