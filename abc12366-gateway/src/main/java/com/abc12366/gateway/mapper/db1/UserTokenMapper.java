package com.abc12366.gateway.mapper.db1;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

import com.abc12366.gateway.model.bo.UCUserBO;

/**
 * 用户token mapper
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-04-04 2:32 PM
 * @since 1.0.0
 */
public interface UserTokenMapper {

    /**
     * 根据token查询是否存在记录
     *
     * @param token              User-Token
     * @param lastTokenResetTime 当前时间 - token有效期(7200s),格式：yyyy-MM-dd HH:mm:ss
     * @return 用户ID
     */
    String isAuthentication(@Param("token") String token, @Param("lastTokenResetTime") String lastTokenResetTime);

    /**
     * 根据用户token续期token
     *
     * @param token User-Token
     * @return 影响行数
     */
    int updateLastTokenResetTime(String token);

    /**
     * 根据用户token查询用户信息
     */
    UCUserBO selectOneByToken(String token);
}
