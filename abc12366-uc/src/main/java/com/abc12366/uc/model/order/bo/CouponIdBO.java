package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 使用优惠劵计算最大优惠对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-16 5:53 PM
 * @since 1.0.0
 */
public class CouponIdBO {


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
                ", userId='" + userId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
