package com.abc12366.cms.model.questionnaire.bo;

import com.abc12366.cms.model.questionnaire.Prize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 抽奖设置表
 **/
@SuppressWarnings("serial")
public class PrizeSetBO implements Serializable {

    /**
     * 问卷ID
     **/
    private String questionId;

    /**
     * 截止时间
     **/
    private java.util.Date endTime;

    /**
     * 发奖人
     **/
    private String awardUser;

    /**
     * 联系方式
     **/
    private String contactWay;

    /**
     * 发奖方式
     **/
    private String awardWay;

    /**
     * 中奖概率
     **/
    private double prizeRate;

    private List<Prize> prizeList = new ArrayList<Prize>();

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public String getAwardUser() {
        return this.awardUser;
    }

    public void setAwardUser(String awardUser) {
        this.awardUser = awardUser;
    }

    public String getContactWay() {
        return this.contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getAwardWay() {
        return this.awardWay;
    }

    public void setAwardWay(String awardWay) {
        this.awardWay = awardWay;
    }

    public void setPrizeRate(double prizeRate) {
        this.prizeRate = prizeRate;
    }

    public double PrizeSetBO() {
        return this.prizeRate;
    }

    public List<Prize> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<Prize> prizeList) {
        this.prizeList = prizeList;
    }
}
