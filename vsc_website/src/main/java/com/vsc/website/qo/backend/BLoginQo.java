package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class BLoginQo {
    @ApiModelProperty(value = "用户名(注册手机号)", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password;
    @ApiModelProperty(value = "图片验证码", required = true)
    @NotBlank
    private String captcha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
