package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * CMS评论表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class CommentBo implements Serializable {

    /**
     * commentId**varchar(64)
     **/
    private String commentId;

    /**
     * 评论用户ID**varchar(64)
     **/
    private String commentUserId;

    /**
     * 回复用户ID**varchar(64)
     **/
    private String replyUserId;

    /**
     * 内容ID**varchar(64)
     **/
    private String contentId;

    /**
     * 标题
     **/
    private String title;

    /**
     * 站点ID**varchar(64)
     **/
    private String siteId;

    /**
     * 评论时间**datetime
     **/
    private java.util.Date createTime;

    /**
     * 回复时间**datetime
     **/
    private java.util.Date replyTime;

    /**
     * 支持数**smallint(6)
     **/
    private Integer ups;

    /**
     * 反对数**smallint(6)
     **/
    private Integer downs;

    /**
     * 是否推荐**tinyint(1)
     **/
    private Integer isRecommend;

    /**
     * 是否审核**tinyint(1)
     **/
    private Integer isChecked;

    /**
     * 评分**int(11)
     **/
    private Integer score;

    /**
     * 父级评论**int(11)
     **/
    private String parentId;

    /**
     * 回复数**int(11)
     **/
    private Integer replyCount;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getDowns() {
        return downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
}
