package com.abc12366.uc.model.invoice.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 发票信息
 **/
@SuppressWarnings("serial")
public class InvoiceCheckBO implements Serializable {

	@NotEmpty
	@Size(min = 2,max = 32)
	private String id;
	/**
	 * 是否同意开票
	 **/
	@NotNull
	private Boolean isBilling;

	/**
	 * 发票类型代码
	 */
	private String type;

	/**发票详情ID**/
	private String detailId;

	/**
	 * 备注
	 */
	@Size(min = 0,max = 2000)
	private String remark;

	public Boolean getIsBilling() {
		return isBilling;
	}

	public void setIsBilling(Boolean isBilling) {
		this.isBilling = isBilling;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
