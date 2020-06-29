package com.vsc.website.entity;

public class TidingsNames {
    private Integer id;

    private Integer tidingsId;

    private String title;

    private String content;

    private Integer language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTidingsId() {
        return tidingsId;
    }

    public void setTidingsId(Integer tidingsId) {
        this.tidingsId = tidingsId;
    }

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