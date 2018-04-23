package com.abc12366.uc.model.pay.bo;

import java.io.Serializable;
/**
 * 商品信息
 * @author zhushuai 2017-8-5
 *
 */
public class AliProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String show_url;//商品的展示地址
    
    private String goods_id;//商品的编号
    
    private String goods_name;//商品名称
    
    private Integer quantity;//商品数量
    
    private String price;//商品单价，单位为元
    
    private String goods_category;//商品类目
    
    private String body;//商品描述信息
    
    
    
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getGoods_category() {
		return goods_category;
	}
	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
}
