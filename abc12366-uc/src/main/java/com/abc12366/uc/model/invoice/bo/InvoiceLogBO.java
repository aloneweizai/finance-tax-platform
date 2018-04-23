package com.abc12366.uc.model.invoice.bo;

import java.io.Serializable;


/**
 * 发票操作日志记录
 **/
@SuppressWarnings("serial")
public class InvoiceLogBO implements Serializable {

    /****/
    private String id;

    /**
     * 发票Id
     **/
    private String invoiceId;

    /**
     * 动作
     **/
    private String action;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 创建用户
     **/
    private String createUser;

    /**
     * 备注
     **/
    private String remark;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
