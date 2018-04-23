package com.abc12366.uc.model.pay;

import javax.validation.constraints.NotNull;

/**
 * 微信退款申请
 * 
 * @author Administrator
 *
 */
public class WxRefund {
	// 公众账号appid
	private String appid;
	// 商户号
	private String mch_id;
	// 设备号
	private String device_info = "WEB";
	// 随机字符串
	private String nonce_str;
	// 签名
	private String sign;
	// 签名类型
	private String sign_type = "MD5";
	@NotNull
	private String transaction_id;
	@NotNull
	private String out_trade_no;
	//商户退款单号
	@NotNull
	private String out_refund_no;
	//订单金额
	@NotNull
	private String total_fee;
	//退款金额
	@NotNull
	private String refund_fee;
    //退款货币种类
	private String refund_fee_type = "CNY";
    //退款原因
	private String refund_desc;
    //退款资金来源
	private String refund_account;
    //退款结果通知url
	private String notify_url;

	public String getAppid() {
		return appid;
	}

	public WxRefund setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}

	public WxRefund setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

	public String getDevice_info() {
		return device_info;
	}

	public WxRefund setDevice_info(String device_info) {
		this.device_info = device_info;
		return this;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public WxRefund setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
		return this;
	}

	public String getSign() {
		return sign;
	}

	public WxRefund setSign(String sign) {
		this.sign = sign;
		return this;
	}

	public String getSign_type() {
		return sign_type;
	}

	public WxRefund setSign_type(String sign_type) {
		this.sign_type = sign_type;
		return this;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public WxRefund setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
		return this;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public WxRefund setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public WxRefund setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
		return this;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public WxRefund setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
		return this;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public WxRefund setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
		return this;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public WxRefund setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
		return this;
	}

	public String getRefund_desc() {
		return refund_desc;
	}

	public WxRefund setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
		return this;
	}

	public String getRefund_account() {
		return refund_account;
	}

	public WxRefund setRefund_account(String refund_account) {
		this.refund_account = refund_account;
		return this;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public WxRefund setNotify_url(String notify_url) {
		this.notify_url = notify_url;
		return this;
	}

}
