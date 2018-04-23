package com.abc12366.uc.model.pay;

/**
 * 微信订单查询
 * 
 * @author Administrator
 *
 */
public class WxOrderQuery {
	// 公众账号appid
	private String appid;
	private String mch_id;
	private String transaction_id;
	private String out_trade_no;
	private String nonce_str;
	private String sign;
	private String sign_type = "MD5";

	public String getAppid() {
		return appid;
	}

	public WxOrderQuery setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}

	public WxOrderQuery setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public WxOrderQuery setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
		return this;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public WxOrderQuery setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public WxOrderQuery setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
		return this;
	}

	public String getSign() {
		return sign;
	}

	public WxOrderQuery setSign(String sign) {
		this.sign = sign;
		return this;
	}

	public String getSign_type() {
		return sign_type;
	}

	public WxOrderQuery setSign_type(String sign_type) {
		this.sign_type = sign_type;
		return this;
	}

}
