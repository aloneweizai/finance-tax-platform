package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;

/**
 * 退换货管理员操作
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-11 11:42 AM
 * @since 1.0.0
 */
public class ExchangeAdminBO {

    private String id;
    @Length(max = 500)
    private String adminRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }
}
