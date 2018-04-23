package com.abc12366.uc.model.invoice.bo;

import java.io.Serializable;


/**
 *
 * 发票使用明细表
 *
 **/
@SuppressWarnings("serial")
public class InvoiceDetailBO implements Serializable {

	/****/
	private String id;

	/**发票号码**/
	private String invoiceNo;

	/**发票代码**/
	private String invoiceCode;

	/**发票性质：1.纸质发票 2.电子发票**/
	private String property;

	/**发票状态：0.未使用 1.分配中 2.已使用 3.已作废**/
	private String status;

	/****/
	private java.util.Date createTime;

	/****/
	private java.util.Date lastUpdate;

	/**备注**/
	private String remark;

	/**发票编号(FK)**/
	private String invoiceRepoId;

	/**收票地址**/
	private String pdfUrl;

	/**PDF下载地址**/
	private String spUrl;


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setInvoiceNo(String invoiceNo){
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceNo(){
		return this.invoiceNo;
	}

	public void setInvoiceCode(String invoiceCode){
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceCode(){
		return this.invoiceCode;
	}

	public void setProperty(String property){
		this.property = property;
	}

	public String getProperty(){
		return this.property;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setInvoiceRepoId(String invoiceRepoId){
		this.invoiceRepoId = invoiceRepoId;
	}

	public String getInvoiceRepoId(){
		return this.invoiceRepoId;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public String getSpUrl() {
		return spUrl;
	}

	public void setSpUrl(String spUrl) {
		this.spUrl = spUrl;
	}
}
