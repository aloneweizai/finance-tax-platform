package com.abc12366.uc.model.bo;
import java.io.Serializable;


/**
 * 
 * 用户积分返现订单表
 * 
 **/
@SuppressWarnings("serial")
public class UserPointsApplyBO implements Serializable {

	/**积分返现订单号**/
	private String pointsNo;

	/****/
	private String userId;

	/**积分订单状态**/
	private String status;

	/**支付方式：WEIXIN、ALIPAY**/
	private String payMethod;

	/**创建时间**/
	private java.util.Date createTime;

	/**冻结时间**/
	private java.util.Date freezeTime;

	/**用户名**/
	private String username;

	/**成交金额**/
	private Double totalPrice;

	/**赠送金额**/
	private Double givePrice;

	/**冻结状态，1:冻结，2:放款，3：打款，4：完成**/
	private String freezeStatus;

	/**最后修改时间**/
	private java.util.Date lastUpdate;



	public void setPointsNo(String pointsNo){
		this.pointsNo = pointsNo;
	}

	public String getPointsNo(){
		return this.pointsNo;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setPayMethod(String payMethod){
		this.payMethod = payMethod;
	}

	public String getPayMethod(){
		return this.payMethod;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setFreezeTime(java.util.Date freezeTime){
		this.freezeTime = freezeTime;
	}

	public java.util.Date getFreezeTime(){
		return this.freezeTime;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setTotalPrice(Double totalPrice){
		this.totalPrice = totalPrice;
	}

	public Double getTotalPrice(){
		return this.totalPrice;
	}

	public void setGivePrice(Double givePrice){
		this.givePrice = givePrice;
	}

	public Double getGivePrice(){
		return this.givePrice;
	}

	public void setFreezeStatus(String freezeStatus){
		this.freezeStatus = freezeStatus;
	}

	public String getFreezeStatus(){
		return this.freezeStatus;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

}
