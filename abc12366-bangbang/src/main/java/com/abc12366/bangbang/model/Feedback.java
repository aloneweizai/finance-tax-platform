package com.abc12366.bangbang.model;


/**
 * 
 * 意见反馈表
 * 
 **/
public class Feedback{

	/**主键**/
	private String id;

	/**来源类型 	财税网：TAX  财务专家：TAX_EXPERT**/
	private String sourceType;

	/**反馈用户**/
	private String feedbackUserId;

	/**反馈用户名**/
	private String feedbackUserName;

	/**反馈类型**/
	private String feedbackType;

	/**反馈时间**/
	private java.util.Date feedbackTime;

	/**意见描述**/
	private String opinionDesc;

	/**联系电话**/
	private String contactNumber;

	/** 该条反馈的回复信息 **/
	private String replyMsg;


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setSourceType(String sourceType){
		this.sourceType = sourceType;
	}

	public String getSourceType(){
		return this.sourceType;
	}

	public void setFeedbackUserId(String feedbackUserId){
		this.feedbackUserId = feedbackUserId;
	}

	public String getFeedbackUserId(){
		return this.feedbackUserId;
	}

	public String getFeedbackUserName() {
		return feedbackUserName;
	}

	public void setFeedbackUserName(String feedbackUserName) {
		this.feedbackUserName = feedbackUserName;
	}

	public void setFeedbackType(String feedbackType){
		this.feedbackType = feedbackType;
	}

	public String getFeedbackType(){
		return this.feedbackType;
	}

	public void setFeedbackTime(java.util.Date feedbackTime){
		this.feedbackTime = feedbackTime;
	}

	public java.util.Date getFeedbackTime(){
		return this.feedbackTime;
	}

	public void setOpinionDesc(String opinionDesc){
		this.opinionDesc = opinionDesc;
	}

	public String getOpinionDesc(){
		return this.opinionDesc;
	}

	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}

	public String getContactNumber(){
		return this.contactNumber;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public Feedback setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
		return this;
	}
}
