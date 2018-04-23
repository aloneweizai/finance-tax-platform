package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 邦派与分类关联表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionClassifyBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**分类代码(请传分类代码不是ID)**varchar(64)**/
	private String classifyId;

    /**分类名称**varchar(64)**/
    private String classifyName;



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

	public void setClassifyId(String classifyId){
		this.classifyId = classifyId;
	}

	public String getClassifyId(){
		return this.classifyId;
	}

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
