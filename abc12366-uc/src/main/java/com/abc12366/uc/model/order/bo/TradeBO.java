package com.abc12366.uc.model.order.bo;

import java.io.Serializable;
import java.util.List;


/**
 * 交易记录与订单关系表
 *
 * @author lizhongwei 2017-10-19
 **/
@SuppressWarnings("serial")
public class TradeBO implements Serializable {

    /**
     * 交易流水号
     **/
    private String tradeNo;

    /**
     * 订单号
     **/
    private String orderNo;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

    private List<TradeLogBO> tradeLogBOList;

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
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

    public List<TradeLogBO> getTradeLogBOList() {
        return tradeLogBOList;
    }

    public void setTradeLogBOList(List<TradeLogBO> tradeLogBOList) {
        this.tradeLogBOList = tradeLogBOList;
    }
}
