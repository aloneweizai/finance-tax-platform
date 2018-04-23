package com.abc12366.uc.model.pay;

public class Wxrefundquery {
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
	private String transaction_id = "";
	private String out_trade_no = "";
	private String out_refund_no = "";
	private String refund_id = "";
	private String offset = "0";
	
	
	public String getAppid() {
		return appid;
	}

	public Wxrefundquery setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}

	public Wxrefundquery setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

	public String getDevice_info() {
		return device_info;
	}

	public Wxrefundquery setDevice_info(String device_info) {
		this.device_info = device_info;
		return this;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public Wxrefundquery setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
		return this;
	}

	public String getSign() {
		return sign;
	}

	public Wxrefundquery setSign(String sign) {
		this.sign = sign;
		return this;
	}

	public String getSign_type() {
		return sign_type;
	}

	public Wxrefundquery setSign_type(String sign_type) {
		this.sign_type = sign_type;
		return this;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public Wxrefundquery setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
		return this;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public Wxrefundquery setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public Wxrefundquery setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
		return this;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public Wxrefundquery setRefund_id(String refund_id) {
		this.refund_id = refund_id;
		return this;
	}

	public String getOffset() {
		return offset;
	}

	public Wxrefundquery setOffset(String offset) {
		this.offset = offset;
		return this;
	}

}
