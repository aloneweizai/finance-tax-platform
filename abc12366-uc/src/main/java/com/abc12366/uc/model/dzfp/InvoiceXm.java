package com.abc12366.uc.model.dzfp;

import org.hibernate.validator.constraints.NotEmpty;

public class InvoiceXm {
	
	@NotEmpty
	private String fphxz=""; //发票行性质 0.正常行 1.折扣行 2.被折扣行
	private String spbm=""; //商品编码
	private String zxbm=""; //自行编码
	private String yhzcbs=""; //优惠政策标识 0：不使用 1使用
	private String lslbs=""; //零税率标示 空：非0税率 1：免税 2：不征收3：普通零税率
	private String zzstsgl=""; //增值税特殊管理
	
	@NotEmpty
	private String xmmc=""; //项目名称
	@NotEmpty
	private Double totalAmt;
	
   
	//private Double xmdj=0.00; //项目单价
    // @NotEmpty
	//private Double xmje=0.00; //项目金额
	private String ggxh=""; //规格型号
	private String dw=""; //计量单位
	private Double xmsl=1.00; //项目数量
	
	//@NotEmpty
	//private Double se=0.00; //税额
	private String by1=""; //备用字段
	private String by2=""; //备用字段。订单号
	private String by3=""; //备用字段
	private String by4=""; //备用字段
	private String by5=""; //备用字段
	
	
	public Double getTotalAmt() {
			return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
			this.totalAmt = totalAmt;
	}
	
	public String getFphxz() {
		return fphxz;
	}
	public void setFphxz(String fphxz) {
		this.fphxz = fphxz;
	}
	public String getSpbm() {
		return spbm;
	}
	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}
	public String getZxbm() {
		return zxbm;
	}
	public void setZxbm(String zxbm) {
		this.zxbm = zxbm;
	}
	public String getYhzcbs() {
		return yhzcbs;
	}
	public void setYhzcbs(String yhzcbs) {
		this.yhzcbs = yhzcbs;
	}
	public String getLslbs() {
		return lslbs;
	}
	public void setLslbs(String lslbs) {
		this.lslbs = lslbs;
	}
	public String getZzstsgl() {
		return zzstsgl;
	}
	public void setZzstsgl(String zzstsgl) {
		this.zzstsgl = zzstsgl;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public Double getXmsl() {
		return xmsl;
	}
	public void setXmsl(Double xmsl) {
		this.xmsl = xmsl;
	}
	
	public String getBy1() {
		return by1;
	}
	public void setBy1(String by1) {
		this.by1 = by1;
	}
	public String getBy2() {
		return by2;
	}
	public void setBy2(String by2) {
		this.by2 = by2;
	}
	public String getBy3() {
		return by3;
	}
	public void setBy3(String by3) {
		this.by3 = by3;
	}
	public String getBy4() {
		return by4;
	}
	public void setBy4(String by4) {
		this.by4 = by4;
	}
	public String getBy5() {
		return by5;
	}
	public void setBy5(String by5) {
		this.by5 = by5;
	}
	
	
}
