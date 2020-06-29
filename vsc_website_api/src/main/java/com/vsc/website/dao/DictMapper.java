package com.vsc.website.dao;

import com.vsc.website.entity.Dict;
import com.vsc.website.vo.common.CDictVo;
import com.vsc.website.vo.frontend.FDictKindVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    List<CDictVo> getDict(@Param("type") String type,
                          @Param("language") Integer language);

    List<FDictKindVo> kindVoList(Integer language);
}