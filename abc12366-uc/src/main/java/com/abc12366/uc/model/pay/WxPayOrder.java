package com.abc12366.uc.model.pay;

import javax.validation.constraints.NotNull;

/**
 * 微信同意下单实体类
 * 
 * @author Administrator
 *
 */
public class WxPayOrder {
	// 公众账号appid
	private String appid;
	// 商户号
	private String mch_id;
	// 设备号
	private String device_info = "WEB";
	//随机字符串     
	private String nonce_str;
	// 签名
	private String sign;
	// 签名类型
	private String sign_type = "MD5";
	// 商品描述
	@NotNull(message="body不能为空") 
	private String body;
	// 商品详情
	private String detail;
	// 附加数据
	private String attach;
	// 商户订单号
	@NotNull(message="out_trade_no不能为空") 
	private String out_trade_no;
	// 标价币种
	private String fee_type = "CNY";
	// 标价金额
	@NotNull(message="total_fee不能为空")
	private String total_fee;
	// 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	@NotNull(message="spbill_create_ip不能为空")
	private String spbill_create_ip;
	// 交易起始时间
	private String time_start;
	// 交易结束时间
	private String time_expire;
	// 订单优惠标记
	private String goods_tag;
	// 通知地址
	private String notify_url;
	// 交易类型 JSAPI 公众号支付NATIVE 扫码支付 APP APP支付
	@NotNull(message="trade_type不能为空") 
	private String trade_type;
	// 商品ID trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
	private String product_id;
	// 指定支付方式   上传此参数no_credit--可限制用户不能使用信用卡支付
	private String limit_pay;
	// 用户标识  trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识
	private String openid;
	// 场景信息 该字段用于上报场景信息，目前支持上报实际门店信息。该字段为JSON对象数据
	private String scene_info;


	public String getAppid() {
		return appid;
	}

	public WxPayOrder setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}

	public WxPayOrder setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

	public String getDevice_info() {
		return device_info;
	}

	public WxPayOrder setDevice_info(String device_info) {
		this.device_info = device_info;
		return this;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public WxPayOrder setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
		return this;
	}

	public String getSign() {
		return sign;
	}

	public WxPayOrder setSign(String sign) {
		this.sign = sign;
		return this;
	}

	public String getSign_type() {
		return sign_type;
	}

	public WxPayOrder setSign_type(String sign_type) {
		this.sign_type = sign_type;
		return this;
	}

	public String getBody() {
		return body;
	}

	public WxPayOrder setBody(String body) {
		this.body = body;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public WxPayOrder setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public String getAttach() {
		return attach;
	}

	public WxPayOrder setAttach(String attach) {
		this.attach = attach;
		return this;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public WxPayOrder setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}

	public String getFee_type() {
		return fee_type;
	}

	public WxPayOrder setFee_type(String fee_type) {
		this.fee_type = fee_type;
		return this;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public WxPayOrder setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
		return this;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public WxPayOrder setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
		return this;
	}

	public String getTime_start() {
		return time_start;
	}

	public WxPayOrder setTime_start(String time_start) {
		this.time_start = time_start;
		return this;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public WxPayOrder setTime_expire(String time_expire) {
		this.time_expire = time_expire;
		return this;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public WxPayOrder setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
		return this;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public WxPayOrder setNotify_url(String notify_url) {
		this.notify_url = notify_url;
		return this;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public WxPayOrder setTrade_type(String trade_type) {
		this.trade_type = trade_type;
		return this;
	}

	public String getProduct_id() {
		return product_id;
	}

	public WxPayOrder setProduct_id(String product_id) {
		this.product_id = product_id;
		return this;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public WxPayOrder setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public WxPayOrder setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getScene_info() {
		return scene_info;
	}

	public WxPayOrder setScene_info(String scene_info) {
		this.scene_info = scene_info;
		return this;
	}


}
