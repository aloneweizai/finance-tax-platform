package com.abc12366.message.model;


public class UserSubscriptionInfo extends UserSubscription{
	private String type; // 消息类型

	private String busiType; // 业务类型

	private Boolean hasWeb; // 是否有站内消息

	private Boolean webForce; // 是否强制订阅站内消息

	private Boolean hasWechat; // 是否有微信消息

	private Boolean wechatForce; // 是否强制订阅微信消息

	private Boolean hasMessage; // 是否有短信消息

	private Boolean messageForce; // 是否强制订阅短信
	
	private String remark;//备注信息

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public Boolean getHasWeb() {
		return hasWeb;
	}

	public void setHasWeb(Boolean hasWeb) {
		this.hasWeb = hasWeb;
	}

	public Boolean getWebForce() {
		return webForce;
	}

	public void setWebForce(Boolean webForce) {
		this.webForce = webForce;
	}

	public Boolean getHasWechat() {
		return hasWechat;
	}

	public void setHasWechat(Boolean hasWechat) {
		this.hasWechat = hasWechat;
	}

	public Boolean getWechatForce() {
		return wechatForce;
	}

	public void setWechatForce(Boolean wechatForce) {
		this.wechatForce = wechatForce;
	}

	public Boolean getHasMessage() {
		return hasMessage;
	}

	public void setHasMessage(Boolean hasMessage) {
		this.hasMessage = hasMessage;
	}

	public Boolean getMessageForce() {
		return messageForce;
	}

	public void setMessageForce(Boolean messageForce) {
		this.messageForce = messageForce;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
