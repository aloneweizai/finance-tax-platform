package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


/**
 * 配送方式
 **/
@SuppressWarnings("serial")
public class DeliveryMethodUpdateBO implements Serializable {

    private String id;
    private Boolean status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
