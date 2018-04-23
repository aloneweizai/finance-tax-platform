package com.abc12366.uc.model.pay;
/*
 * 退款结果
 */
public class WxRefundRsp extends WxRspBase {
	private String transaction_id; //微信订单号
	private String out_trade_no; // 商户订单号
	private String out_refund_no; // 商户退款单号
	private String refund_id; // 微信退款单号
	private String refund_fee; // 退款金额
	private String settlement_refund_fee; // 应结退款金额
	private String total_fee; // 标价金额
	private String settlement_total_fee; // 应结订单金额
	private String fee_type; // 标价币种
	private String cash_fee; // 现金支付金额
	private String cash_fee_type; // 现金支付币种
	private String cash_refund_fee; // 现金退款金额
	private String coupon_type_$n; // 代金券类型
	private String coupon_refund_fee; // 代金券退款总金额
	private String coupon_refund_fee_$n; // 单个代金券退款金额
	private String coupon_refund_count; // 退款代金券使用数量
	private String coupon_refund_id_$n; // 退款代金券ID

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getSettlement_refund_fee() {
		return settlement_refund_fee;
	}

	public void setSettlement_refund_fee(String settlement_refund_fee) {
		this.settlement_refund_fee = settlement_refund_fee;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(String cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}

	public String getCoupon_type_$n() {
		return coupon_type_$n;
	}

	public void setCoupon_type_$n(String coupon_type_$n) {
		this.coupon_type_$n = coupon_type_$n;
	}

	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(String coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}

	public String getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}

	public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
		this.coupon_refund_fee_$n = coupon_refund_fee_$n;
	}

	public String getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public void setCoupon_refund_count(String coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}

	public String getCoupon_refund_id_$n() {
		return coupon_refund_id_$n;
	}

	public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
		this.coupon_refund_id_$n = coupon_refund_id_$n;
	}

}
