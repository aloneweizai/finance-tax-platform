package com.abc12366.cms.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 宣传页图片
 * 
 * @author Administrator
 *
 */
public class ProductImg {
	private String id;

	@NotNull
	private String productId; // 商品ID

	@NotNull
	@Length(max = 255)
	private String imageUrl; // 商品图片地址

	@Length(max = 150)
	private String description; // 描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
