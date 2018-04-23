package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 使用优惠劵计算金额对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-16 5:53 PM
 * @since 1.0.0
 */
public class CouponCalculateBO {


    /**
     * 领用的优惠券ID
     */
    @NotEmpty
    private String useCouponId;

    /**
     * 优惠劵ID
     */
    private String couponId;

    /**
     * 用户ID
     */
    @NotEmpty
    @Length(max = 64)
    private String userId;

    /**
     * 商品品目
     */
    @NotEmpty
    @Length(max = 64)
    private String categoryId;

    /**
     * 使用优惠劵之前的金额
     */
    @NotNull
    private Double amount;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "CouponOrderBO{" +
                "couponId=" + couponId +
                ", userId='" + userId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getUseCouponId() {
        return useCouponId;
    }

    public void setUseCouponId(String useCouponId) {
        this.useCouponId = useCouponId;
    }
}
