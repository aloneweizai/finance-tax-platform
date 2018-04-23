package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 秘籍与标签关联表
 * 
 **/
@SuppressWarnings("serial")
public class CheatsTagBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**秘籍ID**varchar(64)**/
	private String cheatsId;

	/**标签ID**varchar(64)**/
	private String tagId;

    /**标签名称**/
    private String tagName;

    /**分类代码**/
    private String classifyCode;

    /**父分类代码**/
    private String parentCode;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setCheatsId(String cheatsId){
		this.cheatsId = cheatsId;
	}

	public String getCheatsId(){
		return this.cheatsId;
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
