package com.abc12366.uc.model;

/**
 * 电子申报注册统计实体类
 * 
 * @author Administrator
 *
 */
public class DzsbRegisterStat {
	private String djrq;
	private Integer total;

	public DzsbRegisterStat() {

	}

	public DzsbRegisterStat(String djrq, Integer total) {
		this.djrq = djrq;
		this.total = total;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
