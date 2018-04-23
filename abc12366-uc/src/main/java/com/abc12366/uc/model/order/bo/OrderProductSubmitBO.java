package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 订单商品对应关系表-提交订单
 *
 * @author lizhongwei 2017-10-19
 **/
@SuppressWarnings("serial")
public class OrderProductSubmitBO implements Serializable {

    /**
     * FK，订单号
     **/
    private String orderNo;

    /**
     * 商品原价
     **/
    @NotNull
    private Double goodsPrice;

    /**
     * 购买数量
     **/
    @NotNull
    private Integer num;

    /**
     * 成交价格
     **/
    @NotNull
    private Double dealPrice;

    /**
     * 商品名称
     **/
    @NotEmpty
    @Size(min = 6, max = 200)
    private String name;

    /**
     * 商品分类
     **/
    private String categoryId;

    /**
     * 分类名称
     **/
    private String category;

    /**
     * 商品重量，单位：克
     **/
    private Double weight;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    /**
     * FK，商品ID
     **/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String goodsId;

    /**
     * FK，商品规格ID
     **/
    private String productId;

    /**
     * 规格信息
     **/
    @NotEmpty
    @Size(min = 2, max = 255)
    private String specInfo;

    /**
     * 是否可退货，0，可退，1，不可退
     **/
    @NotEmpty
    private String isReturn;

    /**
     * 是否可换货，0：可换，1：不可换
     **/
    @NotEmpty
    private String isExchange;

    /**
     * 展示图片URL
     **/
    private String imageUrl;

    /**
     * 商品类型，1.虚拟，2.实物，3.服务，4.会员服务，5.会员充值，6.学堂服务
     **/
    private String goodsType;

    /**
     * 商品浏览URL
     **/
    private String browseUrl;

    /**
     * 交易渠道，(字典ID)
     **/
    private String tradingChannels;


    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsPrice() {
        return this.goodsPrice;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Double getDealPrice() {
        return this.dealPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public String getSpecInfo() {
        return this.specInfo;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getIsReturn() {
        return this.isReturn;
    }

    public void setIsExchange(String isExchange) {
        this.isExchange = isExchange;
    }

    public String getIsExchange() {
        return this.isExchange;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return this.goodsType;
    }

    public void setBrowseUrl(String browseUrl) {
        this.browseUrl = browseUrl;
    }

    public String getBrowseUrl() {
        return this.browseUrl;
    }

    public void setTradingChannels(String tradingChannels) {
        this.tradingChannels = tradingChannels;
    }

    public String getTradingChannels() {
        return this.tradingChannels;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
