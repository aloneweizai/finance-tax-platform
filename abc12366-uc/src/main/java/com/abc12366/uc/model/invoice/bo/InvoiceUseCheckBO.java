package com.abc12366.uc.model.invoice.bo;
import java.io.Serializable;
import java.util.List;


/**
 *
 * 发票领用申请表
 *
 **/
@SuppressWarnings("serial")
public class InvoiceUseCheckBO implements Serializable {

	/****/
	private String id;

	/**申请人**/
	private String applyUser;

	/**申请时间**/
	private java.util.Date applyTime;

	/**签发状态，0：未分发，1：已分发，2：已签收**/
	private String issueStatus;

	/**审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿**/
	private String examineStatus;

	/**分发人**/
	private String distributeUser;

	/**分发时间**/
	private java.util.Date distributeTime;

	/**签收人**/
	private String signUser;

	/**签收时间**/
	private java.util.Date signTime;

	/**备注**/
	private String remark;

	/**审核意见**/
	private String checkOpinion;

	private List<InvoiceUseDetailBO> invoiceUseDetailBOList;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setApplyUser(String applyUser){
		this.applyUser = applyUser;
	}

	public String getApplyUser(){
		return this.applyUser;
	}

	public void setApplyTime(java.util.Date applyTime){
		this.applyTime = applyTime;
	}

	public java.util.Date getApplyTime(){
		return this.applyTime;
	}

	public void setIssueStatus(String issueStatus){
		this.issueStatus = issueStatus;
	}

	public String getIssueStatus(){
		return this.issueStatus;
	}

	public void setExamineStatus(String examineStatus){
		this.examineStatus = examineStatus;
	}

	public String getExamineStatus(){
		return this.examineStatus;
	}

	public void setDistributeUser(String distributeUser){
		this.distributeUser = distributeUser;
	}

	public String getDistributeUser(){
		return this.distributeUser;
	}

	public void setDistributeTime(java.util.Date distributeTime){
		this.distributeTime = distributeTime;
	}

	public java.util.Date getDistributeTime(){
		return this.distributeTime;
	}

	public void setSignUser(String signUser){
		this.signUser = signUser;
	}

	public String getSignUser(){
		return this.signUser;
	}

	public void setSignTime(java.util.Date signTime){
		this.signTime = signTime;
	}

	public java.util.Date getSignTime(){
		return this.signTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public List<InvoiceUseDetailBO> getInvoiceUseDetailBOList() {
		return invoiceUseDetailBOList;
	}

	public void setInvoiceUseDetailBOList(List<InvoiceUseDetailBO> invoiceUseDetailBOList) {
		this.invoiceUseDetailBOList = invoiceUseDetailBOList;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
}
