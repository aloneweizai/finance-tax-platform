package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 用户退单表
 **/
@SuppressWarnings("serial")
public class OrderBack implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * FK
     **/
    private String userId;

    /**
     * 订单号
     **/
    private String orderNo;

    /**
     * 退单原因
     **/
    private String reason;

    /**
     * 用户备注
     **/
    private String userRemark;

    /**
     * 管理员备注
     **/
    private String adminRemark;

    /**
     * 快递单号
     **/
    private String expressNo;

    /**
     * 快递公司
     **/
    private String expressComp;

    /**
     * 退单状态
     **/
    private String status;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    /**
     * 操作人员ID
     **/
    private String operatorUser;

    /**
     * 管理员确认备注
     */
    private String adminConfirmRemark;

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

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExpressNo() {
        return this.expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressComp() {
        return this.expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
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

    public String getOperatorUser() {
        return operatorUser;
    }

    public void setOperatorUser(String operatorUser) {
        this.operatorUser = operatorUser;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

    public String getAdminConfirmRemark() {
        return adminConfirmRemark;
    }

    public void setAdminConfirmRemark(String adminConfirmRemark) {
        this.adminConfirmRemark = adminConfirmRemark;
    }
}
