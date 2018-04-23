package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


/**
 * 订单产品规格
 **/
@SuppressWarnings("serial")
public class OrderProductspecBO implements Serializable {

    private String orderNo;
    private String productId;
    private String specId;
    private String fieldValue;
    private String fieldKey;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSpecId() {
        return this.specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }
}
