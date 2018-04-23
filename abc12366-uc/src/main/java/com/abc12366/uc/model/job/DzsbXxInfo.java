package com.abc12366.uc.model.job;


/**
 * 电子申报消息详细信息
 * @author zhushuai 2017-11-9
 *
 */
public class DzsbXxInfo {
    private String nsrsbh;//纳税人识别号
    private String shxydm;//社会信用代码
    private String djxh;//登记序号
    private String nsrmc;//纳税人名称
    private String sbzldm;//申报种类代码
    private String szmc;//申报种类名称
    private String skssqq;//所属期开始
    private String skssqz;//所属期截止
    private String wcrq;//日期
    private String lrrq;//录入日期
    
    private Double ybtse;//金额
    private Double kkje;//金额
    
    private String sbqx;//申报期限
    
    
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getShxydm() {
		return shxydm;
	}
	public void setShxydm(String shxydm) {
		this.shxydm = shxydm;
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
	public String getSbzldm() {
		return sbzldm;
	}
	public void setSbzldm(String sbzldm) {
		this.sbzldm = sbzldm;
	}
	public String getSzmc() {
		return szmc;
	}
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	
	public Double getYbtse() {
		return ybtse;
	}
	public void setYbtse(Double ybtse) {
		this.ybtse = ybtse;
	}
	public Double getKkje() {
		return kkje;
	}
	public void setKkje(Double kkje) {
		this.kkje = kkje;
	}
	public String getSkssqq() {
		return skssqq;
	}
	public void setSkssqq(String skssqq) {
		this.skssqq = skssqq;
	}
	public String getSkssqz() {
		return skssqz;
	}
	public void setSkssqz(String skssqz) {
		this.skssqz = skssqz;
	}
	public String getWcrq() {
		return wcrq;
	}
	public void setWcrq(String wcrq) {
		this.wcrq = wcrq;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getSbqx() {
		return sbqx;
	}
	public void setSbqx(String sbqx) {
		this.sbqx = sbqx;
	}
    
    
}
