package com.vsc.website.qo.frontend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SendResumeMailQo {
    @ApiModelProperty("招聘岗位Id")
    @NotNull
    private Integer jobOffersId;

    @ApiModelProperty("文件名")
    @NotBlank
    private String fileName;

    @ApiModelProperty("语言012 中英日 非必填")
    private Integer language;

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getJobOffersId() {
        return jobOffersId;
    }

    public void setJobOffersId(Integer jobOffersId) {
        this.jobOffersId = jobOffersId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
