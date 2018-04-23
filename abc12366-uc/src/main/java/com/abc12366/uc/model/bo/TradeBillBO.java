package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 交易帐单
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-12 2:11 PM
 * @since 1.0.0
 */
public class TradeBillBO {

    @NotEmpty
    @Length(min = 16, max = 64)
    private String orderNo;

    @NotEmpty
    @Length(min = 16, max = 64)
    private String tradeNo;

    @NotEmpty
    private Double amount;

    @NotEmpty
    @Length(min = 16, max = 64)
    private String aliTrandeNo;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TradeBillBO{" +
                "orderNo='" + orderNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getAliTrandeNo() {
        return aliTrandeNo;
    }

    public void setAliTrandeNo(String aliTrandeNo) {
        this.aliTrandeNo = aliTrandeNo;
    }
}
