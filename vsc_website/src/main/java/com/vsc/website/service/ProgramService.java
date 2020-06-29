package com.vsc.website.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.website.common.ApiException;
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
        Programs programs = new Programs();
        PropertyUtils.copyProperties(programs, qo);
        String s = attachmentService.moveTmpFile(qo.getIconUrl(), Config.attachmentFolder);
        programs.setIconUrl(s);
        if (qo.getHomeLocation() != null) {
            if (checkHomeLocation(qo.getHomeLocation(), null)) {
                programsMapper.updateShowLocation(qo.getHomeLocation());
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
        Programs programs = sure(id, loginTokens,null);
        PropertyUtils.copyProperties(programs, qo);
        programs.setIconUrl(attachmentService.moveTmpFile(qo.getIconUrl(), Config.attachmentFolder));
        if (qo.getHomeLocation() != null) {
            if (checkHomeLocation(qo.getHomeLocation(), id)) {
                programsMapper.updateShowLocation(qo.getHomeLocation());
            }
        }
        programs.setUpdateAt(new Date());
        programs.setUpdateUser(loginTokens.getUserId());
        programsMapper.updateByPrimaryKeySelective(programs);
        programsNamesService.add(qo, id, loginTokens);
    }

    /**
     * 解决方案列表
     *
     * @param keywords
     * @param industryId
     * @param page
     * @param size
     * @param loginTokens
     * @return
     */
    public PageBean<BProgramListVo> list(String keywords, Integer industryId, int page, int size, LoginTokens loginTokens) {
        PageHelper.startPage(page, size);
        List<BProgramListVo> vos = programsMapper.list(keywords, industryId, loginTokens.getLanguage());
        PageInfo<BProgramListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    /**
     * 解决方案详情
     *
     * @param id
     * @param loginTokens
     * @return
     */
    public BProgramDetailVo detail(Integer id, LoginTokens loginTokens,Integer language) {
        sure(id, loginTokens,language);
        BProgramDetailVo vo = programsMapper.detail(id, loginTokens==null?language:loginTokens.getLanguage());
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
        sure(id, loginTokens,null);
        programsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 确认解决方案
     *
     * @param id
     * @param loginTokens
     */
    public Programs sure(Integer id, LoginTokens loginTokens,Integer language) {
        Programs programs = programsMapper.selectByPrimaryKey(id);
        if (programs == null) {
            throw new ApiException(MessageCode.CODE_NO_PROGRAM, loginTokens==null?language:loginTokens.getLanguage());
        }
        return programs;
    }

    /**
     * 检查首页位置是否被占用
     *
     * @param homeLocation
     * @param programId
     * @return
     */
    public boolean checkHomeLocation(Integer homeLocation, Integer programId) {
        Programs programs = programsMapper.selectByHomeLocation(homeLocation);
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
