package com.abc12366.cms.model.questionnaire.bo;

import java.io.Serializable;


/**
 * 奖品表
 **/
@SuppressWarnings("serial")
public class PrizeBO implements Serializable {

    /**
     * 奖品ID
     **/
    private String id;

    /**
     * 问卷ID
     **/
    private String questionId;

    /**
     * 奖品名称
     **/
    private String name;

    /**
     * 数量
     **/
    private Integer number;

    /**
     * 价格
     **/
    private double price;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double PrizeBO() {
        return this.price;
    }

}
