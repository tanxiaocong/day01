package com.vsc.website.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.website.common.ApiException;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.dao.JobOffersMapper;
import com.vsc.website.entity.JobOffers;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.qo.backend.BJobOffersAddQo;
import com.vsc.website.vo.backend.BJobOffersDetailVo;
import com.vsc.website.vo.backend.BJobOffersListVo;
import com.vsc.website.vo.backend.BProgramListVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class JobOffersService {

    @Resource
    private JobOffersMapper jobOffersMapper;

    @Resource
    private JobOffersNamesService jobOffersNamesService;

    /**
     * 添加
     * @param qo
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BJobOffersAddQo qo, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JobOffers jobOffers=new JobOffers();
        PropertyUtils.copyProperties(jobOffers,qo);
        jobOffers.setUpdateAt(new Date());
        jobOffers.setUpdateUser(loginTokens.getUserId());
        jobOffers.setCreateAt(new Date());
        jobOffers.setCreateUser(loginTokens.getUserId());
        jobOffersMapper.insert(jobOffers);
        jobOffersNamesService.add(qo,jobOffers.getId());
    }

    /**
     * 更新招聘岗位
     * @param qo
     * @param id
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void update(BJobOffersAddQo qo,Integer id,LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JobOffers jobOffers = sureJobOffers(id, loginTokens);
        PropertyUtils.copyProperties(jobOffers,qo);
        jobOffers.setUpdateUser(loginTokens.getUserId());
        jobOffers.setUpdateAt(new Date());
        jobOffersMapper.updateByPrimaryKeySelective(jobOffers);
        jobOffersNamesService.add(qo,id);
    }

    /**
     * 列表
     * @param keywords
     * @param loginTokens
     * @param page
     * @param size
     * @return
     */
    public PageBean<BJobOffersListVo> list(String keywords,LoginTokens loginTokens,int page,int size){
        PageHelper.startPage(page,size);
        List<BJobOffersListVo> vos=jobOffersMapper.list(keywords,loginTokens.getLanguage());
        PageInfo<BProgramListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    /**
     * 详情
     * @param id
     * @param loginTokens
     * @return
     */
    public BJobOffersDetailVo detail(Integer id,LoginTokens loginTokens){
        sureJobOffers(id,loginTokens);
        BJobOffersDetailVo vo=jobOffersMapper.detail(id,loginTokens.getLanguage());
        vo.setJobOffersNamesVos(jobOffersNamesService.detail(id));
        return vo;
    }

    /**
     * 删除
     * @param id
     * @param loginTokens
     */
    public void delete(Integer id,LoginTokens loginTokens){
        sureJobOffers(id,loginTokens);
        jobOffersMapper.deleteByPrimaryKey(id);
        jobOffersNamesService.delete(id);
    }

    /**
     * 确认岗位
     * @param jobOffersId
     * @param loginTokens
     * @return
     */
    public JobOffers sureJobOffers(Integer jobOffersId, LoginTokens loginTokens){
        JobOffers jobOffers=jobOffersMapper.selectByPrimaryKey(jobOffersId);
        if (jobOffers==null){
            throw  new ApiException(MessageCode.CODE_NO_JOB_OFFERS,loginTokens.getLanguage());
        }
        return jobOffers;
    }

}
