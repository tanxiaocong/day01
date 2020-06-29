package com.vsc.website.common;

public class Constant {

    /**登录过期时间(30分钟)：测试为10倍**/
    public static long loginTokenLimit = 10 * 30 * 60 * 1000;

    /**一天毫秒数**/
    public static final long ONE_DAY = 24 * 60 * 60 * 1000;

    /**一小时毫秒数**/
    public static final long ONE_HOUR = 60 * 60 * 1000;

    /**一分钟秒数**/
    public static final long ONE_MINUTE = 60 * 1000;

    public static final int CITY_PARTNER_DEADLINE = 30;

    /**标志-FALSE**/
    public static final int FALSE = 0;

    /**标志-TRUE**/
    public static final int TRUE = 1;

    /**语言-英文**/
    public static final Integer ENGLISH=1;

    /**语言-中文**/
    public static final Integer CHINESE=0;

    /**语言-繁体中文**/
    public static final Integer TRADITIONAL_CHINESE=2;

    /** 后台用户 */
    public static final String TOKEN_TYPE_BACKEND = "backend";

    /** app用户 */
    public static final String TOKEN_TYPE_APP = "app";

}
