package com.abc12366.uc.model.invoice;
import java.io.Serializable;


/**
 *
 * 发票分发信息表
 *
 **/
@SuppressWarnings("serial")
public class InvoiceDistribute implements Serializable {

	/**发票分发流水号**/
	private String id;

	/**发票编号(FK)**/
	private String invoiceRepoId;

	/**发票代码**/
	private String invoiceCode;

	/**发票号码起**/
	private String invoiceNoStart;

	/**发票号码止**/
	private String invoiceNoEnd;

	/****/
	private String status;

	/**发票本数**/
	private Integer share;

	/**发票本数**/
	private Integer book;

	/****/
	private String remark;

	/****/
	private String useId;

	/**发票种类代码，字典表ID**/
	private String invoiceTypeCode;

	/**分发人**/
	private String distributeUser;

	/**分发时间**/
	private java.util.Date distributeTime;

	/**签收人**/
	private String signUser;

	/**签收时间**/
	private java.util.Date signTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setInvoiceRepoId(String invoiceRepoId){
		this.invoiceRepoId = invoiceRepoId;
	}

	public String getInvoiceRepoId(){
		return this.invoiceRepoId;
	}

	public void setInvoiceCode(String invoiceCode){
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceCode(){
		return this.invoiceCode;
	}

	public void setInvoiceNoStart(String invoiceNoStart){
		this.invoiceNoStart = invoiceNoStart;
	}

	public String getInvoiceNoStart(){
		return this.invoiceNoStart;
	}

	public void setInvoiceNoEnd(String invoiceNoEnd){
		this.invoiceNoEnd = invoiceNoEnd;
	}

	public String getInvoiceNoEnd(){
		return this.invoiceNoEnd;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setShare(Integer share){
		this.share = share;
	}

	public Integer getShare(){
		return this.share;
	}

	public void setBook(Integer book){
		this.book = book;
	}

	public Integer getBook(){
		return this.book;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setUseId(String useId){
		this.useId = useId;
	}

	public String getUseId(){
		return this.useId;
	}

	public void setInvoiceTypeCode(String invoiceTypeCode){
		this.invoiceTypeCode = invoiceTypeCode;
	}

	public String getInvoiceTypeCode(){
		return this.invoiceTypeCode;
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

}
