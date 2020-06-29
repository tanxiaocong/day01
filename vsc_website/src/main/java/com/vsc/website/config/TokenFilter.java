package com.vsc.website.config;

import com.vsc.website.common.ApiException;
import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.service.LoginTokensService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenFilter implements HandlerInterceptor {

    @Resource
    private LoginTokensService loginTokenService;
//    @Resource
//    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.equalsIgnoreCase("OPTIONS", request.getMethod())) {
            return true;
        }
        LoginTokens loginToken = loginTokenService.refreshLoginToken(token);
        if (loginToken == null /*|| !StringUtils.equals(request.getRemoteAddr(), loginToken.getIp())*/) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ApiException(MessageCode.CODE_NEED_LOGIN, null);
        }
        String uri = StringUtils.equalsIgnoreCase("GET", request.getMethod())
                ? request.getServletPath() + request.getQueryString() : request.getServletPath();
        if (!checkPrivilege(uri, loginToken)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new ApiException(MessageCode.CODE_NO_PRIVILEGE, loginToken.getLanguage());
        }
        request.setAttribute("loginToken", loginToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 验证用户权限
     */
    private boolean checkPrivilege(String uri, LoginTokens loginTokens) {
        //非common接口不需要判断权限
        if (uri.startsWith("/common")) {
            return true;
        } else if (uri.startsWith("/app") && !StringUtils.equals(Constant.TOKEN_TYPE_APP, loginTokens.getType())) {
            return false;
        } else if (uri.startsWith("/backend") && !StringUtils.equals(Constant.TOKEN_TYPE_BACKEND, loginTokens.getType())) {
            return false;
        }

//        List<Privileges> unauthorizedPrivileges = usersService.getUnauthorizedPrivileges(loginTokens.getUserId(), loginTokens.getLanguage());
//
//        if (unauthorizedPrivileges == null || unauthorizedPrivileges.isEmpty()) {
//            return true;
//        }
//
//        for (Privileges privileges : unauthorizedPrivileges) {
//            String pattern = "^" + privileges.getUrlPattern().replaceAll("\\*", ".*");
//            if (Pattern.matches(pattern, uri)) {
//                return false;
//            }
//        }
        return true;
    }
}
