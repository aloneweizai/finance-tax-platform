package com.abc12366.uc.model.gift.bo;
import java.io.Serializable;


/**
 * 
 * 会员礼包交易日志
 * 
 **/
@SuppressWarnings("serial")
public class UamountLogBO implements Serializable {

	/**PK**/
	private String id;

	/**用户ID**/
	private String userId;

	/**业务ID**/
	private String businessId;

	/**收入**/
	private double income;

	/**支出**/
	private double outgo;

	/**可用金额**/
	private double usable;

	/**创建时间**/
	private java.util.Date createTime;

	/**备注**/
	private String remark;



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

	public void setBusinessId(String businessId){
		this.businessId = businessId;
	}

	public String getBusinessId(){
		return this.businessId;
	}

	public void setIncome(double income){
		this.income = income;
	}

	public double getIncome(){
		return this.income;
	}

	public void setOutgo(double outgo){
		this.outgo = outgo;
	}

	public double getOutgo(){
		return this.outgo;
	}

	public void setUsable(double usable){
		this.usable = usable;
	}

	public double getUsable(){
		return this.usable;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
