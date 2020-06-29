package com.vsc.website.common;

public enum MessageCode {

    /**成功**/
    CODE_SUCCESS(0),

    /**参数错误**/
    CODE_PARAMETER_ERROR(9000),

    /**文件过大**/
    CODE_FILE_TOO_LARGE(9001),

    /**请先登录**/
    CODE_NEED_LOGIN(9002),

    /**图片验证码错误**/
    CODE_CAPTCHA_ERROR(9003),

    /**原密码错误**/
    CODE_PASSWORD_ERROR(9004),

    /**用户不存在**/
    CODE_NO_USER(9005),

    /**用户名或密码错误**/
    CODE_LOGIN_ERROR(9006),

    /**没有权限**/
    CODE_NO_PRIVILEGE(9008),

    /**解决方案不存在**/
    CODE_NO_PROGRAM(9007),

    /**新闻不存在**/
    CODE_NO_TIDINGS(9008),

    /**招聘岗位不存在**/
    CODE_NO_JOB_OFFERS(9009),

    /**下拉选项不存在**/
    CODE_NO_PULL_DOWN(9010),

    /**系统异常**/
    CODE_EXCEPTION(9999);


    private int code;

    MessageCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
