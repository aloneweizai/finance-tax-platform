package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 问题点赞表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionLike implements Serializable {

	/**点赞ID**varchar(64)**/
	private String likeId;

	/**问题或回复ID**varchar(64)**/
	private String id;

	/**用户ID**varchar(64)**/
	private String userId;

    /**被赞或被踩用户ID**varchar(64)**/
    private String toUserId;

    /**点赞类型**int**/
    private Integer likeType;

    /**点赞来源**int**/
    private Integer likeTarget;

	/**点赞时间**datetime**/
	private java.util.Date likeTime;

    /**问题ID**varchar(64)**/
    private String questionId;

    /**邦派ID**varchar(64)**/
    private String factionId;



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

    public Integer getLikeType() {
        return likeType;
    }

    public void setLikeType(Integer likeType) {
        this.likeType = likeType;
    }


    public Integer getLikeTarget() {
        return likeTarget;
    }

    public void setLikeTarget(Integer likeTarget) {
        this.likeTarget = likeTarget;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
}
