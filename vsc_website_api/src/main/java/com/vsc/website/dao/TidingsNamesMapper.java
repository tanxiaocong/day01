package com.vsc.website.dao;

import com.vsc.website.entity.TidingsNames;
import com.vsc.website.vo.backend.BTidingsNamesVo;

import java.util.List;

public interface TidingsNamesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TidingsNames record);

    int insertSelective(TidingsNames record);

    TidingsNames selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TidingsNames record);

    int updateByPrimaryKey(TidingsNames record);

    void deleteByTidingsId(Integer tidingsId);

    List<BTidingsNamesVo> getTidingsNames(Integer tidingsId);
}