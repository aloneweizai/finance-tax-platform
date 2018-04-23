package com.abc12366.uc.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 电子申报服务密码修改日志
 * 
 * @author Administrator
 *
 */
public class NsrsbhPasswordLog {
	private String id;
	private String userId; // 用户ID
	private String nsrsbh; // 纳税人识别号
	private String frmc; // 法人名称
	private String frzjh; // 法人证件号
	private Date createTime; // 时间
	private String ip; // IP
	private String code; // 返回码
	private String message; // 返回信息

	private String username;
	private String nickname;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return StringUtils.isNoneEmpty(message) && message.length() > 1000 ? "操作返回异常信息!" : message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

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

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getFrmc() {
		return frmc;
	}

	public void setFrmc(String frmc) {
		this.frmc = frmc;
	}

	public String getFrzjh() {
		return frzjh;
	}

	public void setFrzjh(String frzjh) {
		this.frzjh = frzjh;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
