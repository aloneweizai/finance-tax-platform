package com.abc12366.bangbang.model.question;

import java.util.Date;

/**
 * 
 * 
 * 
 **/
public class QuestionExpert {

	/**PK**/
	private String id;

	/**uc关联用户id**/
	private String userId;

	/**专家类型(财税大侠，税务大侠)**/
	private String type;

	/**工作年长**/
	private Integer yearWork;

	/**擅长领域**/
	private String goodField;

	/**专家介绍**/
	private String intro;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date updateTime;

	/**对应uc_admin用户**/
	private String createAdmin;

	private String updateAdmin;

	/**状态（0：无效，1：有效）**/
	private String status;


	public String getId() {
		return id;
	}

	public QuestionExpert setId(String id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public QuestionExpert setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getType() {
		return type;
	}

	public QuestionExpert setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getYearWork() {
		return yearWork;
	}

	public QuestionExpert setYearWork(Integer yearWork) {
		this.yearWork = yearWork;
		return this;
	}

	public String getGoodField() {
		return goodField;
	}

	public QuestionExpert setGoodField(String goodField) {
		this.goodField = goodField;
		return this;
	}

	public String getIntro() {
		return intro;
	}

	public QuestionExpert setIntro(String intro) {
		this.intro = intro;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public QuestionExpert setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public QuestionExpert setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getCreateAdmin() {
		return createAdmin;
	}

	public QuestionExpert setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
		return this;
	}

	public String getUpdateAdmin() {
		return updateAdmin;
	}

	public QuestionExpert setUpdateAdmin(String updateAdmin) {
		this.updateAdmin = updateAdmin;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public QuestionExpert setStatus(String status) {
		this.status = status;
		return this;
	}
}
