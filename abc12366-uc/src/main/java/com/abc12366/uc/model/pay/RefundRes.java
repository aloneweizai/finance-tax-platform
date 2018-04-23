package com.abc12366.uc.model.pay;

import com.abc12366.uc.model.pay.PayqueryRes.Fundbill;

import java.io.Serializable;
import java.util.List;
/**
 * 退款返回类
 * @author zhushuai 2017-8-5
 *
 */
public class RefundRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private String buyer_logon_id;//用户的登录id
    private String buyer_user_id;//买家在支付宝的用户id
    private String fund_change;//本次退款是否发生了资金变化 Y,N
    private String gmt_refund_pay;//退款支付时间
    private String open_id;
    private String out_trade_no;//商户订单号
    private String refund_fee;//退款总金额
    private String send_back_fee;
    private String trade_no;//支付宝交易号
    private List<Fundbill> fund_bill_list;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}
	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}
	public String getBuyer_user_id() {
		return buyer_user_id;
	}
	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}
	public String getFund_change() {
		return fund_change;
	}
	public void setFund_change(String fund_change) {
		this.fund_change = fund_change;
	}
	public String getGmt_refund_pay() {
		return gmt_refund_pay;
	}
	public void setGmt_refund_pay(String gmt_refund_pay) {
		this.gmt_refund_pay = gmt_refund_pay;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getSend_back_fee() {
		return send_back_fee;
	}
	public void setSend_back_fee(String send_back_fee) {
		this.send_back_fee = send_back_fee;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public List<Fundbill> getFund_bill_list() {
		return fund_bill_list;
	}
	public void setFund_bill_list(List<Fundbill> fund_bill_list) {
		this.fund_bill_list = fund_bill_list;
	}
    
	
}
