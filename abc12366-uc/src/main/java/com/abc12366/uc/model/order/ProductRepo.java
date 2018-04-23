package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 产品库存表
 **/
@SuppressWarnings("serial")
public class ProductRepo implements Serializable {

    /**
     * FK
     **/
    private String id;

    /**
     * FK
     **/
    private String goodsId;

    /**
     * FK
     **/
    private String productId;

    /**
     * 入库
     **/
    private Integer income;

    /**
     * 出库
     **/
    private Integer outcome;

    /**
     * 库存
     **/
    private Integer stock;

    /**
     * 备注
     **/
    private String remark;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    /**
     * 操作用户
     */
    private String optionUser;

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

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getIncome() {
        return this.income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getOutcome() {
        return this.outcome;
    }

    public void setOutcome(Integer outcome) {
        this.outcome = outcome;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getOptionUser() {
        return optionUser;
    }

    public void setOptionUser(String optionUser) {
        this.optionUser = optionUser;
    }
}
