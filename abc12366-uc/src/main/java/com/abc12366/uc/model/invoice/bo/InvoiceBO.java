package com.abc12366.uc.model.invoice.bo;

import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.invoice.InvoiceLog;
import com.abc12366.uc.model.order.bo.DeliveryMethodBO;
import com.abc12366.uc.model.order.bo.OrderBO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


/**
 * 发票信息
 **/
@SuppressWarnings("serial")
public class InvoiceBO implements Serializable {

	private String id;
	private String userId;
	@Size(min = 2, max = 20)
	private String username;
	private String invoiceNo;
	private String invoiceCode;

	@NotEmpty
	@Size(max = 1)
	private String name;
	private String content;
	/**内容详情**/
	private String contentDetail;
	private String compName;
	private Double amount;

	private String type;

	@NotEmpty
	@Size(max = 1)
	private String property;
	private String status;
	private java.util.Date createTime;
	private java.util.Date lastUpdate;

	@Size(min = 0,max = 32)
	private String nsrsbh;

	@Size(min = 0,max = 300)
	private String nsrmc;

	@Size(min = 0,max = 200)
	private String address;

	@Size(min = 0,max = 20)
	private String phone;

	@Size(min = 0,max = 100)
	private String bank;

	@Size(min = 1,max = 64)
	private String addressId;
	private String deliveryMethod;
	private Integer isShipping;
	private Integer isFreeShipping;
	private Double deliveryFee;
	private Integer isInsured;
	private Double InsuredFee;
	private String payMethod;
	/**
	 * 收货人
	 **/
	private String consignee;

	/**联系电话**/
	private String contactNumber;

	/**收件地址**/
	private String shippingAddress;

	private List<OrderBO> orderBOList;

	private String[] orderNos;
	private java.util.Date startTime;
	private java.util.Date endTime;
//	private UserAddressBO userAddressBO;
	private DeliveryMethodBO deliveryMethodBO;

	private InvoiceDetail invoiceDetail;
	/**
	 * 是否已开发票，true：是，false：否
	 **/
	private Boolean isInvoice;

	private InvoiceLog invoiceLog;

	/**
	 * 是否同意开票
	 **/
	private Boolean isBilling;

	/**
	 * 运单号
	 */
	private String waybillNum;

	private String remark;

    /**
     * 邮箱
     */
    private String email;

	private String expressCompId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(java.util.Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNsrsbh() {
		return this.nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return this.nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public Integer getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(Integer isShipping) {
		this.isShipping = isShipping;
	}

	public Integer getIsFreeShipping() {
		return isFreeShipping;
	}

	public void setIsFreeShipping(Integer isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Integer getIsInsured() {
		return isInsured;
	}

	public void setIsInsured(Integer isInsured) {
		this.isInsured = isInsured;
	}

	public Double getInsuredFee() {
		return InsuredFee;
	}

	public void setInsuredFee(Double insuredFee) {
		InsuredFee = insuredFee;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String[] getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String[] orderNos) {
		this.orderNos = orderNos;
	}

	public List<OrderBO> getOrderBOList() {
		return orderBOList;
	}

	public void setOrderBOList(List<OrderBO> orderBOList) {
		this.orderBOList = orderBOList;
	}

	public DeliveryMethodBO getDeliveryMethodBO() {
		return deliveryMethodBO;
	}

	public void setDeliveryMethodBO(DeliveryMethodBO deliveryMethodBO) {
		this.deliveryMethodBO = deliveryMethodBO;
	}

	public Boolean getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Boolean isInvoice) {
		this.isInvoice = isInvoice;
	}

	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public InvoiceLog getInvoiceLog() {
		return invoiceLog;
	}

	public void setInvoiceLog(InvoiceLog invoiceLog) {
		this.invoiceLog = invoiceLog;
	}

	public Boolean getIsBilling() {
		return isBilling;
	}

	public void setIsBilling(Boolean isBilling) {
		this.isBilling = isBilling;
	}

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContentDetail() {
		return contentDetail;
	}

	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getExpressCompId() {
		return expressCompId;
	}

	public void setExpressCompId(String expressCompId) {
		this.expressCompId = expressCompId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
