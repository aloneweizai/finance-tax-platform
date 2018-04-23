package com.abc12366.gateway.model.bo;

import com.abc12366.gateway.model.User;

import java.io.Serializable;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class LoginInfoBO implements Serializable {

    /****/
    private String id;

    /**
     * 用户ID
     **/

    private String userId;

    /**
     * 应用ID
     **/
    private String appId;

    /**
     * 登录token
     **/
    private String token;

    /**
     * 最后重置token时间
     **/
    private java.util.Date lastResetTokenTime;

    private User admin;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public java.util.Date getLastResetTokenTime() {
        return this.lastResetTokenTime;
    }

    public void setLastResetTokenTime(java.util.Date lastResetTokenTime) {
        this.lastResetTokenTime = lastResetTokenTime;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "LoginInfoBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", appId='" + appId + '\'' +
                ", token='" + token + '\'' +
                ", lastResetTokenTime=" + lastResetTokenTime +
                ", admin=" + admin +
                '}';
    }
}
