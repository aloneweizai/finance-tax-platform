package com.abc12366.uc.model;
import java.io.Serializable;


/**
 * 
 * 用户银行卡信息表
 * 
 **/
@SuppressWarnings("serial")
	public class UserBank implements Serializable {

	/****/
	private String id;

	/**用户ID**/
	private String userId;

	/**银行卡真实姓名**/
	private String fullName;

	/**银行名称**/
	private String bankName;

	/**银行分行地址**/
	private String bandAddress;

	/**是否默认，0：否，1：是**/
	private String isDefault;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date lastUpdate;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return this.bankName;
	}

	public void setBandAddress(String bandAddress){
		this.bandAddress = bandAddress;
	}

	public String getBandAddress(){
		return this.bandAddress;
	}

	public void setIsDefault(String isDefault){
		this.isDefault = isDefault;
	}

	public String getIsDefault(){
		return this.isDefault;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
