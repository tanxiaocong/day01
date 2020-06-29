package com.vsc.website.vo.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class BTidingsDetailVo {

    @ApiModelProperty("新闻id")
    private Integer id;
    @ApiModelProperty("显示时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date tidingsDate;
    @ApiModelProperty("操作人")
    private String updateUser;
    @ApiModelProperty("操作时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateAt;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("语言版本")
    private List<BTidingsNamesVo> tidingsNamesVos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTidingsDate() {
        return tidingsDate;
    }

    public void setTidingsDate(Date tidingsDate) {
        this.tidingsDate = tidingsDate;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<BTidingsNamesVo> getTidingsNamesVos() {
        return tidingsNamesVos;
    }

    public void setTidingsNamesVos(List<BTidingsNamesVo> tidingsNamesVos) {
        this.tidingsNamesVos = tidingsNamesVos;
    }
}
