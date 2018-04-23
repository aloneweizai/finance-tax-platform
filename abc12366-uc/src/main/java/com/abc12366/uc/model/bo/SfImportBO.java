package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-10 9:11 PM
 * @since 1.0.0
 */
public class SfImportBO {

    @NotEmpty
    @Length(min=6, max = 32)
    private String orderNo;

    @NotEmpty
    @Length(min=1, max = 100)
    private String expressNo;

    @NotEmpty
    @Length(min=6, max = 200)
    private String expressComp;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    @Override
    public String toString() {
        return "SfImportBO{" +
                "orderNo='" + orderNo + '\'' +
                ", expressNo='" + expressNo + '\'' +
                '}';
    }

    public String getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }
}
