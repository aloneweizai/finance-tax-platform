package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 订单与发票关联表
 **/
@SuppressWarnings("serial")
public class OrderInvoice implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 订单号
     **/
    private String orderNo;

    /**
     * 发票ID
     **/
    private String invoiceId;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    private Boolean status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
