package com.abc12366.uc.model.pay.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 支付宝统一收单线下交易实体
 * @author zhushuai 2017-8-5
 *
 */
public class AliCodePay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min = 1, max = 64)
    private String out_trade_no; // 商户订单号,64个字符以内,唯一
    
    private String seller_id;//卖家支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
    @NotEmpty
	@Size(min = 1, max = 11)
    private String total_amount;//金额
    
    private String discountable_amount;//可打折金额. 参与优惠计算的金额，单位为元
    @NotEmpty
	@Size(min = 1, max = 256)
    private String subject;
    
    @NotEmpty
    private Integer qrCodeSize=200;//二维码大小
    
    private String notify_url;//通知地址
	private String return_url;//返回地址

	private String body;//对交易或商品的描述
    
    private String operator_id;//商户操作员编号
    
    private String store_id;//商户门店编号
    
    private String terminal_id;//商户机具终端编号
    
    private String timeout_express="2h";
    
    private List<AliProduct> goods_detail;
    
    private PayExtend extend_params;// 业务扩展参数

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getDiscountable_amount() {
		return discountable_amount;
	}

	public void setDiscountable_amount(String discountable_amount) {
		this.discountable_amount = discountable_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public List<AliProduct> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<AliProduct> goods_detail) {
		this.goods_detail = goods_detail;
	}

	public PayExtend getExtend_params() {
		return extend_params;
	}

	public void setExtend_params(PayExtend extend_params) {
		this.extend_params = extend_params;
	}

	public Integer getQrCodeSize() {
		return qrCodeSize;
	}

	public void setQrCodeSize(Integer qrCodeSize) {
		this.qrCodeSize = qrCodeSize;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
    
    
    
}
