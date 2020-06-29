package com.vsc.website.vo.frontend;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class FHomePageViewsVo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("新闻日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date tidingsDate;
    @ApiModelProperty("新闻标题")
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
