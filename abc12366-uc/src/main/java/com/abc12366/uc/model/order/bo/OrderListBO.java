package com.abc12366.uc.model.order.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 用户订单列表BO
 **/
@SuppressWarnings("serial")
public class OrderListBO implements Serializable {


    /**
     * 订单编号
     **/
    private String orderNo;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 订单状态：1.新订单 2.确认订单 3.取消订单 4.作废订单 5.完成订单 6.退款 7.部分退款
     **/
    private String orderStatus;

    /**
     * 支付方式：WEIXIN、ALIPAY
     **/
    private String payMethod;


    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 是否需要寄送
     **/
    private Integer isShipping;


    /**
     * 成交总金额
     **/
    private Double totalPrice;


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

    private Date startTime;

    private Date endTime;

    private String address;

    private String addressId;

    private String expressNo;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 商品名称+规格信息
     */
    private String goodsName;

    /**
     * 数量
     */
    private int num;

    private List<OrderProductBO> orderProductBOList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Integer isShipping) {
        this.isShipping = isShipping;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public List<OrderProductBO> getOrderProductBOList() {
        return orderProductBOList;
    }

    public void setOrderProductBOList(List<OrderProductBO> orderProductBOList) {
        this.orderProductBOList = orderProductBOList;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
}
