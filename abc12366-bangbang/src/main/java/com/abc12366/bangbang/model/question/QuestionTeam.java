package com.abc12366.bangbang.model.question;

/**
 * 
 * 
 * 
 **/
public class QuestionTeam{

	/**PK**/
	private String id;

	/**分类**/
	private String categoryCode;

	/**宣言**/
	private String declaration;

	/**关键字**/
	private String keywords;

	/**团队图片**/
	private String image;

	/**创建用户id**/
	private String createUser;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date updateTime;

	/**状态**/
	private String status;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setCategoryCode(String categoryCode){
		this.categoryCode = categoryCode;
	}

	public String getCategoryCode(){
		return this.categoryCode;
	}

	public void setDeclaration(String declaration){
		this.declaration = declaration;
	}

	public String getDeclaration(){
		return this.declaration;
	}

	public void setKeywords(String keywords){
		this.keywords = keywords;
	}

	public String getKeywords(){
		return this.keywords;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return this.image;
	}

	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	public String getCreateUser(){
		return this.createUser;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

}
