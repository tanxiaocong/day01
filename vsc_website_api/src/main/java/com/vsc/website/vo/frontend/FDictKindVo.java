package com.vsc.website.vo.frontend;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class FDictKindVo {

    @ApiModelProperty("大分类id")
    private Integer id;

    @ApiModelProperty("大分类名")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("分类方案")
    private List<FProgramNamesVo> programNamesVos;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FProgramNamesVo> getProgramNamesVos() {
        return programNamesVos;
    }

    public void setProgramNamesVos(List<FProgramNamesVo> programNamesVos) {
        this.programNamesVos = programNamesVos;
    }
}
