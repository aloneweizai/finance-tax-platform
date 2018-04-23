package com.abc12366.uc.mapper.db1;


import com.abc12366.uc.model.Token;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by lgy on 2017-05-05.
 */
public interface TokenMapper {
    int insert(Token token);

    int update(Token token);

    int delete(String token);

    void updateLastTokenResetTime(String token);

    Token selectOne(@Param("userId") String userId, @Param("appId") String appId);

    Token isAuthentication(String userToken);
    
    List<Token> getActiveToken();
}
