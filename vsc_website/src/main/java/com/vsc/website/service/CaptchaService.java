package com.vsc.website.service;

import com.vsc.website.common.ApiException;
import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class CaptchaService {
    public static final String SK_CAPTCHA = "captcha";

    public void checkCaptcha(HttpServletRequest request, String captcha) {
        if (StringUtils.equalsIgnoreCase(captcha, (String)request.getSession().getAttribute(SK_CAPTCHA))) {
            request.getSession().removeAttribute(SK_CAPTCHA);
        } else {
            throw new ApiException(MessageCode.CODE_CAPTCHA_ERROR, Constant.CHINESE);
        }
    }
}
