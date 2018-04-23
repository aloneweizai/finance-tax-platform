package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;


/**
 * 
 * 帮邦用户表
 * 
 **/
@SuppressWarnings("serial")
public class QcTitleBo implements Serializable {

	/**题目ID**varchar(64)**/
	private String questionId;

	/**答案ID**varchar(64)**/
	private String id;

    /**题目标题**/
    private String title;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
