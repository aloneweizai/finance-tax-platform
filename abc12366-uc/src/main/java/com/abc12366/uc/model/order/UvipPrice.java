package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 会员价格表
 **/
@SuppressWarnings("serial")
public class UvipPrice implements Serializable {

    /****/
    private String id;

    /**
     * FK
     **/
    private String productId;

    /**
     * 会员等级
     **/
    private String vipLevel;

    /**
     * 会员折扣
     **/
    private Double discount;

    /**
     * 交易价格
     **/
    private Double tradePrice;

    /**
     * 赠送积分
     **/
    private Integer giftPoints;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

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

    public String getVipLevel() {
        return this.vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTradePrice() {
        return this.tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Integer getGiftPoints() {
        return this.giftPoints;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
