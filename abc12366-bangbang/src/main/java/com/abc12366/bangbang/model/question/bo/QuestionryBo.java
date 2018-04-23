package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 问题表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionryBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**问题**varchar(300)**/
	private String title;

    /**问题描述**/
    private String detail;

	/**创建时间**datetime**/
	private Date createTime;

	/**悬赏积分**int(11)**/
	private Integer points;

	/**评论次数**int(11)**/
	private Integer commentNum;

    /**回答次数**int(11)**/
    private Integer answerNum;

    /**用户昵称**varchar(64)**/
    private String nickname;

    /**回答ID**varchar(64)**/
    private String answerId;

    /**用户图片**/
    private String userPicturePath;

    /**简短回答内容**varchar(300)**/
    private String shortAnswer;

    /**浏览数**/
    private String browseNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public String getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(String browseNum) {
        this.browseNum = browseNum;
    }
}
