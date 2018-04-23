package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 帮邦动态表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionDtBo implements Serializable {

	/**动态类型：1、提出问题，2、回答了问题，3、评论了回答，5、点赞了回答，6、写秘籍，7、评论了秘籍，9、点赞了秘籍**varchar(64)**/
	private String qlogType;

	/**动态时间**varchar(64)**/
	private Date createTime;

    /**问题状态**varchar(20)**/
    private String status;

    /**用户ID**varchar(300)**/
    private String userId;

    /**用户昵称**varchar(64)**/
    private String nickname;

    /**用户图片**/
    private String userPicturePath;

    /**标题ID**/
    private String titleId;

    /**标题**/
    private String title;

    /**回答ID**/
    private String answerId;

	/**简短回答**/
	private String shortAnswer;

    /**简短回答**/
    private String commentTxt;

    /**回答图片**/
    private String answerImage;

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

    public String getQlogType() {
        return qlogType;
    }

    public void setQlogType(String qlogType) {
        this.qlogType = qlogType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
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

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getTrampleNum() {
        return trampleNum;
    }

    public void setTrampleNum(Integer trampleNum) {
        this.trampleNum = trampleNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }
}
