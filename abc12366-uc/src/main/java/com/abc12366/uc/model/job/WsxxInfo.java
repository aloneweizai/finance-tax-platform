package com.abc12366.uc.model.job;

/**
 * 文书申请详细信息
 * 
 * @author zhushuai 2017-11-10
 * 
 */
public class WsxxInfo {
	private String uuid; // 主键
	private String wsid; // 文书id
	private String djxh; // 纳税人登记序号
	private String nsrmc; // 纳税人名称
	private String cszjUserid; // 财税专家用户id
	private String wszlDm; // 文书种类代码
	private String wszlmc; // 文书种类名称
	private String wsztDm; // 文书状态代码
	private String wsztmc; // 文书状态名称
	private String lrrq; // 消息产生日期

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWsid() {
		return wsid;
	}

	public void setWsid(String wsid) {
		this.wsid = wsid;
	}

	public String getDjxh() {
		return djxh;
	}

	public void setDjxh(String djxh) {
		this.djxh = djxh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getCszjUserid() {
		return cszjUserid;
	}

	public void setCszjUserid(String cszjUserid) {
		this.cszjUserid = cszjUserid;
	}

	public String getWszlDm() {
		return wszlDm;
	}

	public void setWszlDm(String wszlDm) {
		this.wszlDm = wszlDm;
	}

	public String getWszlmc() {
		return wszlmc;
	}

	public void setWszlmc(String wszlmc) {
		this.wszlmc = wszlmc;
	}

	public String getWsztDm() {
		return wsztDm;
	}

	public void setWsztDm(String wsztDm) {
		this.wsztDm = wsztDm;
	}

	public String getWsztmc() {
		return wsztmc;
	}

	public void setWsztmc(String wsztmc) {
		this.wsztmc = wsztmc;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

}
