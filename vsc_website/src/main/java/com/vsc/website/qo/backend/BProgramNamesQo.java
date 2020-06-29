package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class BProgramNamesQo {
    @ApiModelProperty("方案名称")
    @NotBlank
    @Pattern(regexp = "^[\\s\\S]{1,255}$")
    private String programName;

    @ApiModelProperty("方案描述")
    @Pattern(regexp = "^[\\s\\S]{0,1000}$")
    private String programDescription;

    @ApiModelProperty("方案功能")
    private String programFunction;

    @ApiModelProperty("方案特点")
    private String feature;

    @ApiModelProperty("相关案例")
    private String similarCase;

    @ApiModelProperty("语言 0 中 1 英 2 日")
    @NotNull@Min(0)@Max(2)
    private Integer language;

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
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

    public String getProgramFunction() {
        return programFunction;
    }

    public void setProgramFunction(String programFunction) {
        this.programFunction = programFunction;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getSimilarCase() {
        return similarCase;
    }

    public void setSimilarCase(String similarCase) {
        this.similarCase = similarCase;
    }
}
