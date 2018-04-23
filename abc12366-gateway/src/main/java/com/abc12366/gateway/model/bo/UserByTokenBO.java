package com.abc12366.gateway.model.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-03
 * Time: 10:16
 */
public class UserByTokenBO {
    private String id;
    private String userId;
    private String appId;
    private String token;
    private Date lastResetTokenTime;

    public UserByTokenBO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastResetTokenTime() {
        return lastResetTokenTime;
    }

    public void setLastResetTokenTime(Date lastResetTokenTime) {
        this.lastResetTokenTime = lastResetTokenTime;
    }

    @Override
    public String toString() {
        return "UserByTokenBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", appId='" + appId + '\'' +
                ", token='" + token + '\'' +
                ", lastResetTokenTime=" + lastResetTokenTime +
                '}';
    }
}
