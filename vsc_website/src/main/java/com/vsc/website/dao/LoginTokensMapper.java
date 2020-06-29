package com.vsc.website.dao;

import com.vsc.website.entity.LoginTokens;
import org.apache.ibatis.annotations.Param;

public interface LoginTokensMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginTokens record);

    int insertSelective(LoginTokens record);

    LoginTokens selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginTokens record);

    int updateByPrimaryKey(LoginTokens record);

    void clearOverdueTokens(@Param("currentTime") Long currentTime);

    LoginTokens selectByToken(@Param("token") String token, @Param("timeLimit") Long timeLimit);


    LoginTokens selectToken(@Param("token") String token);

    void updateByUserId(@Param("userId") Integer userId);
}