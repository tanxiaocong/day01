package com.vsc.website.vo.frontend;

import io.swagger.annotations.ApiModelProperty;

public class FProgramNamesVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("行业id")
    private Integer industryId;

    @ApiModelProperty("方案名称")
    private String ProgramName;

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }
}
