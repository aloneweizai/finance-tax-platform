package com.abc12366.uc.model.order.bo;

import com.abc12366.uc.model.order.Goods;

import java.io.Serializable;


/**
 * 产品库存表
 **/
@SuppressWarnings("serial")
public class ProductRepoBO implements Serializable {

    private String id;
    private String goodsId;
    private String productId;
    private Integer income;
    private Integer outcome;
    private Integer stock;
    private String remark;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;

    private String productName;
    private String type;
    private Integer option;
    private Goods goods;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public String getOptionUser() {
        return optionUser;
    }

    public void setOptionUser(String optionUser) {
        this.optionUser = optionUser;
    }
}
