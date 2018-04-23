package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 秘籍收藏表
 * 
 **/
@SuppressWarnings("serial")
public class CheatsCollectBo implements Serializable {

	/**收藏ID**varchar(64)**/
	private String collectId;

	/**秘籍ID**varchar(64)**/
	private String cheatsId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**收藏时间**datetime**/
	private java.util.Date collectTime;



	public void setCollectId(String collectId){
		this.collectId = collectId;
	}

	public String getCollectId(){
		return this.collectId;
	}

	public void setCheatsId(String cheatsId){
		this.cheatsId = cheatsId;
	}

	public String getCheatsId(){
		return this.cheatsId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setCollectTime(java.util.Date collectTime){
		this.collectTime = collectTime;
	}

	public java.util.Date getCollectTime(){
		return this.collectTime;
	}

}
