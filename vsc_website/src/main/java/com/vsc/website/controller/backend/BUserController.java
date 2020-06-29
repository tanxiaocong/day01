package com.vsc.website.controller.backend;

import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.ResultObject;
import com.vsc.website.common.Util;
import com.vsc.website.qo.backend.BLoginQo;
import com.vsc.website.qo.backend.BUserChangePasswordQo;
import com.vsc.website.service.UserService;
import com.vsc.website.vo.backend.BLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@Api(tags = {"后台-用户管理"})
@Controller
@RequestMapping("/backend/users")
public class BUserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultObject<BLoginVo> login(
            HttpServletRequest request,
            @Valid @RequestBody BLoginQo qo,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, Util.getLoginToken(request).getLanguage(), errors.getAllErrors());
        }
        BLoginVo data = userService.txLogin(qo, request.getRemoteAddr(), request);
        return new ResultObject<>(MessageCode.CODE_SUCCESS,null,data);
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/api/changePassword.do", method = {RequestMethod.PUT})
    @ResponseBody
    public ResultObject changePassword(HttpServletRequest request
            , @Valid @RequestBody BUserChangePasswordQo qo
            , BindingResult errors) throws IllegalAccessException, InvocationTargetException {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, Util.getLoginToken(request).getLanguage(), errors.getAllErrors());
        }
        userService.txChangePassword(Util.getLoginToken(request), qo);
        return new ResultObject(MessageCode.CODE_SUCCESS, Util.getLoginToken(request).getLanguage());
    }
}
