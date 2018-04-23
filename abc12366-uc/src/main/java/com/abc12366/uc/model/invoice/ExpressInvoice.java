package com.abc12366.uc.model.invoice;

import java.io.Serializable;


/**
 * 快递单与发票的关联信息
 **/
@SuppressWarnings("serial")
public class ExpressInvoice implements Serializable {

    /****/
    private String id;

    /**
     * 快递单ID
     **/
    private String expressId;

    /**
     * 发票ID
     **/
    private String invoiceId;

    /****/
    private java.util.Date createTime;

    /****/
    private String lastUpdate;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpressId() {
        return this.expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
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

    public String getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
