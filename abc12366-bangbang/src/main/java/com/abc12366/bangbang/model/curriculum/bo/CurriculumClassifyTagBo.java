package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;


/**
 * 
 * 课程分类与标签关联表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumClassifyTagBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**问题分类ID**varchar(64)**/
	private String classifyId;

	/**标签ID**varchar(64)**/
	private String tagId;

    /**标签名称**varchar(100)**/
    private String tagName;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setClassifyId(String classifyId){
		this.classifyId = classifyId;
	}

	public String getClassifyId(){
		return this.classifyId;
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
}
