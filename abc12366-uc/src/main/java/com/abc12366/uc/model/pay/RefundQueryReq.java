package com.abc12366.uc.model.pay;

import java.io.Serializable;
/**
 * 退款查询
 * @author zhushuai 2017-8-5
 *
 */
public class RefundQueryReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String trade_no;
	private String out_trade_no;
	private String out_request_no;
	
	public  RefundQueryReq(){}
	
	public  RefundQueryReq(String out_trade_no,String out_request_no){
		this.out_trade_no=out_trade_no;
		this.out_request_no=out_request_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}

}
