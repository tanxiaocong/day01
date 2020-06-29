package com.vsc.website.vo.frontend;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class FDictIndustryVo {

    @ApiModelProperty("行业id")
    private Integer id;

    @ApiModelProperty("行业名")
    private String name;

    @ApiModelProperty("行业方案")
    private List<FProgramNamesVo> programNamesVos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FProgramNamesVo> getProgramNamesVos() {
        return programNamesVos;
    }

    public void setProgramNamesVos(List<FProgramNamesVo> programNamesVos) {
        this.programNamesVos = programNamesVos;
    }
}
