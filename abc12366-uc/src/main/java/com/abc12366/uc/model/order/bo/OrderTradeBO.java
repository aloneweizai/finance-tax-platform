package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


/**
 * 用户订单
 *
 * @author lizhongwei 2017-10-19
 **/
@SuppressWarnings("serial")
public class OrderTradeBO implements Serializable {


    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 交易流水号
     **/
    private String tradeNo;

    /**
     * 交易名称
     **/
    private String tradeName;

    /**
     * 交易金额
     **/
    private String tradeAmount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }
}
