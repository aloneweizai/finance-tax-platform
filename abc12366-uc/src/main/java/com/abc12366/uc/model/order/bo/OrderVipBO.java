package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 用户订单-开通VIP
 *
 * @author lizhongwei 2017-10-19
 **/
@SuppressWarnings("serial")
public class OrderVipBO implements Serializable {


    /**
     * 用户ID
     **/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String userId;

    /**
     * 订单号
     */
    @NotEmpty
    @Size(min = 6, max = 64)
    private String orderNo;

    /**
     * 会员等级编码
     */
    @NotEmpty
    @Size(min = 6, max = 64)
    private String levelCode;

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

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }
}
