package com.abc12366.bangbang.model;
import java.io.Serializable;


/**
 * 
 * VIEW
 * 
 **/
@SuppressWarnings("serial")
public class DzsbHngs implements Serializable {

	/****/
	private String id;

	/**纳税人识别号**/
	private String nsrsbh;

	/**纳税人名称**/
	private String nsrmc;

	/**税务机关代码**/
	private String swjgdm;

	/**税务机关名称**/
	private String swjgmc;

	/**
	 * 访问次数
	 */
	private Integer amount;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setNsrsbh(String nsrsbh){
		this.nsrsbh = nsrsbh;
	}

	public String getNsrsbh(){
		return this.nsrsbh;
	}

	public void setNsrmc(String nsrmc){
		this.nsrmc = nsrmc;
	}

	public String getNsrmc(){
		return this.nsrmc;
	}


	public String getSwjgdm() {
		return swjgdm;
	}

	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}

	public String getSwjgmc() {
		return swjgmc;
	}

	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
