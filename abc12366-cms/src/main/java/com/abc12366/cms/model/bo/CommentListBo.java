package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * CMS评论列表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class CommentListBo implements Serializable {
    /****/
    private String commentId;

    /**
     * 评论内容
     **/
    private String text;

    /**
     * 回复内容
     **/
    private String reply;

    /**
     * 标题
     **/
    private String title;

    /**
     * IP地址
     **/
    private String ip;

    /**
     * 评论时间
     **/
    private java.util.Date createTime;

    /**
     * 评论用户ID
     **/
    private Integer commentUserId;

    /**
     * 是否推荐**tinyint(1)
     **/
    private Integer isRecommend;

    /**
     * 是否审核**tinyint(1)
     **/
    private Integer isChecked;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
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
}
