package com.abc12366.uc.model.admin;

import java.io.Serializable;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class LoginInfo implements Serializable {

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
}
