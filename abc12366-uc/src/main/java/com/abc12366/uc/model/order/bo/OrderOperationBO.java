package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;


/**
 * 管理员操作订单BO
 **/
@SuppressWarnings("serial")
public class OrderOperationBO implements Serializable {

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
    @Length(max = 100)
    private String remark;

    /**
     * 取消原因，字典ID
     */
    private String cancelId;

    /**
     * 配送方式
     **/
    private String deliveryMethod;

    /**
     * 运单号
     **/
    private String expressNo;

    /**物流公司ID**/
    private String expressCompId;

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

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCompId() {
        return expressCompId;
    }

    public void setExpressCompId(String expressCompId) {
        this.expressCompId = expressCompId;
    }
}
