package com.abc12366.uc.model.gift.bo;

import java.io.Serializable;
import java.util.List;


/**
 *
 * 会员礼包申请表
 *
 **/
@SuppressWarnings("serial")
public class UgiftApplyBO implements Serializable {

	/**申请单号**/
	private String applyId;

	/**申请用户ID**/
	private String userId;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date lastUpdate;

	/**状态：0-已拒绝，1-待处理，2-已审批，3-已发货，4-已完成**/
	private String status;

	/**快递单号**/
	private String expressNo;

	/**快递公司**/
	private String expressComp;

	/**收件人姓名**/
	private String name;

	/**收件人手机**/
	private String phone;

	/**收件人地址**/
	private String address;

	/**备注**/
	private String remark;

	private List<GiftApplyBO> giftApplyBOList;

	private List<UgiftLogBO> ugiftLogBOList;

	public void setApplyId(String applyId){
		this.applyId = applyId;
	}

	public String getApplyId(){
		return this.applyId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setExpressNo(String expressNo){
		this.expressNo = expressNo;
	}

	public String getExpressNo(){
		return this.expressNo;
	}

	public void setExpressComp(String expressComp){
		this.expressComp = expressComp;
	}

	public String getExpressComp(){
		return this.expressComp;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public List<GiftApplyBO> getGiftApplyBOList() {
		return giftApplyBOList;
	}

	public void setGiftApplyBOList(List<GiftApplyBO> giftApplyBOList) {
		this.giftApplyBOList = giftApplyBOList;
	}

    public List<UgiftLogBO> getUgiftLogBOList() {
        return ugiftLogBOList;
    }

    public void setUgiftLogBOList(List<UgiftLogBO> ugiftLogBOList) {
        this.ugiftLogBOList = ugiftLogBOList;
    }
}
