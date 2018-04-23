package com.abc12366.bangbang.model.question.bo;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/11/10 19:23
 */
public class RecommendBo {

    private String id;

    private String title;

    private java.util.Date createTime;

    private Boolean isRecommend;

    /**点赞次数**int(11)**/
    private Integer likeNum;

    /**回复次数**int(11)**/
    private Integer answerNum;

    /**评论次数**int(11)**/
    private Integer commentNum;

    /*question, cheats*/
    private String type;


    public String getId() {
        return id;
    }

    public RecommendBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecommendBo setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RecommendBo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public RecommendBo setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
        return this;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public RecommendBo setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
        return this;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public RecommendBo setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
        return this;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public RecommendBo setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public String getType() {
        return type;
    }

    public RecommendBo setType(String type) {
        this.type = type;
        return this;
    }
}
