package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 问题收藏表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionCollect implements Serializable {

	/**收藏ID**varchar(64)**/
	private String collectId;

	/**问题ID**varchar(64)**/
	private String questionId;

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

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return this.questionId;
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
