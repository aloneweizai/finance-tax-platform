package com.abc12366.bangbang.model.question.bo;

import com.abc12366.bangbang.model.question.QuestionInvite;
import com.abc12366.bangbang.model.question.QuestionTag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 问题表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionBo implements Serializable {

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

    /**标签**/
	private List<QuestionTag> tagList;

    /**问题邀请用户表**/
    private List<QuestionInvite> inviteList;

    /**标签**varchar(1000)**/
    private String tag;

    /**ip**varchar(45)**/
    private String ip;

    /**回复次数**int(11)**/
    private Integer answerNum;

    /**收藏次数**int(11)**/
    private Integer collectNum;

    /**点赞次数**int(11)**/
    private Integer likeNum;

    /**踩次数**int(11)**/
    private Integer trampleNum;

	/**评论次数**int(11)**/
	private Integer commentNum;

    /**被举报次数**int(11)**/
    private Integer reportNum;

    /****varchar(64)**/
    private String factionId;

    /**用户昵称**varchar(64)**/
    private String nickname;

    /**用户图片**/
    private String userPicturePath;

    /**回复或者评论内容**/
    private String answer;

    /**回复或者评论内容Id**/
    private String answerId;

    /**简短回答内容**varchar(300)**/
    private String shortAnswer;

    /**回答图片**varchar(300)**/
    private String answerImage;


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

    public List<QuestionTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<QuestionTag> tagList) {
        this.tagList = tagList;
    }

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public QuestionBo setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
		return this;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public QuestionBo setRecommendTime(Date recommendTime) {
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

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

	public Integer getCommentNum() {
		return commentNum;
	}

	public QuestionBo setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
		return this;
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

    public List<QuestionInvite> getInviteList() {
        return inviteList;
    }

    public void setInviteList(List<QuestionInvite> inviteList) {
        this.inviteList = inviteList;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getTrampleNum() {
        return trampleNum;
    }

    public void setTrampleNum(Integer trampleNum) {
        this.trampleNum = trampleNum;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
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
}
