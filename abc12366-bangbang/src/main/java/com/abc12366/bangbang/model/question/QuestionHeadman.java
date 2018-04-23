package com.abc12366.bangbang.model.question;


/**
 * 
 * 帮邦掌门人
 * 
 **/
public class QuestionHeadman  {

	/**PK**/
	private String id;

	/**掌门人名称**/
	private String name;

	/**掌门人电话**/
	private String phone;

    /**自我介绍**/
    private String introduce;

	/**申请理由**/
	private String applyReason;

	/**申请时间**/
	private java.util.Date createTime;

	/**掌门人登录用户uc_user id**/
	private String userId;

	/**掌门人负责的分类代码**/
	private String classifyCode;

	/**掌门人负责的分类名称**/
	private String classifyName;

	/**掌门人所属帮派id**/
	private String factionId;

	/**状态（apply:申请,success:通过,refuse:拒绝）**/
	private String status;

	/**修改时间**/
	private java.util.Date updateTime;

	/**后台操作人**/
	private String updateAdmin;

	/**备注**/
	private String remark;

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

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setApplyReason(String applyReason){
		this.applyReason = applyReason;
	}

	public String getApplyReason(){
		return this.applyReason;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public String getFactionId() {
		return factionId;
	}

	public String getClassifyCode() {
		return classifyCode;
	}

	public QuestionHeadman setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
		return this;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public QuestionHeadman setClassifyName(String classifyName) {
		this.classifyName = classifyName;
		return this;
	}

	public QuestionHeadman setFactionId(String factionId) {
		this.factionId = factionId;
		return this;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
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

	public String getRemark() {
		return remark;
	}

	public QuestionHeadman setRemark(String remark) {
		this.remark = remark;
		return this;
	}
}
