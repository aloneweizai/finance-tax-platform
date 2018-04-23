package com.abc12366.uc.model.pay;

import java.io.Serializable;
/**
 * 二维码请求返回
 * @author zhushuai 2017-11-6
 *
 */
public class PayCodeRsp implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String out_trade_no; //流水号
	private String qccodeStr; //二维码内容
	
	public PayCodeRsp(String outTradeNo, String creatQRcodeString) {
		this.out_trade_no=outTradeNo;
		this.qccodeStr=creatQRcodeString;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getQccodeStr() {
		return qccodeStr;
	}
	public void setQccodeStr(String qccodeStr) {
		this.qccodeStr = qccodeStr;
	}
    
    
}
