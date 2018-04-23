package com.abc12366.uc.model.invoice;
import java.io.Serializable;


/**
 *
 * 发票审批记录表
 *
 **/
@SuppressWarnings("serial")
public class InvoiceApprovalLog implements Serializable {

	/**PK**/
	private String id;

	/**领用流水号**/
	private String useId;

	/**审批结果**/
	private String approvalResult;

	/**审批意见**/
	private String approvalOpinions;

	/**审批人**/
	private String approver;

	/**审批时间**/
	private java.util.Date approverTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setUseId(String useId){
		this.useId = useId;
	}

	public String getUseId(){
		return this.useId;
	}

	public void setApprovalResult(String approvalResult){
		this.approvalResult = approvalResult;
	}

	public String getApprovalResult(){
		return this.approvalResult;
	}

	public void setApprovalOpinions(String approvalOpinions){
		this.approvalOpinions = approvalOpinions;
	}

	public String getApprovalOpinions(){
		return this.approvalOpinions;
	}

	public void setApprover(String approver){
		this.approver = approver;
	}

	public String getApprover(){
		return this.approver;
	}

	public void setApproverTime(java.util.Date approverTime){
		this.approverTime = approverTime;
	}

	public java.util.Date getApproverTime(){
		return this.approverTime;
	}

}
