package com.abc12366.bangbang.model.question.bo;

import com.abc12366.bangbang.model.question.QuestionTag;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 问题打标签
 * 
 **/
@SuppressWarnings("serial")
public class QuestionTagListBo implements Serializable {

    /**问题ID**varchar(64)**/
    private String questionId;

    /**标签**/
	private List<QuestionTag> tagList;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<QuestionTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<QuestionTag> tagList) {
        this.tagList = tagList;
    }
}
