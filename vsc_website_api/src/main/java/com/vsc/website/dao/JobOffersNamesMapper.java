package com.vsc.website.dao;

import com.vsc.website.entity.JobOffersNames;
import com.vsc.website.vo.backend.BJobOffersNamesVo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface JobOffersNamesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobOffersNames record);

    int insertSelective(JobOffersNames record);

    JobOffersNames selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobOffersNames record);

    int updateByPrimaryKey(JobOffersNames record);

    void deleteByJobId(Integer jobOffersId);

    List<BJobOffersNamesVo> detail(Integer jobOffersId);
}