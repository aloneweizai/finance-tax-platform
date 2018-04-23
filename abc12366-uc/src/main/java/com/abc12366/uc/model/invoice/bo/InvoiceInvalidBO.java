package com.abc12366.uc.model.invoice.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 发票作废信息
 **/
@SuppressWarnings("serial")
public class InvoiceInvalidBO implements Serializable {

    /**
     * 发票申请ID
     */
	@NotEmpty
	private String id;
	/**
	 * 作废类型，0：发票订单，1：发票订单和发票
	 **/
	@NotNull
	private Integer type;

    /**
     * 发票性质：1.纸质发票 2.电子发票
     **/
    @NotNull
    private String property;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
