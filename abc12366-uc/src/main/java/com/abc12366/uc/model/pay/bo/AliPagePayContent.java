package com.abc12366.uc.model.pay.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 支付宝发起页面支付实体类
 * 
 * @author zhushuai 2017-8-4
 * 
 */
public class AliPagePayContent {
	@NotEmpty
	@Size(min = 1, max = 64)
	private String out_trade_no; // 商户订单号,64个字符以内,唯一
	@NotEmpty
	@Size(min = 1, max = 64)
	private String product_code="FAST_INSTANT_TRADE_PAY";// 销售产品码，与支付宝签约的产品码名称
	@NotEmpty
	@Size(min = 1, max = 256)
	private String subject;// 订单标题
	// 该笔订单允许的最晚付款时间，逾期将关闭交易。
	// 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点，
	// 如 1.5h，可转换为 90m。
	private String timeout_express="2h";
	@NotEmpty
	@Size(min = 1, max = 11)
	private String total_amount;// 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	

	private String body;// 订单描述

	private List<AliProduct> goods_detail; // 订单包含的商品列表信息

	private String passback_params;// 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数

	private String goods_type;// 商品主类型：0—虚拟类商品，1—实物类商品（默认）

	private String enable_pay_channels;// 可用渠道，用户只能在指定渠道范围内支付当有多个渠道时用“,”分隔

	

	private String auth_token;// 针对用户授权接口，获取用户相关数据时，用于标识用户授权关系

	private PayExtend extend_params;// 业务扩展参数

	private String qr_pay_mode;// PC扫码支付的方式，支持前置模式和跳转模式。

	private String qrcode_width;// 商户自定义二维码宽度

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
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

	public List<AliProduct> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<AliProduct> goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getPassback_params() {
		return passback_params;
	}

	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public void setEnable_pay_channels(String enable_pay_channels) {
		this.enable_pay_channels = enable_pay_channels;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	public PayExtend getExtend_params() {
		return extend_params;
	}

	public void setExtend_params(PayExtend extend_params) {
		this.extend_params = extend_params;
	}

	public String getQr_pay_mode() {
		return qr_pay_mode;
	}

	public void setQr_pay_mode(String qr_pay_mode) {
		this.qr_pay_mode = qr_pay_mode;
	}

	public String getQrcode_width() {
		return qrcode_width;
	}

	public void setQrcode_width(String qrcode_width) {
		this.qrcode_width = qrcode_width;
	}

}
