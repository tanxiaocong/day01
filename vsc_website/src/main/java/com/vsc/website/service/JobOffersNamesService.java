package com.vsc.website.service;

import com.vsc.website.dao.JobOffersNamesMapper;
import com.vsc.website.entity.JobOffersNames;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.qo.backend.BJobOffersAddQo;
import com.vsc.website.qo.backend.BJobOffersNamesQo;
import com.vsc.website.vo.backend.BJobOffersNamesVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class JobOffersNamesService {

    @Resource
    private JobOffersNamesMapper jobOffersNamesMapper;


    /**
     * 添加
     * @param qo
     * @param jobOffersId
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BJobOffersAddQo qo, Integer jobOffersId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        delete(jobOffersId);
        for(BJobOffersNamesQo namesVo:qo.getJobOffersNamesQos()){
            JobOffersNames jobOffersNamesQo=new JobOffersNames();
            PropertyUtils.copyProperties(jobOffersNamesQo,namesVo);
            jobOffersNamesQo.setJobOffersId(jobOffersId);
            jobOffersNamesMapper.insert(jobOffersNamesQo);
        }
    }

    /**
     * 岗位名详情
     * @param jobOffersId
     * @return
     */
    public List<BJobOffersNamesVo>  detail(Integer jobOffersId){
        List<BJobOffersNamesVo> vos=jobOffersNamesMapper.detail(jobOffersId);
        return vos;
    }

    /**
     * 删除
     * @param jobOffersId
     */
    public void delete(Integer jobOffersId){
        jobOffersNamesMapper.deleteByJobId(jobOffersId);
    }
}
