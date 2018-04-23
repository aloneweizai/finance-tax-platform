package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 问题邀请用户表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionInvite implements Serializable {

	/****varchar(64)**/
	private String id;

	/**问题ID**varchar(64)**/
	private String questionId;

	/**邀请用户ID**varchar(64)**/
	private String userId;

    /**是否已读**int(1)**/
    private Integer isRead;



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

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
