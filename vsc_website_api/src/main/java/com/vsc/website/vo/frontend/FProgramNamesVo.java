package com.vsc.website.vo.frontend;

import io.swagger.annotations.ApiModelProperty;

public class FProgramNamesVo {
    @ApiModelProperty("方案id")
    private Integer id;

    @ApiModelProperty("显示位置")
    private Integer planLocation;

    @ApiModelProperty("分类id")
    private Integer KindId;

    @ApiModelProperty("方案名称")
    private String ProgramName;

    @ApiModelProperty("方案功能")
    private String programFunction;

    public String getProgramFunction() {
        return programFunction;
    }

    public void setProgramFunction(String programFunction) {
        this.programFunction = programFunction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanLocation() {
        return planLocation;
    }

    public void setPlanLocation(Integer planLocation) {
        this.planLocation = planLocation;
    }

    public Integer getKindId() {
        return KindId;
    }

    public void setKindId(Integer kindId) {
        KindId = kindId;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }
}
