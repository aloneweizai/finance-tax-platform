package com.abc12366.uc.model.invoice.bo;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


/**
 * 发票信息
 **/
@SuppressWarnings("serial")
public class InvoiceExpressExcel implements Serializable {

    /**
     * 用户订单号
     **/
    private String invoiceOrderNo;

    /**
     * 收件公司
     **/
    private String receivingCompany;

    /**
     * 联系人
     **/
    private String linkman;

    /**
     * 手机号码
     **/
    private String phone;

    /**
     * 收件详细地址
     **/
    private String address;

    /**
     * 寄托物内容
     **/
    private String cargoContent;

    /**
     * 未合并的内容
     */
    private String content;

    /**
     * 寄托物数量
     **/
    private Integer cargoNum;

    /**
     * 运单号
     */
    @NotEmpty
    private String waybillNum;

    public String getReceivingCompany() {
        return receivingCompany;
    }

    public void setReceivingCompany(String receivingCompany) {
        this.receivingCompany = receivingCompany;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCargoContent() {
        return cargoContent;
    }

    public void setCargoContent(String cargoContent) {
        this.cargoContent = cargoContent;
    }

    public Integer getCargoNum() {
        return cargoNum;
    }

    public void setCargoNum(Integer cargoNum) {
        this.cargoNum = cargoNum;
    }

    public String getInvoiceOrderNo() {
        return invoiceOrderNo;
    }

    public void setInvoiceOrderNo(String invoiceOrderNo) {
        this.invoiceOrderNo = invoiceOrderNo;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
