package com.abc12366.cms.model.questionnaire;

import java.io.Serializable;


/**
 * 答题记录表
 **/
@SuppressWarnings("serial")
public class AnswerLog implements Serializable {

    /****/
    private String id;

    /**
     * 问卷ID
     **/
    private String questionId;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 微信ID
     **/
    private String weixinId;

    /**
     * 接入终端
     **/
    private String accessTerminal;

    /**
     * IP地址
     **/
    private String ipAddress;

    /**
     * 答题开始时间
     **/
    private java.util.Date startTime;

    /**
     * 答题结束时间
     **/
    private java.util.Date endTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessTerminal() {
        return this.accessTerminal;
    }

    public void setAccessTerminal(String accessTerminal) {
        this.accessTerminal = accessTerminal;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public java.util.Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public AnswerLog setWeixinId(String weixinId) {
        this.weixinId = weixinId;
        return this;
    }
}
