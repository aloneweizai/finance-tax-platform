package com.abc12366.uc.model.order.bo;

import java.io.Serializable;


/**
 * 用户订单
 *
 * @author lizhongwei 2017-11-21
 **/
@SuppressWarnings("serial")
public class OrderStatisBO implements Serializable {


    /**
     * 订单状态，2：待付款，3：付款中，4：付款成功，5：已发货，6：已完成，7：已结束，8：付款失败，9：已退单, 10:退换货中
     **/
    private String orderStatus;

    /**
     * 成交金额
     **/
    private Double totalPrice;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 订单月份
     */
    private String orderMonth;

    /**
     * 交易方式：RMB、POINTS
     **/
    private String tradeMethod;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }
}
