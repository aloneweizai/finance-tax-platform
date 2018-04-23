package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 邦派与标签关联表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionTagBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**标签ID**varchar(64)**/
	private String tagId;

    /**标签名称**varchar(64)**/
    private String tagName;




	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setFactionId(String factionId){
		this.factionId = factionId;
	}

	public String getFactionId(){
		return this.factionId;
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
