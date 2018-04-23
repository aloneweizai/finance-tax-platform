package com.abc12366.bangbang.model.question;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 问题表
 * 
 **/
@SuppressWarnings("serial")
public class Question implements Serializable {

	/****varchar(64)**/
	private String id;

	/**用户ID**varchar(64)**/
	private String userId;

	/**问题**varchar(300)**/
	private String title;

	/**问题详情**longtext**/
	private String detail;

	/**问题状态**varchar(20)**/
	private String status;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date lastUpdate;

	/**悬赏积分**int(11)**/
	private Integer points;

	/**是否解决：0/1**tinyint(4)**/
	private Integer isSolve;

	/**问题分类**varchar(30)**/
	private String classifyCode;

	/**浏览量**int(11)**/
	private Integer browseNum;

	/**是否推荐**/
	private Boolean isRecommend;

	/**推荐创建时间**/
	private java.util.Date recommendTime;

    /**标签**varchar(1000)**/
    private String tag;

    /**ip**varchar(45)**/
    private String ip;

    /****varchar(64)**/
    private String factionId;

    /**回复数**int(11)**/
    private Integer answerNum;

    /**收藏次数**int(11)**/
    private Integer collectNum;

    /**被举报次数**int(11)**/
    private Integer reportNum;

    /**用户昵称**varchar(64)**/
    private String nickname;

    /**用户图片**/
    private String userPicturePath;


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

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
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

	public void setPoints(Integer points){
		this.points = points;
	}

	public Integer getPoints(){
		return this.points;
	}

	public void setIsSolve(Integer isSolve){
		this.isSolve = isSolve;
	}

	public Integer getIsSolve(){
		return this.isSolve;
	}

	public void setClassifyCode(String classifyCode){
		this.classifyCode = classifyCode;
	}

	public String getClassifyCode(){
		return this.classifyCode;
	}

	public void setBrowseNum(Integer browseNum){
		this.browseNum = browseNum;
	}

	public Integer getBrowseNum(){
		return this.browseNum;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public Question setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
		return this;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public Question setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
		return this;
	}

	public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
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

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }
}
