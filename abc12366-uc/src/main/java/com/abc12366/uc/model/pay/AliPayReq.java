package com.abc12366.uc.model.pay;

import com.abc12366.uc.model.pay.bo.AliPagePayContent;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 *支付宝支付请求
 * @author zhushuai 2017-8-4
 *
 */
public class AliPayReq implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private AliPagePayContent payContent;
	private String notify_url;//通知地址
	private String return_url;//返回地址
	
	
	public AliPagePayContent getPayContent() {
		return payContent;
	}
	public void setPayContent(AliPagePayContent payContent) {
		this.payContent = payContent;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
   
   
}
