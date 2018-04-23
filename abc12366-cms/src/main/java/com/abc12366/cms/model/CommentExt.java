package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS评论扩展表
 **/
@SuppressWarnings("serial")
public class CommentExt implements Serializable {

    /**
     * commentId**varchar(64)
     **/
    private String commentId;

    /**
     * IP地址**varchar(50)
     **/
    private String ip;

    /**
     * 评论内容**longtext
     **/
    private String text;

    /**
     * 回复内容**longtext
     **/
    private String reply;

    public String getCommentId() {
        return this.commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReply() {
        return this.reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}
