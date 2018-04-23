package com.abc12366.message.model.bo;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.Serializable;

/**
 * 网上办税联合登录
 * 
 * @author zhushuai 2017-11-6
 * 
 */
public class HndsLoginBo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 国地税类型，1为国税，2为地税，不允许为空
	private String taxtype;
	// 1:国税网厅的纳税人识别号;2:地税网厅的许可用户帐号,不允许为空
	private String mainuserid;
	// Taxtype为2时有用，为地税网厅的子用户帐号，可以为空，为空则代表为以许可用户帐号身份登录
	private String subuserid;
	// MD5后的密码
	private String mm;
	// Taxtype为1时有用，为国税网厅的角色ID，当Taxtype为1时不允许为空
	private String roleid;
	// 用户ID
	private String userId;
	// 纳税人名称
	private String nsrmc = "";

	public String getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}

	public String getMainuserid() {
		return mainuserid;
	}

	public void setMainuserid(String mainuserid) {
		this.mainuserid = mainuserid;
	}

	public String getSubuserid() {
		return subuserid;
	}

	public void setSubuserid(String subuserid) {
		this.subuserid = subuserid;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@SuppressWarnings("restriction")
	public String getChangePwd() {
		return org.apache.commons.lang3.StringUtils.isNotEmpty(this.mm)
				? new BASE64Encoder().encode(this.mm.toUpperCase().getBytes()) : "";
	}

	/**
	 * 拼接地址
	 * 
	 * @param urlfix
	 *            地址前缀
	 * @return
	 */
	public String toLoginStr(String urlfix) {
		StringBuffer url = new StringBuffer(urlfix);
		url.append("&taxtype=" + this.taxtype);
		url.append("&mainuserid=" + this.mainuserid);
		url.append("&mm=" + getChangePwd());
		if (!StringUtils.isEmpty(this.subuserid)) {
			url.append("&subuserid=" + this.subuserid);
		}
		if (!StringUtils.isEmpty(this.roleid)) {
			url.append("&roleid=" + this.roleid);
		}
		return url.toString();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static void main(String[] args) {
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
}
