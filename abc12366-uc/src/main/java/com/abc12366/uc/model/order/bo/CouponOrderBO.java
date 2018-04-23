package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 订单使用优惠劵对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-16 5:53 PM
 * @since 1.0.0
 */
public class CouponOrderBO {

    /**
     * 领用优惠劵ID
     */
    @NotEmpty
    private String useCouponId;

    /**
     * 用户ID
     */
    @NotEmpty
    @Length(max = 64)
    private String userId;

    /**
     * 订单号
     */
    @NotEmpty
    @Length(max = 64)
    private String orderNo;

    /**
     * 商品品目
     */
    @NotEmpty
    @Length(max = 64)
    private String categoryId;

    /**
     * 使用优惠劵之前的金额
     */
    @NotEmpty
    private Double amount;

    /**
     * 优惠劵状态：0-未领取 1-已领取 2-已使用 3-已冻结 4-已删除 5-已过期 6-已作废
     */
    @NotEmpty
    @Length(max = 1)
    private String status;

    /**
     * 操作，1：提交订单，0：取消订单
     */
    private String operation;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CouponOrderBO{" +
                "useCouponId=" + useCouponId +
                ", userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }

    public String getUseCouponId() {
        return useCouponId;
    }

    public void setUseCouponId(String useCouponId) {
        this.useCouponId = useCouponId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
