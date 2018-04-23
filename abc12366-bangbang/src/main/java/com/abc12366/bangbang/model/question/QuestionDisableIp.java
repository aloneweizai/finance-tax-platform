package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 帮帮社区禁言IP表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionDisableIp implements Serializable {

	/**PK**/
	private String id;

	/**ip地址**/
	private String ip;

	/**禁言原因**/
	private String reason;

	/**下次生效时间**/
	private java.util.Date activeTime;

	/**审核管理员id**/
	private String updateAdmin;

	/****/
	private java.util.Date updateTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return this.ip;
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
