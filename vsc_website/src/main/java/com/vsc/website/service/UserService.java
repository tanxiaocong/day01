package com.vsc.website.service;

import com.vsc.website.common.ApiException;
import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.Util;
import com.vsc.website.dao.UserMapper;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.entity.User;
import com.vsc.website.qo.backend.BLoginQo;
import com.vsc.website.qo.backend.BUserChangePasswordQo;
import com.vsc.website.vo.backend.BLoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CaptchaService captchaService;
    @Resource
    private LoginTokensService loginTokensService;
//    @Autowired
//    private LogService logService;

    /**
     * 修改密码
     */
    public void txChangePassword(LoginTokens loginTokens, BUserChangePasswordQo qo) {
        // 获取用户信息
        User user = assertUser(loginTokens.getUserId(),loginTokens);
        // 验证用户输入的密码是否与原密码相同
        if (!StringUtils.equalsIgnoreCase(DigestUtils.md5Hex(qo.getPassword()), user.getPassword())) {
            throw new ApiException(MessageCode.CODE_PASSWORD_ERROR,loginTokens.getLanguage());
        }
        // 修改密码
        user.setPassword(DigestUtils.md5Hex(qo.getNewPassword()));
        user.setUpdateAt(new Date());
        userMapper.updateByPrimaryKey(user);
//        logService.addLog(Log.LogType.edit, loginToken.getUserId(), "修改密码：" + loginToken.getUserId());
    }

    /**
     * 登录
     */
    public BLoginVo txLogin(BLoginQo qo, String ip, HttpServletRequest request) {
        // 验证图片验证码
        captchaService.checkCaptcha(request, qo.getCaptcha());
        User user = getUser(qo.getName());
        if (user == null
                || !StringUtils.equalsIgnoreCase(user.getPassword(), DigestUtils.md5Hex(qo.getPassword()))) {
            //手机或密码错误
            throw new ApiException(MessageCode.CODE_LOGIN_ERROR, Constant.CHINESE);
        }
        LoginTokens loginTokens = loginTokensService.addLoginToken(Constant.TOKEN_TYPE_BACKEND, user.getId(), ip,Constant.CHINESE);
//        logService.addLog(Log.LogType.login, loginToken.getUserId(), "用户登录：" + loginToken.getUserId());

        return new BLoginVo(user.getId(), loginTokens.getToken(), null, user.getName());
    }

    /**
     * 确认用户信息
     */
    public User assertUser(Integer userId,LoginTokens loginTokens) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ApiException(MessageCode.CODE_NO_USER,loginTokens.getLanguage());
        }
        return user;
    }

    /**
     * 取得用户信息
     */
    public User getUser(String name) {
        User user = userMapper.selectByName(name);
        if (user == null) {
            throw new ApiException(MessageCode.CODE_NO_USER,null);
        }
        return user;
    }

}
