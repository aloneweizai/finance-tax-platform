package com.abc12366.uc.model.order;
import java.io.Serializable;


/**
 * 
 * ��Ʊ������־��¼
 * 
 **/
@SuppressWarnings("serial")
public class GoodsLog implements Serializable {

	/****/
	private String id;

	/**��ƷId**/
	private String goodsId;

	/**����**/
	private String action;

	/**����ʱ��**/
	private java.util.Date createTime;

	/**�����û�**/
	private String createUser;

	/**��ע**/
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

}
