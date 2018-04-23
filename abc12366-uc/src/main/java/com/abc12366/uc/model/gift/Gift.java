package com.abc12366.uc.model.gift;
import java.io.Serializable;


/**
 * 
 * 会员礼包表
 * 
 **/
@SuppressWarnings("serial")
public class Gift implements Serializable {

	/**PK**/
	private String id;

	/**礼物名称**/
	private String name;

	/**图片地址**/
	private String imageUrl;

	/**简单介绍**/
	private String introduction;

	/**详情**/
	private String details;

	/**状态：0-删除，1-下架，2-上架**/
	private String status;

	/**排序**/
	private Integer sort;

	/**分类，根据会员等级**/
	private String category;

	/**库存**/
	private Integer stock;

	/**销售价格**/
	private double sellingPrice;

	/**成本价格**/
	private double costPrice;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date lastUpdate;

	/**第三方产品详情URL**/
	private String detailUrl;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return this.imageUrl;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return this.introduction;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return this.details;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return this.category;
	}

	public void setStock(Integer stock){
		this.stock = stock;
	}

	public Integer getStock(){
		return this.stock;
	}

	public void setSellingPrice(double sellingPrice){
		this.sellingPrice = sellingPrice;
	}

	public double getSellingPrice(){
		return this.sellingPrice;
	}

	public void setCostPrice(double costPrice){
		this.costPrice = costPrice;
	}

	public double getCostPrice(){
		return this.costPrice;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	@Override
	public String toString() {
		return "Gift{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", introduction='" + introduction + '\'' +
				", details='" + details + '\'' +
				", status='" + status + '\'' +
				", sort=" + sort +
				", category='" + category + '\'' +
				", stock=" + stock +
				", sellingPrice=" + sellingPrice +
				", costPrice=" + costPrice +
				", createTime=" + createTime +
				", lastUpdate=" + lastUpdate +
				", detailUrl='" + detailUrl + '\'' +
				'}';
	}
}
