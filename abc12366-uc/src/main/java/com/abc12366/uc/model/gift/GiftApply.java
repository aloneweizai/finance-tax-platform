package com.abc12366.uc.model.gift;
import java.io.Serializable;


/**
 * 
 * 礼包申请表与礼包关联表
 * 
 **/
@SuppressWarnings("serial")
public class GiftApply implements Serializable {

	/**PK**/
	private String id;

	/**申请ID**/
	private String applyId;

	/**礼物ID**/
	private String giftId;

	/**礼物名称**/
	private String giftName;

	/**礼物金额**/
	private double giftAmount;

    /**礼物数量**/
    private int giftNum;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setApplyId(String applyId){
		this.applyId = applyId;
	}

	public String getApplyId(){
		return this.applyId;
	}

	public void setGiftId(String giftId){
		this.giftId = giftId;
	}

	public String getGiftId(){
		return this.giftId;
	}

	public void setGiftName(String giftName){
		this.giftName = giftName;
	}

	public String getGiftName(){
		return this.giftName;
	}

	public void setGiftAmount(double giftAmount){
		this.giftAmount = giftAmount;
	}

	public double getGiftAmount(){
		return this.giftAmount;
	}

    public int getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(int giftNum) {
        this.giftNum = giftNum;
    }

	@Override
	public String toString() {
		return "GiftApply{" +
				"id='" + id + '\'' +
				", applyId='" + applyId + '\'' +
				", giftId='" + giftId + '\'' +
				", giftName='" + giftName + '\'' +
				", giftAmount=" + giftAmount +
				", giftNum=" + giftNum +
				'}';
	}
}
