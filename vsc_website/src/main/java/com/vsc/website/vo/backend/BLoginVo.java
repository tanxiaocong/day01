package com.vsc.website.vo.backend;

import io.swagger.annotations.ApiModelProperty;

public class BLoginVo {
    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;
    @ApiModelProperty(value = "token", required = true)
    private String token;
    @ApiModelProperty(value = "权限列表", required = true)
    private String privileges;
    @ApiModelProperty(value = "用户名称")
    private String name;

    public BLoginVo(Integer userId, String token, String privileges, String name) {
        this.userId = userId;
        this.token = token;
        this.privileges = privileges;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
