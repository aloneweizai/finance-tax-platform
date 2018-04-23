package com.abc12366.bangbang.model.question.bo;
import com.abc12366.bangbang.model.question.QuestionClassifyTag;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 问题分类表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionClassifyBo implements Serializable {

	/**问题分类ID**varchar(64)**/
	private String classifyId;

	/**父分类代码**varchar(64)**/
	private String parentCode;

	/**是否显示**tinyint(1)**/
	private Integer isDisplay;

	/**分类代码**varchar(100)**/
	private String classifyCode;

	/**分类名称**varchar(100)**/
	private String classifyName;

	/*分类父名称*/
	private String parentName;

	/**排列顺序**int**/
	private Integer priority;

	private List<QuestionClassifyTag> tagList;



	public void setClassifyId(String classifyId){
		this.classifyId = classifyId;
	}

	public String getClassifyId(){
		return this.classifyId;
	}

	public void setParentCode(String parentCode){
		this.parentCode = parentCode;
	}

	public String getParentCode(){
		return this.parentCode;
	}

	public void setIsDisplay(Integer isDisplay){
		this.isDisplay = isDisplay;
	}

	public Integer getIsDisplay(){
		return this.isDisplay;
	}

	public void setClassifyCode(String classifyCode){
		this.classifyCode = classifyCode;
	}

	public String getClassifyCode(){
		return this.classifyCode;
	}

	public void setClassifyName(String classifyName){
		this.classifyName = classifyName;
	}

	public String getClassifyName(){
		return this.classifyName;
	}

    public List<QuestionClassifyTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<QuestionClassifyTag> tagList) {
        this.tagList = tagList;
    }

	public String getParentName() {
		return parentName;
	}

	public QuestionClassifyBo setParentName(String parentName) {
		this.parentName = parentName;
		return this;
	}

	public Integer getPriority() {
		return priority;
	}

	public QuestionClassifyBo setPriority(Integer priority) {
		this.priority = priority;
		return this;
	}
}
