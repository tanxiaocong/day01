package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;

public class BUserChangePasswordQo {
    @ApiModelProperty(value = "原密码", required = true)
    @NotBlank
    private String password;
    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
