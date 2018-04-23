package com.abc12366.bangbang.model.question;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * 
 * 
 * 
 **/
public class QuestionTipOff{

	/**PK**/
	private String id;

	/**举报原因**/
	private String reason;

	/**举报人ip**/
	private String createIP;

	/**举报人id**/
	private String createUser;

	/**举报时间**/
	private java.util.Date createTime;

	/*问题id*/
	private String questionId;

	/**来源id**/
	private String sourceId;

	/**来源类型（question：提问，answer：回答，comment：评论）**/
	private String sourceType;

	/**审核中auditing，审核通过approved，拒绝refuse**/
	private String status;

	/**拒绝理由**/
	@NotEmpty
	@NotBlank
	@Size(min = 2, max = 150)
	private String refuseReason;

	/**奖励积分**/
	private Integer rewardsPoints;

	/**审核人id**/
	private String updateAdmin;

	/**审核时间**/
	private java.util.Date updateTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return this.reason;
	}

	public String getCreateIP() {
		return createIP;
	}

	public QuestionTipOff setCreateIP(String createIP) {
		this.createIP = createIP;
		return this;
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

	public void setSourceId(String sourceId){
		this.sourceId = sourceId;
	}

	public String getSourceId(){
		return this.sourceId;
	}

	public void setSourceType(String sourceType){
		this.sourceType = sourceType;
	}

	public String getSourceType(){
		return this.sourceType;
	}

	public void setUpdateAdmin(String updateAdmin){
		this.updateAdmin = updateAdmin;
	}

	public String getUpdateAdmin(){
		return this.updateAdmin;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public String getQuestionId() {
		return questionId;
	}

	public QuestionTipOff setQuestionId(String questionId) {
		this.questionId = questionId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public QuestionTipOff setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public QuestionTipOff setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
		return this;
	}

	public Integer getRewardsPoints() {
		return rewardsPoints;
	}

	public QuestionTipOff setRewardsPoints(Integer rewardsPoints) {
		this.rewardsPoints = rewardsPoints;
		return this;
	}
}
