package com.vsc.website.vo.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BTidingsListVo {
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
    @ApiModelProperty("状态01 未发布已发布")
    private Integer state;
    @ApiModelProperty("新闻标题")
    private String title;
    @ApiModelProperty("语言012 中英日")
    private Integer language;

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
