package com.abc12366.uc.model.order.bo;

/**
 * 订单统计BO
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-10-25 3:30 PM
 * @since 1.0.0
 */
public class OrderStatBO {
    /**
     * 2-待付款
     */
    private Integer orderStatus2;

    /**
     * 3-付款中
     */
    private Integer orderStatus3;
    /**
     * 4-付款成功
     */
    private Integer orderStatus4;
    /**
     * 6-订单完成
     */
    private Integer orderStatus6;
    /**
     * 7-订单结束
     */
    private Integer orderStatus7;
    /**
     * 9-已退款
     */
    private Integer orderStatus9;
    /**
     * 订单总数
     */
    private Integer orderStatus;

    public Integer getOrderStatus3() {
        return orderStatus3;
    }

    public void setOrderStatus3(Integer orderStatus3) {
        this.orderStatus3 = orderStatus3;
    }

    public Integer getOrderStatus4() {
        return orderStatus4;
    }

    public void setOrderStatus4(Integer orderStatus4) {
        this.orderStatus4 = orderStatus4;
    }

    public Integer getOrderStatus6() {
        return orderStatus6;
    }

    public void setOrderStatus6(Integer orderStatus6) {
        this.orderStatus6 = orderStatus6;
    }

    public Integer getOrderStatus7() {
        return orderStatus7;
    }

    public void setOrderStatus7(Integer orderStatus7) {
        this.orderStatus7 = orderStatus7;
    }

    public Integer getOrderStatus9() {
        return orderStatus9;
    }

    public void setOrderStatus9(Integer orderStatus9) {
        this.orderStatus9 = orderStatus9;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus2() {
        return orderStatus2;
    }

    public void setOrderStatus2(Integer orderStatus2) {
        this.orderStatus2 = orderStatus2;
    }
}
