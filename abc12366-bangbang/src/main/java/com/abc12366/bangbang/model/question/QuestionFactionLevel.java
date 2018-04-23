package com.abc12366.bangbang.model.question;


/**
 * 
 * 帮派等级
 * 
 **/
public class QuestionFactionLevel  {

	/**PK**/
	private String id;

	/**帮派等级编码**/
	private String code;

	/**帮派等级名称**/
	private String name;

	/**帮派头像**/
	private String image;

	/**荣誉值**/
	private Integer honorValue;

	/**人数限制**/
	private Integer peopleLimit;

	/**任务限制**/
	private Integer taskLimit;

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

	public void setHonorValue(Integer honorValue){
		this.honorValue = honorValue;
	}

	public Integer getHonorValue(){
		return this.honorValue;
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

	public String getImage() {
		return image;
	}

	public QuestionFactionLevel setImage(String image) {
		this.image = image;
		return this;
	}

	public Integer getPeopleLimit() {
		return peopleLimit;
	}

	public QuestionFactionLevel setPeopleLimit(Integer peopleLimit) {
		this.peopleLimit = peopleLimit;
		return this;
	}

	public Integer getTaskLimit() {
		return taskLimit;
	}

	public QuestionFactionLevel setTaskLimit(Integer taskLimit) {
		this.taskLimit = taskLimit;
		return this;
	}

	public Boolean getStatus() {
		return status;
	}

	public QuestionFactionLevel setStatus(Boolean status) {
		this.status = status;
		return this;
	}

	public String getCode() {
		return code;
	}

	public QuestionFactionLevel setCode(String code) {
		this.code = code;
		return this;
	}
}
