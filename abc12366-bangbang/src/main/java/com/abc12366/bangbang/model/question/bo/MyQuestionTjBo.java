package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;


/**
 * 
 * 我的帮帮统计信息
 * 
 **/
@SuppressWarnings("serial")
public class MyQuestionTjBo implements Serializable {

    /**提问次数**int(11)**/
    private Integer questionNum;

    /**点赞次数**int(11)**/
    private Integer likeNum;

    /**被点赞次数**int(11)**/
    private Integer quiltLikeNum;

    /**回复次数**int(11)**/
    private Integer answerNum;

	/**评论次数**int(11)**/
	private Integer commentNum;

    /**采纳次数**int(11)**/
    private Integer acceptNum;

    /**收藏数**int(11)**/
    private Integer collectNum;

    /**我的关注数量**int(11)**/
    private Integer attentionNum;

    /**我的粉丝数量**int(11)**/
    private Integer fansNum;

    /**我的粉丝角标**int(11)**/
    private Integer newFansNum;

    /**勋章数**int(11)**/
    private Integer medalNum;

    /**我的提问角标**int(11)**/
    private Integer newAnswerNum;

    /**邀我答角标**int(11)**/
    private Integer newInviteNum;

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(Integer acceptNum) {
        this.acceptNum = acceptNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getMedalNum() {
        return medalNum;
    }

    public void setMedalNum(Integer medalNum) {
        this.medalNum = medalNum;
    }

    public Integer getNewAnswerNum() {
        return newAnswerNum;
    }

    public void setNewAnswerNum(Integer newAnswerNum) {
        this.newAnswerNum = newAnswerNum;
    }

    public Integer getNewInviteNum() {
        return newInviteNum;
    }

    public void setNewInviteNum(Integer newInviteNum) {
        this.newInviteNum = newInviteNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public MyQuestionTjBo setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
        return this;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public MyQuestionTjBo setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    public Integer getAttentionNum() {
        return attentionNum;
    }

    public MyQuestionTjBo setAttentionNum(Integer attentionNum) {
        this.attentionNum = attentionNum;
        return this;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public MyQuestionTjBo setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
        return this;
    }

    public Integer getNewFansNum() {
        return newFansNum;
    }

    public MyQuestionTjBo setNewFansNum(Integer newFansNum) {
        this.newFansNum = newFansNum;
        return this;
    }

    public Integer getQuiltLikeNum() {
        return quiltLikeNum;
    }

    public void setQuiltLikeNum(Integer quiltLikeNum) {
        this.quiltLikeNum = quiltLikeNum;
    }
}
