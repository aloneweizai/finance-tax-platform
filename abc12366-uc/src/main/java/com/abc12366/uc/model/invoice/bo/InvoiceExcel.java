package com.abc12366.uc.model.invoice.bo;

import java.io.Serializable;


/**
 * 发票信息
 **/
@SuppressWarnings("serial")
public class InvoiceExcel implements Serializable {

    /**
     * 用户订单号
     **/
    private String invoiceOrderNo;

    /**
     * 发票号码
     **/
    private String invoiceNo;

    /**
     * 发票代码
     **/
    private String invoiceCode;
    /**
     * 纳税人识别号
     **/
    private String nsrsbh;
    /**
     * 纳税人名称
     **/
    private String nsrmc;

    /**
     * 时间
     **/
    private String createTime;

    /**
     * 产品名称
     * 发票内容：1.软件服务费 2.财税咨询费 3.技术服务费 4.财税培训费
     **/
    private String content;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 发票金额
     **/
    private Double amount;
    /**
     * 合计金额大写
     */
    private Double amountBig;

    /**
     * 合计金额小写
     */
    private Double amountSmall;
    /**
     * 开票人
     */
    private String drawer;

    /**
     * 备注
     **/
    private String remark;

    public String getInvoiceOrderNo() {
        return invoiceOrderNo;
    }

    public void setInvoiceOrderNo(String invoiceOrderNo) {
        this.invoiceOrderNo = invoiceOrderNo;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountBig() {
        return amountBig;
    }

    public void setAmountBig(Double amountBig) {
        this.amountBig = amountBig;
    }

    public Double getAmountSmall() {
        return amountSmall;
    }

    public void setAmountSmall(Double amountSmall) {
        this.amountSmall = amountSmall;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
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
}
