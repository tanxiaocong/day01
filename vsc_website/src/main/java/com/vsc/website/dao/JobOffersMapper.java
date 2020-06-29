package com.vsc.website.dao;

import com.vsc.website.entity.JobOffers;
import com.vsc.website.vo.backend.BJobOffersDetailVo;
import com.vsc.website.vo.backend.BJobOffersListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobOffersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobOffers record);

    int insertSelective(JobOffers record);

    JobOffers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobOffers record);

    int updateByPrimaryKey(JobOffers record);

    List<BJobOffersListVo> list(@Param("keywords") String keywords,
                                @Param("language")Integer language);

    BJobOffersDetailVo detail(@Param("id")Integer id,@Param("language")Integer language);
}