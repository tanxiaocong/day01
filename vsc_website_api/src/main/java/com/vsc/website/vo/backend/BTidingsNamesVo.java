package com.vsc.website.vo.backend;

import io.swagger.annotations.ApiModelProperty;

public class BTidingsNamesVo {

    @ApiModelProperty("新闻标题")
    private String title;
    @ApiModelProperty("新闻内容")
    private String content;
    @ApiModelProperty("语言版本012")
    private Integer language;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }
}
