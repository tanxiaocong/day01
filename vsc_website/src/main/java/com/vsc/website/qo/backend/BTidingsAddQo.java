package com.vsc.website.qo.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class BTidingsAddQo {

    @ApiModelProperty(value = "显示时间",example = "2020-3-17 00:00:00")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date tidingsDate;

    @ApiModelProperty("状态")
    @NotNull@Min(0)@Max(1)
    private Integer state;

    @ApiModelProperty("语言版本")
    @Valid
    private List<BTidingsNamesQo> tidingsNamesQos;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getTidingsDate() {
        return tidingsDate;
    }

    public void setTidingsDate(Date tidingsDate) {
        this.tidingsDate = tidingsDate;
    }

    public List<BTidingsNamesQo> getTidingsNamesQos() {
        return tidingsNamesQos;
    }

    public void setTidingsNamesQos(List<BTidingsNamesQo> tidingsNamesQos) {
        this.tidingsNamesQos = tidingsNamesQos;
    }
}
