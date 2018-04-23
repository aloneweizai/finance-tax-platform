package com.abc12366.uc.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author liuguiyao <435720953@qq.com>
 * @create 2017-04-05 12:59 PM
 * @since 1.0.0
 */
public class Token {
    private String id;
    @NotEmpty(message = "appId不能为空")
    private String appId;
    @NotEmpty(message = "userId不能为空")
    private String userId;
    private String token;
    private Date lastTokenResetTime;

    public Token() {
    }

    public Token(String id, String appId, String userId, String token, Date lastTokenResetTime) {
        this.id = id;
        this.appId = appId;
        this.userId = userId;
        this.token = token;
        this.lastTokenResetTime = lastTokenResetTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastTokenResetTime() {
        return lastTokenResetTime;
    }

    public void setLastTokenResetTime(Date lastTokenResetTime) {
        this.lastTokenResetTime = lastTokenResetTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + '\'' +
                ", appId='" + appId + '\'' +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", lastTokenResetTime=" + lastTokenResetTime +
                '}';
    }
}
