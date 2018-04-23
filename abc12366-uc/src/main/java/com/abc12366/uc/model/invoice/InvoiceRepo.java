package com.abc12366.uc.model.invoice;
import java.io.Serializable;


/**
 *
 * 发票仓库
 *
 **/
@SuppressWarnings("serial")
public class InvoiceRepo implements Serializable {

	/**发票编号**/
	private String id;

	/**发票种类代码，字典表ID**/
	private String invoiceTypeCode;

	/**发票代码**/
	private String invoiceCode;

	/**发票号码起**/
	private String invoiceNoStart;

	/**发票号码止**/
	private String invoiceNoEnd;

	/**发票性质：1.纸质发票 2.电子发票**/
	private String property;

	/**发票库存状态，0：可使用，1：已分发，2：已作废**/
	private String status;

	/**每本份数**/
	private Integer share;

	/**本数**/
	private Integer book;

	/**创建时间**/
	private java.util.Date createTime;

	/**最后修改时间**/
	private java.util.Date lastUpdate;

	/**创建人**/
	private String createUser;

	/**修改人**/
	private String updateUser;

	/**备注**/
	private String remark;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setInvoiceTypeCode(String invoiceTypeCode){
		this.invoiceTypeCode = invoiceTypeCode;
	}

	public String getInvoiceTypeCode(){
		return this.invoiceTypeCode;
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

	public void setProperty(String property){
		this.property = property;
	}

	public String getProperty(){
		return this.property;
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

	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	public String getCreateUser(){
		return this.createUser;
	}

	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser;
	}

	public String getUpdateUser(){
		return this.updateUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
