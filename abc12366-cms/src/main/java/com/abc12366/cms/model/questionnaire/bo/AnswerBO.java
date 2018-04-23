package com.abc12366.cms.model.questionnaire.bo;

import java.io.Serializable;


/**
 * 答题表
 **/
@SuppressWarnings("serial")
public class AnswerBO implements Serializable {

    /**
     * 答题记录ID
     **/
    private String answerLogId;

    /**
     * 题目ID
     **/
    private String subjectsId;

    /**
     * 答案内容
     **/
    private String content;

    public String getAnswerLogId() {
        return this.answerLogId;
    }

    public void setAnswerLogId(String answerLogId) {
        this.answerLogId = answerLogId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }
}
