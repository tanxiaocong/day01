package com.vsc.website.dao;

import com.vsc.website.entity.Tidings;
import com.vsc.website.vo.backend.BTidingsDetailVo;
import com.vsc.website.vo.backend.BTidingsListVo;
import com.vsc.website.vo.frontend.FHomePageViewsVo;
import com.vsc.website.vo.frontend.FViewsDetailVo;
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

    FViewsDetailVo frontendDetail(Integer id);

    List<FHomePageViewsVo> getHomePageViews(Integer language);

    List<String> getViewsTimeNew();

    List<String> getViewsTimeOld();

    List<FHomePageViewsVo> frontendList(@Param("year") Integer year,
                                        @Param("language")Integer language);
}