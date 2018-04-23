package com.abc12366.uc.model.invoice.bo;

import com.abc12366.uc.model.User;

import java.io.Serializable;


/**
 * 发票快递单
 **/
@SuppressWarnings("serial")
public class ExpressBO implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 用户订单号
     **/
    private String invoiceOrderNo;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 快递单号
     **/
    private String expressNo;

    /**
     * 快递公司ID
     **/
    private String deliveryId;

    /**
     * 快递状态
     **/
    private String status;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    private java.util.Date startTime;

    private java.util.Date endTime;

    private User user;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getInvoiceOrderNo() {
        return invoiceOrderNo;
    }

    public void setInvoiceOrderNo(String invoiceOrderNo) {
        this.invoiceOrderNo = invoiceOrderNo;
    }
}
