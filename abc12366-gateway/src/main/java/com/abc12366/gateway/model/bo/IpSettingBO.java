package com.abc12366.gateway.model.bo;

import java.io.Serializable;


/**
 * IP地址阀值设置
 **/
@SuppressWarnings("serial")
public class IpSettingBO implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 时间间隔设置值（秒）
     **/
    private Integer setTime;

    /**
     * 相同地址阀值
     **/
    private Integer sameThreshold;

    /**
     * 不同地址阀值
     **/
    private Integer distinctThreshold;

    /**
     * 禁用时间设置值（秒）
     **/
    private Integer disableTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSetTime() {
        return this.setTime;
    }

    public void setSetTime(Integer setTime) {
        this.setTime = setTime;
    }

    public Integer getSameThreshold() {
        return this.sameThreshold;
    }

    public void setSameThreshold(Integer sameThreshold) {
        this.sameThreshold = sameThreshold;
    }

    public Integer getDistinctThreshold() {
        return this.distinctThreshold;
    }

    public void setDistinctThreshold(Integer distinctThreshold) {
        this.distinctThreshold = distinctThreshold;
    }

    public Integer getDisableTime() {
        return this.disableTime;
    }

    public void setDisableTime(Integer disableTime) {
        this.disableTime = disableTime;
    }

    @Override
    public String toString() {
        return "IpSettingBO{" +
                "id='" + id + '\'' +
                ", setTime=" + setTime +
                ", sameThreshold=" + sameThreshold +
                ", distinctThreshold=" + distinctThreshold +
                ", disableTime=" + disableTime +
                '}';
    }
}
