package com.abc12366.bangbang.model.question;

/**
 * 
 * 邦手等级
 * 
 **/
public class QuestionFactionMemberLevel  {

	/**PK**/
	private String id;

	/**code**/
	private String code;

	/**帮手名称**/
	private String name;

	/**回答数**/
	private Integer answers;

	/**讨论数**/
	private Integer discussions;

	/**采纳数**/
	private Integer adoptions;

	/**状态**/
	private Boolean status;

	/****/
	private java.util.Date createTime;

	/****/
	private String createAdmin;

	/****/
	private java.util.Date updateTime;

	/****/
	private String updateAdmin;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setAnswers(Integer answers){
		this.answers = answers;
	}

	public Integer getAnswers(){
		return this.answers;
	}

	public void setDiscussions(Integer discussions){
		this.discussions = discussions;
	}

	public Integer getDiscussions(){
		return this.discussions;
	}

	public void setAdoptions(Integer adoptions){
		this.adoptions = adoptions;
	}

	public Integer getAdoptions(){
		return this.adoptions;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateAdmin(String createAdmin){
		this.createAdmin = createAdmin;
	}

	public String getCreateAdmin(){
		return this.createAdmin;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateAdmin(String updateAdmin){
		this.updateAdmin = updateAdmin;
	}

	public String getUpdateAdmin(){
		return this.updateAdmin;
	}

	public Boolean getStatus() {
		return status;
	}

	public QuestionFactionMemberLevel setStatus(Boolean status) {
		this.status = status;
		return this;
	}

	public String getCode() {
		return code;
	}

	public QuestionFactionMemberLevel setCode(String code) {
		this.code = code;
		return this;
	}
}
