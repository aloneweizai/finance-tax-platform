package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 问题邀请用户表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionInviteBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**问题ID**varchar(64)**/
	private String questionId;

	/**邀请用户ID**varchar(64)**/
	private String userId;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
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

}
