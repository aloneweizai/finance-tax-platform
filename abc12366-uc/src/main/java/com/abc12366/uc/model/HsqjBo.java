package com.abc12366.uc.model;

import java.io.Serializable;

/**
 * 汇算清缴注册实体类
 * 
 * @author Administrator
 *
 */
public class HsqjBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8436300469136048923L;

	private String nsrsbh;//纳税人识别号

	private String nsrmc;//纳税人名称

	private String ywlx;//业务类型

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

}
