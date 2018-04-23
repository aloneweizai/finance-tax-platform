package com.abc12366.cms.model.questionnaire.bo;

import com.abc12366.cms.model.questionnaire.Option;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 题目表
 **/
@SuppressWarnings("serial")
public class SubjectsBO implements Serializable {

    private String id;
    @NotEmpty
    private String questionId;
    private String title;
    private String simpleDesc;
    private Integer isRequired;
    private String optionType;
    private String isQuestion;
    private Integer pages;
    private String picPath;
    private String picName;
    private Integer randomOrder;
    private Integer displayMultiple;
    private Integer mostOptional;
    @NotNull
    private Integer number;

    private List<Option> optionList = new ArrayList<Option>();

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

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
