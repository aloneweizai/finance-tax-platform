package com.abc12366.message.model;

/**
 * 第三方短信发送日志查询
 * 
 * @author Administrator
 *
 */
public class MsgChannelSendLog {
	private String template_id;// 模板ID
	private String phoneNum; // 手机号码
	private String sendStatus; // 发送状态 1：等待回执，2：发送失败，3：发送成功
	private String content; // 短信内容
	private String sendDate; // 发送时间
	private String receiveDate; // 接收时间
	private String channel;// 发送渠道

	public MsgChannelSendLog() {

	}

	public MsgChannelSendLog(String template_id, String phoneNum, String sendStatus, String content, String sendDate,
			String receiveDate, String channel) {
		this.template_id = template_id;
		this.phoneNum = phoneNum;
		this.sendStatus = sendStatus;
		this.content = content;
		this.sendDate = sendDate;
		this.receiveDate = receiveDate;
		this.channel = channel;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
