package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 产品参数
 **/
@SuppressWarnings("serial")
public class Product implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 产品ID
     **/
    private String goodsId;

    /**
     * 市场价
     **/
    private Double marketPrice;

    /**
     * 销售价
     **/
    private Double sellingPrice;

    /**
     * 成本价
     **/
    private Double costPrice;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    private Integer stock;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Double getMarketPrice() {
        return this.marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
