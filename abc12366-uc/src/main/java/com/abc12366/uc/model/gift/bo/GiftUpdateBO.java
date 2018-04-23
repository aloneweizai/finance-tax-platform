package com.abc12366.uc.model.gift.bo;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 会员礼包表
 *
 **/
@SuppressWarnings("serial")
public class GiftUpdateBO implements Serializable {

    /**PK**/
    private String id;

    /**礼物名称**/
    private String name;

    /**图片地址**/
    private String imageUrl;

    /**简单介绍**/
    private String introduction;

    /**详情**/
    private String details;

    /**状态：0-删除，1-下架，2-上架**/
    private String status;

    /**排序**/
    private Integer sort;

    /**分类，根据会员等级**/
    private String category;

    /**库存**/
    private Integer stock;

    /**销售价格**/
    private double sellingPrice;

    /**成本价格**/
    private double costPrice;

    /**创建时间**/
    private java.util.Date createTime;

    /**更新时间**/
    private java.util.Date lastUpdate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
