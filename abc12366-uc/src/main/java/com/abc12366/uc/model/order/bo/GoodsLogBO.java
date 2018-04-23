package com.abc12366.uc.model.order.bo;
import java.io.Serializable;


/**
 *
 * 产品操作日志记录
 *
 **/
@SuppressWarnings("serial")
public class GoodsLogBO implements Serializable {

	/****/
	private String id;

	/**商品Id**/
	private String goodsId;

	/**动作**/
	private String action;

	/**创建时间**/
	private java.util.Date createTime;

	/**创建用户**/
	private String createUser;

	private String createUserName;

	/**备注**/
	private String remark;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setGoodsId(String goodsId){
		this.goodsId = goodsId;
	}

	public String getGoodsId(){
		return this.goodsId;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return this.action;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	public String getCreateUser(){
		return this.createUser;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}