package com.abc12366.gateway.model;

import java.io.Serializable;


/**
 * 接入应用表
 **/
@SuppressWarnings("serial")
public class App implements Serializable {

    /**
     * appId
     **/
    private String id;

    /**
     * 授权应用名称
     **/
    private String name;

    /**
     * 授权应用密码
     **/
    private String password;

    /**
     * 授权应用昵称
     **/
    private String nickname;
    /**
     * 访问授权码
     **/
    private String accessToken;

    /**
     * 上次重置授权码时间
     **/
    private java.util.Date lastResetTokenTime;

    /**
     * 授权时间起
     **/
    private java.util.Date startTime;

    /**
     * 授权时间止
     **/
    private java.util.Date endTime;

    /**
     * 状态
     **/
    private Boolean status;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改时间
     **/
    private java.util.Date lastUpdate;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public java.util.Date getLastResetTokenTime() {
        return this.lastResetTokenTime;
    }

    public void setLastResetTokenTime(java.util.Date lastResetTokenTime) {
        this.lastResetTokenTime = lastResetTokenTime;
    }

    public java.util.Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", lastResetTokenTime=" + lastResetTokenTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
