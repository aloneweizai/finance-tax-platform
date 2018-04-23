package com.abc12366.bangbang.model;
import java.io.Serializable;


/**
 * 
 * 用户使用系统记录统计表
 * 
 **/
@SuppressWarnings("serial")
public class SystemRecordCompany implements Serializable {

	/**PK**/
	private String id;

	/**访问次数**/
	private Integer amount;

	/**菜单名称**/
	private String menu;

	/**纳税人识别号**/
	private String nsrsbh;

	/**统计时间**/
	private java.util.Date createTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setAmount(Integer amount){
		this.amount = amount;
	}

	public Integer getAmount(){
		return this.amount;
	}

	public void setMenu(String menu){
		this.menu = menu;
	}

	public String getMenu(){
		return this.menu;
	}

	public void setNsrsbh(String nsrsbh){
		this.nsrsbh = nsrsbh;
	}

	public String getNsrsbh(){
		return this.nsrsbh;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

}
