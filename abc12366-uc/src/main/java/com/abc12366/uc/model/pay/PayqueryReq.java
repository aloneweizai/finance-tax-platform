package com.abc12366.uc.model.pay;

import java.io.Serializable;
/**
 * 二维码请求
 * @author zhushuai 2017-11-6
 *
 */
public class PayqueryReq implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String out_trade_no; //订单号
    private String trade_no; //交易流水
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
     
     
}
