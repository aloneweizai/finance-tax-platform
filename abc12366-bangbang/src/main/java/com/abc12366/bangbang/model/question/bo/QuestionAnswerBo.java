package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 问题回复表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionAnswerBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**问题ID**varchar(64)**/
	private String questionId;

    /**问题标题**varchar(64)**/
    private String questionTitle;

	/**用户ID**varchar(64)**/
	private String userId;

    /**用户昵称**varchar(64)**/
    private String nickname;

    /**用户图片**/
    private String userPicturePath;

	/**回答内容**varchar(4000)**/
	private String answer;

    /**简短回答内容**varchar(300)**/
    private String shortAnswer;

    /**回答图片**varchar(300)**/
    private String answerImage;

	/**状态**varchar(20)**/
	private String status;

	/****datetime**/
	private java.util.Date createTime;

	/****datetime**/
	private java.util.Date lastUpdate;

	/**是否采纳：1/0**tinyint(4)**/
	private Integer isAccept;

    /**ip**varchar(45)**/
    private String ip;

    /**问题浏览数**int(11)**/
    private Integer browseNum;

    /**回复次数**int(11)**/
    private Integer answerNum;

    /**收藏次数**int(11)**/
    private Integer collectNum;

    /**评论次数**int(11)**/
    private Integer commentNum;

    /**点赞次数**int(11)**/
    private Integer likeNum;

    /**踩次数**int(11)**/
    private Integer trampleNum;

    /**被举报次数**int(11)**/
    private Integer reportNum;

    /**是否已点赞：1/0**tinyint(4)**/
    private Integer isLike;

    /**是否已踩：1/0**tinyint(4)**/
    private Integer isTrample;

    /**邦派ID**/
    private String factionId;

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

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return this.answer;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

	public void setIsAccept(Integer isAccept){
		this.isAccept = isAccept;
	}

	public Integer getIsAccept(){
		return this.isAccept;
	}

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public Integer getTrampleNum() {
        return trampleNum;
    }

    public void setTrampleNum(Integer trampleNum) {
        this.trampleNum = trampleNum;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public String getAnswerImage() {
        return answerImage;
    }

    public void setAnswerImage(String answerImage) {
        this.answerImage = answerImage;
    }

    public Integer getIsTrample() {
        return isTrample;
    }

    public void setIsTrample(Integer isTrample) {
        this.isTrample = isTrample;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
