package com.abc12366.uc.model.admin.bo;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


/**
 * 系统组织机构表
 **/
@SuppressWarnings("serial")
public class OrganizationBO implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 父ID
     **/
    private String parentId;

    private String parentName;
    /**
     * 机构名称
     **/
    @NotEmpty
    private String name;

    /**
     * 机构代码
     **/
    @NotEmpty
    private String code;

    /**
     * 地区编号
     **/
    private String areaId;

    /**
     * 机构类型
     **/
    private String type;

    /**
     * 联系人
     **/
    private String contact;

    /**
     * 联系电话
     **/
    private String phone;

    /**
     * 联系地址
     **/
    private String address;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 有效标志
     **/
    private Boolean status;

    /**
     * 创建用户
     **/
    private String createUser;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改用户
     **/
    private String lastUser;

    /**
     * 修改时间
     **/
    private java.util.Date lastUpdate;

    private String province;

    private String city;

    private String area;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUser() {
        return this.lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
