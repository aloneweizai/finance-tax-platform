package com.abc12366.uc.model.order.bo;

/**
 * 退货订单发票信息
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-11 5:05 PM
 * @since 1.0.0
 */
public class ExchangeOrderInvoiceBO {

    // 订单号
    private String orderNo;
    // 是否开票
    private Boolean isInvoice;
    private String invoiceNo;
    private String invoiceCode;
    private String name;
    private String content;
    private String type;

    // 发票性质
    private String property;
    private Double amount;
    private String nsrsbh;
    private String nsrmc;
    private String address;
    private String phone;
    private String bank;
    // 开票状态
    private String status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "ExchangeOrderInvoiceBO{" +
                "orderNo='" + orderNo + '\'' +
                ", isInvoice=" + isInvoice +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", property='" + property + '\'' +
                ", amount=" + amount +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", nsrmc='" + nsrmc + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", bank='" + bank + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
