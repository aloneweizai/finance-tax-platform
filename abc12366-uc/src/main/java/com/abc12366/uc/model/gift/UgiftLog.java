package com.abc12366.uc.model.gift;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;


/**
 * 礼包申请日志
 **/
@SuppressWarnings("serial")
public class UgiftLog implements Serializable {

    /****/
    private String id;

    /**
     * 申请单号
     **/
    private String applyId;

    /**
     * 动作
     **/
    private String action;

    /**
     * 创建时间
     **/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    /**
     * 操作用户ID
     **/
    private String adminId;

    /**
     * 操作用户姓名
     **/
    private String adminName;

    /**
     * 备注
     **/
    private String remark;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyId() {
        return this.applyId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminId() {
        return this.adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

}
