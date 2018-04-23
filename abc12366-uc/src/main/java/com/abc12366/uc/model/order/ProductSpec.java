package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 产品规格表
 **/
@SuppressWarnings("serial")
public class ProductSpec implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * FK
     **/
    private String productId;

    /**
     * 规格ID
     **/
    private String specId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

}
