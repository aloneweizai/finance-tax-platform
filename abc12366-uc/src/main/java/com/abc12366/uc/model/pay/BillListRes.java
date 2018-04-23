package com.abc12366.uc.model.pay;

import java.io.Serializable;
/**
 * 对账单地址
 * @author zhushuai 2017-8-5
 *
 */
public class BillListRes implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String code;
	
	private String msg;
	
	private String bill_download_url;

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

	public String getBill_download_url() {
		return bill_download_url;
	}

	public void setBill_download_url(String bill_download_url) {
		this.bill_download_url = bill_download_url;
	}
	
	
	
    
    
}
