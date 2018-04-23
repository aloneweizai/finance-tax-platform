package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


public class UserAddressUpdateBO implements Serializable {

    private String id;

    private String userId;

    private Boolean isDefault;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}
