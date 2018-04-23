package com.abc12366.uc.model.order.bo;
import java.io.Serializable;


/**
 * 
 * 购买课程赠送权益表
 * 
 **/
@SuppressWarnings("serial")
public class OrderGiftBO implements Serializable {

	/**主键**/
	private String id;

	/**关联uc_order的主键**/
	private String giftId;

	/**操作符号：or-或,and-和**/
	private String operSymbol;

	/**操作类型：POINTS-积分，COUPON-优惠券，VIP-会员，AMOUNT-礼包金额**/
	private String operType;

	/**操作值**/
	private String operValue;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setOperSymbol(String operSymbol){
		this.operSymbol = operSymbol;
	}

	public String getOperSymbol(){
		return this.operSymbol;
	}

	public void setOperType(String operType){
		this.operType = operType;
	}

	public String getOperType(){
		return this.operType;
	}

	public void setOperValue(String operValue){
		this.operValue = operValue;
	}

	public String getOperValue(){
		return this.operValue;
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	@Override
	public String toString() {
		return "OrderGiftBO{" +
				"id='" + id + '\'' +
				", giftId='" + giftId + '\'' +
				", operSymbol='" + operSymbol + '\'' +
				", operType='" + operType + '\'' +
				", operValue='" + operValue + '\'' +
				'}';
	}
}
