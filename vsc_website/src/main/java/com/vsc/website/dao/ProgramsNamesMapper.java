package com.vsc.website.dao;

import com.vsc.website.entity.ProgramsNames;
import com.vsc.website.vo.backend.BProgramNamesVo;

import java.util.List;

public interface ProgramsNamesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProgramsNames record);

    int insertSelective(ProgramsNames record);

    ProgramsNames selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProgramsNames record);

    int updateByPrimaryKey(ProgramsNames record);

    void delete(Integer programId);

    List<BProgramNamesVo> getByProgramId(Integer programId);
}