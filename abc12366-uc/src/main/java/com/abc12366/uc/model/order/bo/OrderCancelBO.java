package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


/**
 * 用户取消订单BO
 **/
@SuppressWarnings("serial")
public class OrderCancelBO implements Serializable {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取消原因，字典ID
     */
    private String cancelId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }
}
