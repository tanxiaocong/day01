package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BProgramAddQo {

    @ApiModelProperty("行业id")
    @NotNull
    private Integer industryId;

    @ApiModelProperty("方案icon地址")
    private String iconUrl;

    @ApiModelProperty("方案位置")
    @NotNull
    private Integer planLocation;

    @ApiModelProperty("状态0 未发布 1已发布")
    @NotNull@Min(0)@Max(1)
    private Integer state;

    @ApiModelProperty("首页位置")
    private Integer homeLocation;

    @ApiModelProperty("首页是否显示0 1 不显示 显示")
    @NotNull@Min(0)@Max(1)
    private Integer showFlag;

    @ApiModelProperty("分类id")
    @NotNull
    private Integer kindId;

    @ApiModelProperty("不同语言版本")
    @Valid
    private List<BProgramNamesQo> programNamesQos;

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
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

    public List<BProgramNamesQo> getProgramNamesQos() {
        return programNamesQos;
    }

    public void setProgramNamesQos(List<BProgramNamesQo> programNamesQos) {
        this.programNamesQos = programNamesQos;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
