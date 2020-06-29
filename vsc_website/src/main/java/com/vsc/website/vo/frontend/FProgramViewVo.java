package com.vsc.website.vo.frontend;

import io.swagger.annotations.ApiModelProperty;

public class FProgramViewVo {
    @ApiModelProperty("方案id")
    private Integer id;

    @ApiModelProperty("方案行业id")
    private Integer industryId;

    @ApiModelProperty("方案行业名")
    private String industryName;

    @ApiModelProperty("方案icon")
    private String iconUrl;

    @ApiModelProperty("方案名称")
    private String programName;

    @ApiModelProperty("方案描述")
    private String programDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }
}
