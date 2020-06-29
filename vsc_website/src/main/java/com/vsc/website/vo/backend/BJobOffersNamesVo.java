package com.vsc.website.vo.backend;

import io.swagger.annotations.ApiModelProperty;

public class BJobOffersNamesVo {
    @ApiModelProperty("岗位名称")
    private String jobName;

    @ApiModelProperty("职责")
    private String duty;

    @ApiModelProperty("岗位要求")
    private String demand;

    @ApiModelProperty("语言012 中英日")
    private Integer language;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }
}
