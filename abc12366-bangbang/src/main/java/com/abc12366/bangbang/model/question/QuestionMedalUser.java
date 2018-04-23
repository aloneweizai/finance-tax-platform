package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 帮帮勋章表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionMedalUser implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**勋章ID**varchar(64)**/
	private String medalId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**获取时间**datetime**/
	private java.util.Date medalTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setMedalId(String medalId){
		this.medalId = medalId;
	}

	public String getMedalId(){
		return this.medalId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setMedalTime(java.util.Date medalTime){
		this.medalTime = medalTime;
	}

	public java.util.Date getMedalTime(){
		return this.medalTime;
	}

}
