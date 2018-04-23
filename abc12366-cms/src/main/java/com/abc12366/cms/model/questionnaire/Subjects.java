package com.abc12366.cms.model.questionnaire;

import java.io.Serializable;


/**
 * 题目表
 **/
@SuppressWarnings("serial")
public class Subjects implements Serializable {

    /****/
    private String id;

    /**
     * 问卷ID
     **/
    private String questionId;

    /**
     * 题目名称
     **/
    private String title;

    /**
     * 题目简单描述
     **/
    private String simpleDesc;

    /**
     * 是否必填
     **/
    private Integer isRequired;

    /**
     * 选项类型
     **/
    private String optionType;

    /**
     * 是否为题库
     **/
    private String isQuestion;

    /**
     * 页数
     **/
    private Integer pages;

    /**
     * 图片路径
     **/
    private String picPath;

    /**
     * 图片名字
     **/
    private String picName;

    /**
     * 选项随机顺序
     **/
    private Integer randomOrder;

    /**
     * 每行显示多个
     **/
    private Integer displayMultiple;

    /**
     * 最多可选
     **/
    private Integer mostOptional;

    /**
     * 题目编号
     **/
    private Integer number;

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimpleDesc() {
        return this.simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public Integer getIsRequired() {
        return this.isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public String getOptionType() {
        return this.optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getIsQuestion() {
        return this.isQuestion;
    }

    public void setIsQuestion(String isQuestion) {
        this.isQuestion = isQuestion;
    }

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPicPath() {
        return this.picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicName() {
        return this.picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public Integer getRandomOrder() {
        return this.randomOrder;
    }

    public void setRandomOrder(Integer randomOrder) {
        this.randomOrder = randomOrder;
    }

    public Integer getDisplayMultiple() {
        return this.displayMultiple;
    }

    public void setDisplayMultiple(Integer displayMultiple) {
        this.displayMultiple = displayMultiple;
    }

    public Integer getMostOptional() {
        return this.mostOptional;
    }

    public void setMostOptional(Integer mostOptional) {
        this.mostOptional = mostOptional;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
