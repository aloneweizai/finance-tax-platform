package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 问题与标签关联表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionTagBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**问题ID**varchar(64)**/
	private String questionId;

	/**标签ID**varchar(64)**/
	private String tagId;

    /**标签名称**varchar(100)**/
    private String tagName;

    /**分类代码**varchar(100)**/
    private String classifyCode;

    /**父分类代码**varchar(100)**/
    private String parentCode;


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return this.questionId;
	}

	public void setTagId(String tagId){
		this.tagId = tagId;
	}

	public String getTagId(){
		return this.tagId;
	}

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
