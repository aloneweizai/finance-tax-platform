package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 产品表
 **/
@SuppressWarnings("serial")
public class Goods implements Serializable {

    /****/
    private String id;

    /**
     * 产品名称
     **/
    private String name;

    /**
     * 展示图片URL
     **/
    private String imageUrl;

    /**
     * 产品介绍
     **/
    private String introduction;

    /**
     * 产品详情
     **/
    private String details;

    /**
     * 产品分类ID
     **/
    private String categoryId;

    /**
     * 是否上架：boolean
     **/
    private Boolean status;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    /**
     * 赠送积分
     **/
    private Integer giftPoints;

    /**
     * 排序
     **/
    private Integer sort;

    /**
     * 计件单位显示
     **/
    private String unit;

    /**
     * 推荐类型：1.最新产品 2.特价产品 3.热卖产品 4.推荐产品
     **/
    private String recommendType;

    /**
     * 是否需要寄送
     **/
    private Integer isShipping;

    /**
     * 是否免运费
     **/
    private Integer isFreeShipping;

    /**
     * 交易方式：RMB、POINTS
     **/
    private String tradeMethod;

    /**
     * 商品类型
     **/
    private String goodsType;

    /**
     * 发票内容：1.软件服务费 2.财税咨询费 3.技术服务费 4.财税培训费
     *
     * @return
     */
    private String invoiceContent;

    /**
     * 是否可退货，0，可退，1，不可退
     **/
    private String isReturn;

    /**
     * 是否可换货，0：可换，1：不可换
     **/
    private String isExchange;

    /**
     * 会员等级
     **/
    private String memberLevel;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Integer getGiftPoints() {
        return this.giftPoints;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRecommendType() {
        return this.recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public Integer getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Integer isShipping) {
        this.isShipping = isShipping;
    }

    public Integer getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Integer isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(String isExchange) {
        this.isExchange = isExchange;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }
}
