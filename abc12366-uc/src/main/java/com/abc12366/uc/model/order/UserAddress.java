package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 收货地址信息
 **/
@SuppressWarnings("serial")
public class UserAddress implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 收货人姓名
     **/
    private String name;

    /**
     * 省
     **/
    private String province;

    /**
     * 市
     **/
    private String city;

    /**
     * 区
     **/
    private String area;

    /**
     * 详细地址
     **/
    private String detail;

    /**
     * 手机号码
     **/
    private String phone;

    /**
     * 是否默认地址
     **/
    private Boolean isDefault;

    /**
     * 地址状态
     **/
    private Boolean status;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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

}
