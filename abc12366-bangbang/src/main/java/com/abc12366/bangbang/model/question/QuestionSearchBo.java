package com.abc12366.bangbang.model.question;

/**
 * Created by stuy on 2017/11/9.
 */
public class QuestionSearchBo {

    /**
     * 用户编号
     */
    private String userid;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String detail;

    /**
     * 问题编号
     */
    private String questionid;

    /**
     * 问题回答数
     */
    private Integer answerNum;

    /**
     * 问题点赞数
     */
    private Integer likeNum;

    /**
     * 问题踩数
     */
    private Integer treadNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String userPicturePath;

    /**
     * 用户收藏数
     */
    private Integer collectNum;

    /**
     * 回答编号
     */
    private String answerId;

    /**
     * 回答简短内容
     */
    private String shortAnswer;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
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

    public Integer getTreadNum() {
        return treadNum;
    }

    public void setTreadNum(Integer treadNum) {
        this.treadNum = treadNum;
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

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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
}
