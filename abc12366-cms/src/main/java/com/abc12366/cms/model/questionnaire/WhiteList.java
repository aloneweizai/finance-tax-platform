package com.abc12366.cms.model.questionnaire;

import java.io.Serializable;


/**
 * 问卷白名单表
 **/
@SuppressWarnings("serial")
public class WhiteList implements Serializable {

    private String id;
    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 问卷ID
     **/
    private String questionId;

    /**
     * 录入时间
     **/
    private java.util.Date inputTime;

    /**
     * 有效标志
     **/
    private Integer validSign;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public java.util.Date getInputTime() {
        return this.inputTime;
    }

    public void setInputTime(java.util.Date inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getValidSign() {
        return this.validSign;
    }

    public void setValidSign(Integer validSign) {
        this.validSign = validSign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
