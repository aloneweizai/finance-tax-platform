package com.abc12366.cms.model.questionnaire.bo;

import java.io.Serializable;


/**
 * 问卷属性设置表
 **/
@SuppressWarnings("serial")
public class QuestionnaireParamBO implements Serializable {

    /**
     * 问卷ID
     **/
    private String questionId;

    /**
     * 是否显示问题编号，true：是；false：否
     **/
    private Integer isDisplayNumber;

    /**
     * 是否答题过程回退，true：是；false：否
     **/
    private Integer isBackspace;

    /**
     * 是否答题完成后显示结果，true：是；false：否
     **/
    private Integer isDisplayResults;

    /**
     * 问卷回收结束时间
     **/
    private java.util.Date recoveryEndTime;

    /**
     * 是否答题需要登录验证，true：是；false：否
     **/
    private Integer isLoginAuth;

    /**
     * 是否每个用户只能回答一次，true：是；false：否
     **/
    private Integer isAnswerOnce;

    /**
     * 是否限定回答用户范围，true：是；false：否
     **/
    private Integer isUserRange;

    /**
     * 是否回答提醒（有新回答时可获得短信通知），true：是；false：否
     **/
    private Integer isAnswerRemind;

    /**
     * 是否有奖设置，true：是；false：否
     **/
    private Integer isPrize;

    /**
     * 是否每个微信号只能回答一次，true：是；false：否
     **/
    private Integer isWxOnce;

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Integer getIsDisplayNumber() {
        return this.isDisplayNumber;
    }

    public void setIsDisplayNumber(Integer isDisplayNumber) {
        this.isDisplayNumber = isDisplayNumber;
    }

    public Integer getIsBackspace() {
        return this.isBackspace;
    }

    public void setIsBackspace(Integer isBackspace) {
        this.isBackspace = isBackspace;
    }

    public Integer getIsDisplayResults() {
        return this.isDisplayResults;
    }

    public void setIsDisplayResults(Integer isDisplayResults) {
        this.isDisplayResults = isDisplayResults;
    }

    public java.util.Date getRecoveryEndTime() {
        return this.recoveryEndTime;
    }

    public void setRecoveryEndTime(java.util.Date recoveryEndTime) {
        this.recoveryEndTime = recoveryEndTime;
    }

    public Integer getIsLoginAuth() {
        return this.isLoginAuth;
    }

    public void setIsLoginAuth(Integer isLoginAuth) {
        this.isLoginAuth = isLoginAuth;
    }

    public Integer getIsAnswerOnce() {
        return this.isAnswerOnce;
    }

    public void setIsAnswerOnce(Integer isAnswerOnce) {
        this.isAnswerOnce = isAnswerOnce;
    }

    public Integer getIsUserRange() {
        return this.isUserRange;
    }

    public void setIsUserRange(Integer isUserRange) {
        this.isUserRange = isUserRange;
    }

    public Integer getIsAnswerRemind() {
        return this.isAnswerRemind;
    }

    public void setIsAnswerRemind(Integer isAnswerRemind) {
        this.isAnswerRemind = isAnswerRemind;
    }

    public Integer getIsPrize() {
        return this.isPrize;
    }

    public void setIsPrize(Integer isPrize) {
        this.isPrize = isPrize;
    }

    public Integer getIsWxOnce() {
        return isWxOnce;
    }

    public QuestionnaireParamBO setIsWxOnce(Integer isWxOnce) {
        this.isWxOnce = isWxOnce;
        return this;
    }
}
