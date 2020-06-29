package com.vsc.website.service;

import com.vsc.website.common.Constant;
import com.vsc.website.dao.LoginTokensMapper;
import com.vsc.website.entity.LoginTokens;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class LoginTokensService {

    @Resource
    private LoginTokensMapper loginTokensMapper;

    /**
     * 删除token
     * @param loginTokens
     */
    public void deleteToken(LoginTokens loginTokens) {
        loginTokensMapper.deleteByPrimaryKey(loginTokens.getId());
    }

    /**
     * 刷新token信息
     */
    public LoginTokens refreshLoginToken(String token) {
        LoginTokens loginTokens = loginTokensMapper.selectByToken(token,System.currentTimeMillis());
        if (loginTokens != null) {
            loginTokens.setTimeLimit(System.currentTimeMillis() + Constant.loginTokenLimit);
            loginTokens.setUpdateAt(new Date());
            loginTokensMapper.updateByPrimaryKey(loginTokens);
        }
        return loginTokens;
    }

    /**
     * 添加token
     *
     * @param type    用户类型
     * @param userId  用户id
     * @param ip      客户端ip
     */
    public LoginTokens addLoginToken(String type, int userId, String ip, String token, Integer language) {

        LoginTokens loginTokens = loginTokensMapper.selectToken(token);
        if(loginTokens == null){
            loginTokens = new LoginTokens();
            loginTokens.setType(type);
            loginTokens.setUserId(userId);
            loginTokens.setIp(ip);
            loginTokens.setTimeLimit(System.currentTimeMillis() + Constant.loginTokenLimit);
            loginTokens.setToken(token);
            loginTokens.setCreateAt(new Date());
            loginTokens.setLanguage(language);
            loginTokensMapper.insert(loginTokens);
        }else {
            loginTokens.setType(type);
            loginTokens.setUserId(userId);
            loginTokens.setIp(ip);
            loginTokens.setTimeLimit(System.currentTimeMillis() + Constant.loginTokenLimit);
            loginTokens.setToken(token);
            loginTokens.setLanguage(language);
            loginTokensMapper.updateByPrimaryKey(loginTokens);
        }

        return loginTokens;
    }

    /**
     * 添加token
     * @param type
     * @param userId
     * @param ip
     * @param language
     * @return
     */
    public LoginTokens addLoginToken(String type, int userId, String ip, Integer language){
        LoginTokens loginTokens = new LoginTokens();
        loginTokens.setType(type);
        loginTokens.setUserId(userId);
        loginTokens.setIp(ip);
        loginTokens.setTimeLimit(System.currentTimeMillis() + Constant.loginTokenLimit);

        loginTokens.setToken(UUID.randomUUID().toString());
        loginTokens.setCreateAt(new Date());
        loginTokens.setLanguage(language);
        loginTokensMapper.insert(loginTokens);
        return loginTokens;
    }

    /**
     * 切换语种
     * @param loginTokens
     * @param language
     */
    public void changeLanguage(LoginTokens loginTokens, Integer language){
        loginTokens.setLanguage(language);
        loginTokens.setTimeLimit(System.currentTimeMillis() + Constant.loginTokenLimit);
        loginTokensMapper.updateByPrimaryKey(loginTokens);
    }

    /**
     * 清理过期token
     */
    public void clearOverdueTokens() {
        loginTokensMapper.clearOverdueTokens(System.currentTimeMillis());
    }

    /**
     * 后台用户无效，清理有效token
     * @param userId
     */
    public void deleteByUserId(Integer userId){
        loginTokensMapper.updateByUserId(userId);
    }
}
