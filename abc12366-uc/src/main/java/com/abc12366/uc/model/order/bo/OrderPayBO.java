package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 用户订单
 **/
@SuppressWarnings("serial")
public class OrderPayBO implements Serializable {

    /**用户ID**/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String userId;

    /**支付方式：WEIXIN、ALIPAY、POINTS**/
    private String payMethod;

    /**交易流水号**/
    private String tradeNo;

    /**交易方式：RMB、POINTS**/
    private String tradeMethod;

    /**成交金额**/
    private Double totalPrice;

    /**
     * 是否支付成功，1：支付中，2：支付成功，3：支付失败，
     **/
    private int isPay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }
}
