package com.abc12366.uc.model.admin;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 系统用户扩展表
 **/
@SuppressWarnings("serial")
public class AdminExtend implements Serializable {

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 组织机构ID
     **/
    private String orgId;

    /**
     * 职务
     **/
    @NotEmpty
    @Size(min = 0, max = 32, message = "职务长度为0-32位")
    private String job;

    /**
     * 联系方式
     **/
    @NotEmpty
    @Size(min = 0, max = 20, message = "联系方式长度0-20位")
    private String phone;

    /**
     * 联系地址
     **/
    @NotEmpty
    @Size(min = 0, max = 100, message = "地址长度为0-100位")
    private String address;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改时间
     **/
    private java.util.Date lastUpdate;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
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
