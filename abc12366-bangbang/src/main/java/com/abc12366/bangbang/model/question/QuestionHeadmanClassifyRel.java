package com.abc12366.bangbang.model.question;


/**
 * 
 * 掌门人分类关联表
 * 
 **/
public class QuestionHeadmanClassifyRel  {

	/**PK**/
	private String id;

	/**bb_question_headman.id**/
	private String headmanId;

	/**bb_question_classify.id**/
	private String classifyId;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setHeadmanId(String headmanId){
		this.headmanId = headmanId;
	}

	public String getHeadmanId(){
		return this.headmanId;
	}

	public void setClassifyId(String classifyId){
		this.classifyId = classifyId;
	}

	public String getClassifyId(){
		return this.classifyId;
	}

}
