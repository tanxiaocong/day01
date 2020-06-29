package com.vsc.website.dao;

import com.vsc.website.entity.Tidings;
import com.vsc.website.vo.backend.BTidingsDetailVo;
import com.vsc.website.vo.backend.BTidingsListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TidingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tidings record);

    int insertSelective(Tidings record);

    Tidings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tidings record);

    int updateByPrimaryKey(Tidings record);

    List<BTidingsListVo> list(@Param("keywords") String keywords, @Param("language") Integer language);

    BTidingsDetailVo detail(Integer id);
}