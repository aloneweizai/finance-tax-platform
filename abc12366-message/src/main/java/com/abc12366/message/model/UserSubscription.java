package com.abc12366.message.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 个人订阅消息设置
 * 
 * @author Administrator
 *
 */
public class UserSubscription {
	private String id;
	@NotNull
	private String userId;  //用户id
	@NotNull
	private String settingId; //消息订阅id
	@NotNull
	private Boolean web; //是否订阅站内消息
	@NotNull
	private Boolean wechat;  //是否订阅微信消息
	@NotNull
	private Boolean message;  //是否订阅短信消息
	private Date createTime;  //创建时间
	private Date lastUpdate;  //更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSettingId() {
		return settingId;
	}

	public void setSettingId(String settingId) {
		this.settingId = settingId;
	}

	public Boolean getWeb() {
		return web;
	}

	public void setWeb(Boolean web) {
		this.web = web;
	}

	public Boolean getWechat() {
		return wechat;
	}

	public void setWechat(Boolean wechat) {
		this.wechat = wechat;
	}

	public Boolean getMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
