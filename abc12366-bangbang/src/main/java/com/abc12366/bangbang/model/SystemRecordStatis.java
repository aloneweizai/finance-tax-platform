package com.abc12366.bangbang.model;
import java.io.Serializable;


/**
 * @author lizhongwei
 * @date  2017-12-12 12:21 PM
 * 用户使用系统记录
 **/
@SuppressWarnings("serial")
public class SystemRecordStatis implements Serializable {

	/**PK**/
	private String id;

	/**访问次数**/
	private Integer amount;

	/**菜单名称**/
	private String menu;

	/**统计时间**/
	private java.util.Date createTime;

	/**访问总次数**/
	private Integer total;


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

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
