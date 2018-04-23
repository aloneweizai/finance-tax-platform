package com.abc12366.gateway.model;

import java.io.Serializable;


/**
 * 接入应用设置表
 **/
@SuppressWarnings("serial")
public class AppSetting implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * APPID
     **/
    private String appId;

    /**
     * 接口ID
     **/
    private String apiId;

    /**
     * 每分钟允许访问次数
     **/
    private Integer timesPerMinute;

    /**
     * 每小时允许访问次数
     **/
    private Integer timesPerHour;

    /**
     * 每天允许访问次数
     **/
    private Integer timesPerDay;

    /**
     * 启停状态: 0停用，1启用
     **/
    private Boolean status;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改时间
     **/
    private java.util.Date lastUpdate;

    /**
     * 是否需要身份验证
     **/
    private Boolean isValidate;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiId() {
        return this.apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public Integer getTimesPerMinute() {
        return this.timesPerMinute;
    }

    public void setTimesPerMinute(Integer timesPerMinute) {
        this.timesPerMinute = timesPerMinute;
    }

    public Integer getTimesPerHour() {
        return this.timesPerHour;
    }

    public void setTimesPerHour(Integer timesPerHour) {
        this.timesPerHour = timesPerHour;
    }

    public Integer getTimesPerDay() {
        return this.timesPerDay;
    }

    public void setTimesPerDay(Integer timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Boolean getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(Boolean isValidate) {
        this.isValidate = isValidate;
    }

    @Override
    public String toString() {
        return "AppSetting{" +
                "id='" + id + '\'' +
                ", appId='" + appId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", timesPerMinute=" + timesPerMinute +
                ", timesPerHour=" + timesPerHour +
                ", timesPerDay=" + timesPerDay +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", isValidate=" + isValidate +
                '}';
    }
}
