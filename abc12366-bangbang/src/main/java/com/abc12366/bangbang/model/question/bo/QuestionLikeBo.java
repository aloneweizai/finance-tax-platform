package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 问题点赞表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionLikeBo implements Serializable {

	/**点赞ID**varchar(64)**/
	private String likeId;

	/**问题或回复ID**varchar(64)**/
	private String id;

	/**用户ID**varchar(64)**/
	private String userId;

	/**点赞时间**datetime**/
	private java.util.Date likeTime;

    /**问题ID**varchar(64)**/
    private String questionId;



	public void setLikeId(String likeId){
		this.likeId = likeId;
	}

	public String getLikeId(){
		return this.likeId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setLikeTime(java.util.Date likeTime){
		this.likeTime = likeTime;
	}

	public java.util.Date getLikeTime(){
		return this.likeTime;
	}


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
