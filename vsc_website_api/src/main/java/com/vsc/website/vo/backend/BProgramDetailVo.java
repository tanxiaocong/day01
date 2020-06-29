package com.vsc.website.vo.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class BProgramDetailVo {
    @ApiModelProperty("解决方案id")
    private Integer id;

    @ApiModelProperty("行业id")
    private Integer industryId;

    @ApiModelProperty("状态0 未发布 1已发布")
    private Integer state;

    @ApiModelProperty("行业名")
    private String industryName;

    @ApiModelProperty("方案icon地址")
    private String iconUrl;

    @ApiModelProperty("方案icon名称")
    private String iconName;

    @ApiModelProperty("方案位置")
    private Integer planLocation;

    @ApiModelProperty("首页位置")
    private Integer homeLocation;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateAt;

    @ApiModelProperty("首页是否显示0 1 不显示 显示")
    private Integer showFlag;

    @ApiModelProperty("大分类id")
    private Integer kindId;

    @ApiModelProperty("大分类名称")
    private String kindName;

    @ApiModelProperty("语言版本")
    private List<BProgramNamesVo> programNamesVos;

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

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<BProgramNamesVo> getProgramNamesVos() {
        return programNamesVos;
    }

    public void setProgramNamesVos(List<BProgramNamesVo> programNamesVos) {
        this.programNamesVos = programNamesVos;
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

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}
