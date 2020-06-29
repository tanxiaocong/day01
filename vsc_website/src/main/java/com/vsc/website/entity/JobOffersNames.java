package com.vsc.website.entity;

public class JobOffersNames {
    private Integer id;

    private Integer jobOffersId;

    private String jobName;

    private String duty;

    private String demand;

    private Integer language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobOffersId() {
        return jobOffersId;
    }

    public void setJobOffersId(Integer jobOffersId) {
        this.jobOffersId = jobOffersId;
    }

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