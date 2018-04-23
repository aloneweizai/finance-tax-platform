package com.abc12366.uc.model.admin.bo;

import com.abc12366.uc.model.admin.Admin;

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

    private Admin admin;

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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
