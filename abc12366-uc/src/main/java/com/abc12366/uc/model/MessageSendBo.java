package com.abc12366.uc.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 前段消息发送接口类
 * 
 * @author zhushuai 2017-11-13
 *
 */
public class MessageSendBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;// 用户ID

	private List<String> userIds;// 批量用户ID

	private String type;// 消息类型

	private String busiType;// 业务类型

	private String businessId;// 来源ID

	private String webMsg;// 站内消息

	private String phoneMsg;// 短信消息

	private Boolean wxNoChargeVip;// 微信消息是否无视会员等级

	private Boolean moblieNoChargeVip;// 短信消息是否无视会员等级

	private Map<String, String> dataList;// 模板消息内容

	private String templateid;// 模板ID

	private String skipUrl;// 超链接地址

	public Boolean getWxNoChargeVip() {
		return wxNoChargeVip == null ? false : wxNoChargeVip;
	}

	public void setWxNoChargeVip(Boolean wxNoChargeVip) {
		this.wxNoChargeVip = wxNoChargeVip;
	}

	public Boolean getMoblieNoChargeVip() {
		return moblieNoChargeVip == null ? false : moblieNoChargeVip;
	}

	public void setMoblieNoChargeVip(Boolean moblieNoChargeVip) {
		this.moblieNoChargeVip = moblieNoChargeVip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWebMsg() {
		return webMsg;
	}

	public void setWebMsg(String webMsg) {
		this.webMsg = webMsg;
	}

	public String getPhoneMsg() {
		return phoneMsg;
	}

	public void setPhoneMsg(String phoneMsg) {
		this.phoneMsg = phoneMsg;
	}

	public Map<String, String> getDataList() {
		return dataList;
	}

	public void setDataList(Map<String, String> dataList) {
		this.dataList = dataList;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public String getSkipUrl() {
		return skipUrl;
	}

	public void setSkipUrl(String skipUrl) {
		this.skipUrl = skipUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
