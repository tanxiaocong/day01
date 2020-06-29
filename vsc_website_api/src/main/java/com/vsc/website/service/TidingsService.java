package com.vsc.website.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.website.common.ApiException;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.dao.TidingsMapper;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.entity.Tidings;
import com.vsc.website.qo.backend.BTidingsAddQo;
import com.vsc.website.vo.backend.BProgramListVo;
import com.vsc.website.vo.backend.BTidingsDetailVo;
import com.vsc.website.vo.backend.BTidingsListVo;
import com.vsc.website.vo.frontend.FHomePageViewsVo;
import com.vsc.website.vo.frontend.FViewsDetailVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sun.swing.plaf.synth.DefaultSynthStyle;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class TidingsService {

    @Resource
    private TidingsMapper tidingsMapper;

    @Resource
    private TidingsNamesService tidingsNamesService;

    /**
     * 添加新闻
     * @param qo
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BTidingsAddQo qo, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Tidings tidings=new Tidings();
        PropertyUtils.copyProperties(tidings,qo);
        tidings.setCreateAt(new Date());
        tidings.setUpdateAt(new Date());
        tidings.setUpdateUser(loginTokens.getUserId());
        tidings.setCreateUser(loginTokens.getUserId());
        tidingsMapper.insert(tidings);
        tidingsNamesService.add(qo,tidings.getId(),loginTokens);
    }

    /**
     * 更新新闻
     * @param qo
     * @param id
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void update(BTidingsAddQo qo,Integer id, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Tidings tidings = sureTidings(id, loginTokens.getLanguage());
        PropertyUtils.copyProperties(tidings,qo);
        tidings.setUpdateAt(new Date());
        tidings.setUpdateUser(loginTokens.getUserId());
        tidingsMapper.updateByPrimaryKeySelective(tidings);
        tidingsNamesService.add(qo,tidings.getId(),loginTokens);
    }

    /**
     * 新闻列表
     * @param keywords
     * @param language
     * @param page
     * @param size
     * @return
     */
    public PageBean<BTidingsListVo> list(String keywords,Integer language,int page,int size){
        PageHelper.startPage(page,size);
        List<BTidingsListVo> vos=tidingsMapper.list(keywords,language);
        PageInfo<BProgramListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    /**
     * 前台新闻列表
     * @param year
     * @param language
     * @return
     */
    public List<FHomePageViewsVo> frontendList(Integer year,Integer language){
        List<FHomePageViewsVo> vos=tidingsMapper.frontendList(year,language);
        return vos;
    }
    /**
     * 首页新闻
     * @param language
     * @return
     */
    public List<FHomePageViewsVo> getHomePageViews(Integer language){
        List<FHomePageViewsVo> vos=tidingsMapper.getHomePageViews(language);
        return vos;
    }

    /**
     * 前台新闻时间(new)
     * @return
     */
    public List<String> getViewsTimeNew(){
        List<String> vos=tidingsMapper.getViewsTimeNew();
        return  vos;
    }

    /**
     * 前台新闻时间(old)
     * @return
     */
    public List<String> getViewsTimeOld(){
        List<String> vos=tidingsMapper.getViewsTimeOld();
        return  vos;
    }
    /**
     * 删除新闻
     * @param id
     * @param loginTokens
     */
    public void  delete(Integer id, LoginTokens loginTokens){
        sureTidings(id, loginTokens.getLanguage());
        tidingsMapper.deleteByPrimaryKey(id);
        tidingsNamesService.deleteByTidingsId(id);
    }

    /**
     * 新闻详情
     * @param tidingsId
     * @param loginTokens
     * @return
     */
    public BTidingsDetailVo detail(Integer tidingsId,LoginTokens loginTokens){
        sureTidings(tidingsId,loginTokens.getLanguage());
        BTidingsDetailVo vo=tidingsMapper.detail(tidingsId);
        vo.setTidingsNamesVos(tidingsNamesService.getTidingsNames(tidingsId));
        return vo;
    }

    /**
     * 前台新闻详情
     * @param tidingsId
     * @param language
     * @return
     */
    public FViewsDetailVo frontendDetail(Integer tidingsId, Integer language){
        sureTidings(tidingsId,language);
        FViewsDetailVo vo=tidingsMapper.frontendDetail(tidingsId);
        vo.setTidingsNamesVos(tidingsNamesService.getTidingsNames(tidingsId));
        return vo;
    }
    /**
     * 确认新闻
     * @param id
     * @param language
     * @return
     */
    public Tidings  sureTidings(Integer id,Integer language){
        Tidings tidings=tidingsMapper.selectByPrimaryKey(id);
        if (tidings==null){
            throw new ApiException(MessageCode.CODE_NO_TIDINGS,language);
        }
        return tidings;
    }
}
