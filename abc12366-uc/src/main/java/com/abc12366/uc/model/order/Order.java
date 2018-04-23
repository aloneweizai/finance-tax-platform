package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 用户订单
 *
 * @author lizhongwei 2017-10-19
 **/
@SuppressWarnings("serial")
public class Order implements Serializable {

    /**
     * 订单编号
     **/
    private String orderNo;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 订单状态，2：待付款，3：付款中，4：付款成功，5：已发货，6：已完成，7：已结束，8：付款失败，9：已退单, 10:退换货中
     **/
    private String orderStatus;

    /**
     * 配送方式
     **/
    private String deliveryMethod;

    /**
     * 支付方式：WEIXIN、ALIPAY、POINTS
     **/
    private String payMethod;

    /**
     * 当前会员等级
     **/
    private String nowVipLevel;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 配送费
     **/
    private Double deliveryFee;

    /**
     * 成交金额
     **/
    private Double totalPrice;

    /**
     * 运单号
     **/
    private String expressNo;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 赠送积分
     **/
    private Integer giftPoints;

    /**
     * 交易方式：RMB、POINTS
     **/
    private String tradeMethod;

    /**
     * 是否已开发票，true：是，false：否
     **/
    private Boolean isInvoice;

    /**
     * 取消原因，字典表ID
     **/
    private String cancelId;

    /**
     * 推荐人工号
     **/
    private String recommendUser;

    /**
     * 物流公司ID
     **/
    private String expressCompId;

    /**
     * 收件人
     **/
    private String consignee;

    /**
     * 联系电话
     **/
    private String contactNumber;

    /**
     * 收件地址
     **/
    private String shippingAddress;

    /**
     * 是否需要寄送
     **/
    private Integer isShipping;

    /**
     * 是否免运费
     **/
    private Integer isFreeShipping;


    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setNowVipLevel(String nowVipLevel) {
        this.nowVipLevel = nowVipLevel;
    }

    public String getNowVipLevel() {
        return this.nowVipLevel;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setIsShipping(Integer isShipping) {
        this.isShipping = isShipping;
    }

    public Integer getIsShipping() {
        return this.isShipping;
    }

    public void setIsFreeShipping(Integer isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Integer getIsFreeShipping() {
        return this.isFreeShipping;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getDeliveryFee() {
        return this.deliveryFee;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressNo() {
        return this.expressNo;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    public Integer getGiftPoints() {
        return this.giftPoints;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getTradeMethod() {
        return this.tradeMethod;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Boolean getIsInvoice() {
        return this.isInvoice;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public String getCancelId() {
        return this.cancelId;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public String getRecommendUser() {
        return this.recommendUser;
    }

    public void setExpressCompId(String expressCompId) {
        this.expressCompId = expressCompId;
    }

    public String getExpressCompId() {
        return this.expressCompId;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsignee() {
        return this.consignee;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

}
