package com.abc12366.uc.model.pay;

import java.io.Serializable;
import java.util.List;
/**
 * 支付宝信息交易查询返回类
 * @author zhushuai 2017-8-7
 *
 */
public class PayqueryRes implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String code;
    private String msg;
    private String buyer_logon_id;//买家支付宝账号
    private String buyer_pay_amount;//买家实付金额
    private String buyer_user_id;
    private String invoice_amount;//交易中用户支付的可开具发票的金额
    private String open_id;
    private String out_trade_no;//商家订单号
    private String point_amount;//积分支付的金额
    private String receipt_amount;//实收金额
    private String send_pay_date;//本次交易打款给卖家的时间
    private String total_amount;//交易的订单金额
    private String trade_no;//支付宝交易号
    private String trade_status;//交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
    private List<Fundbill> fund_bill_list;//交易支付使用的资金渠道
    
    
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
	public String getBuyer_pay_amount() {
		return buyer_pay_amount;
	}
	public void setBuyer_pay_amount(String buyer_pay_amount) {
		this.buyer_pay_amount = buyer_pay_amount;
	}
	public String getBuyer_user_id() {
		return buyer_user_id;
	}
	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}
	public String getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(String invoice_amount) {
		this.invoice_amount = invoice_amount;
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
	public String getPoint_amount() {
		return point_amount;
	}
	public void setPoint_amount(String point_amount) {
		this.point_amount = point_amount;
	}
	public String getReceipt_amount() {
		return receipt_amount;
	}
	public void setReceipt_amount(String receipt_amount) {
		this.receipt_amount = receipt_amount;
	}
	public String getSend_pay_date() {
		return send_pay_date;
	}
	public void setSend_pay_date(String send_pay_date) {
		this.send_pay_date = send_pay_date;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
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

	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	class Fundbill{
    	private String amount;//渠道实际付款金额
    	private String fund_channel;//交易使用的资金渠道
    	
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getFund_channel() {
			return fund_channel;
		}
		public void setFund_channel(String fund_channel) {
			this.fund_channel = fund_channel;
		}
    	
    	
    }
     
}
