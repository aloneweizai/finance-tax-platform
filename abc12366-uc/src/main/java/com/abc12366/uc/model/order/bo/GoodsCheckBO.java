package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


/**
 * 产品审核BO
 **/
@SuppressWarnings("serial")
public class GoodsCheckBO implements Serializable {

    private String goodsIds;
    private Boolean status;

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
