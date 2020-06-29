package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class BTidingsNamesQo {
    @ApiModelProperty("新闻标题")
    @NotBlank
    @Pattern(regexp = "^[\\s\\S]{1,255}$")
    private String title;

    @ApiModelProperty("新闻内容")
    @NotBlank
    private String content;

    @ApiModelProperty("语言版本012 中英日")
    @NotNull@Min(0)@Max(2)
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
