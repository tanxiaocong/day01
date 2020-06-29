package com.vsc.website.dao;

import com.vsc.website.entity.Programs;
import com.vsc.website.vo.backend.BProgramDetailVo;
import com.vsc.website.vo.backend.BProgramListVo;
import com.vsc.website.vo.frontend.FProgramNamesVo;
import com.vsc.website.vo.frontend.FProgramViewVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Programs record);

    int insertSelective(Programs record);

    Programs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Programs record);

    int updateByPrimaryKey(Programs record);

    List<BProgramListVo> list(@Param("keywords") String keywords,
                              @Param("language") Integer language);

    BProgramDetailVo detail(@Param("id") Integer id);

    Programs selectByHomeLocation(@Param("homeLocation") Integer homeLocation,
                                  @Param("language")Integer language);

    void updateByHomeLocation(@Param("homeLocation") Integer homeLocation,
                              @Param("language")Integer language);

    List<FProgramNamesVo> getProgramAll(Integer language);

    List<FProgramViewVo> getHomeView(Integer language);
}