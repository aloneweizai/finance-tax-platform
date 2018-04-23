package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 订单产品规格
 **/
@SuppressWarnings("serial")
public class OrderProductSpec implements Serializable {

    /**
     * FK
     **/
    private String orderNo;

    /**
     * FK
     **/
    private String productId;

    /**
     * FK
     **/
    private String specId;

    private String specName;

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

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
