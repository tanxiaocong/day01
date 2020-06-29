package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class BJobOffersNamesQo {
    @ApiModelProperty("岗位名称")
    @NotBlank
    @Pattern(regexp = "^[\\s\\S]{1,255}$")
    private String jobName;

    @ApiModelProperty("职责")
    @NotBlank
//    @Pattern(regexp = "^[\\s\\S]{1,1000}$")
    private String duty;

    @ApiModelProperty("要求")
    @NotBlank
//    @Pattern(regexp = "^[\\s\\S]{1,1000}$")
    private String demand;

    @NotNull
    @Min(0)@Max(2)
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
