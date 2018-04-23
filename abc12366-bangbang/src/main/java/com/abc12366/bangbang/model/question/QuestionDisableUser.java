package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 帮帮社区禁用用户表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionDisableUser implements Serializable {

	/**前端用户id**/
	private String userId;

	/**禁用原因**/
	private String reason;

	/**下次生效时间**/
	private java.util.Date activeTime;

	/**审核管理员id**/
	private String updateAdmin;

	/****/
	private java.util.Date updateTime;



	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return this.reason;
	}

	public void setActiveTime(java.util.Date activeTime){
		this.activeTime = activeTime;
	}

	public java.util.Date getActiveTime(){
		return this.activeTime;
	}

	public void setUpdateAdmin(String updateAdmin){
		this.updateAdmin = updateAdmin;
	}

	public String getUpdateAdmin(){
		return this.updateAdmin;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

}
