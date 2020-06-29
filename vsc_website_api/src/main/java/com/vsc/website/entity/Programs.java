package com.vsc.website.entity;

import java.util.Date;

public class Programs {
    private Integer id;

    private Integer industryId;

    private String iconUrl;

    private Integer planLocation;

    private Integer homeLocation;

    private Integer updateUser;

    private Integer state;

    private Date updateAt;

    private Integer createUser;

    private Date createAt;

    private Integer showFlag;

    private Integer kindId;

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getPlanLocation() {
        return planLocation;
    }

    public void setPlanLocation(Integer planLocation) {
        this.planLocation = planLocation;
    }

    public Integer getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Integer homeLocation) {
        this.homeLocation = homeLocation;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }
}