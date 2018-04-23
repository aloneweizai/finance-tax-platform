package com.abc12366.uc.model.dzfp;

import com.abc12366.uc.webservice.DzfpClient;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 电子发票请求类
 * 
 * @author zhushuai 2017-8-8
 * 
 */
public class DzfpGetReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fpqqlsh=""; //请求流水号
	private String yfp_dm=""; //原发票代码 红字发票必填
	private String yfp_hm=""; //原发票号码 红字发票必填
	
	@NotEmpty
	private String kplx=""; //开票类型 0：蓝字发票 1：红字发票
	@NotEmpty
	private String zsfs=""; //征税方式 0：普通征税 2差额征税
	@NotEmpty
	private String gmf_mc=""; //购买方名称
	@NotEmpty
	private String kpr;
	/*@NotEmpty
	private Double jshj=0.00; //价税合计
	@NotEmpty
	private Double hjje=0.00; //合计金额
	@NotEmpty
	private Double hjse=0.00; //合计税额
    */	
	@NotEmpty
	private String hylx="";//行业类型 0.商业 。1.其他
	private String bmb_bbh=""; //编码表版本号
	private String gmf_nsrsbh=""; //购买方纳税人识别号
	
	private String gmf_dzdh=""; //购买方地址
	private String gmf_yhzh=""; //购买方银行卡号
	private String gmf_sjh=""; //购买方手机号
	private String gmf_dzyx=""; //购买方电子邮箱
	private String fpt_zh=""; //购买方平台账户
	private String wx_openid=""; //微信id
	private String skr=""; //收款人
	private String fhr=""; //复核人
	
	
	private Double kce=0.00; //扣除额
	private String bz=""; //备注
	
	private String by1=""; //备用字段
	private String by2=""; //备用字段。订单号
	private String by3=""; //备用字段
	private String by4=""; //备用字段
	private String by5=""; //备用字段
	private String by6=""; //备用字段
	private String by7=""; //备用字段 
	private String by8=""; //备用字段
	private String by9=""; //备用字段
	private String by10=""; //备用字段
	
	@NotNull
	private  List<InvoiceXm>  invoiceXms=new ArrayList<InvoiceXm>();
		
	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kqr) {
		this.kpr = kqr;
	}
	
	/***
	 * 生成电子发票所需XML
	 * @return
	 */
	public String tosendXml(){
		StringBuffer invoiceXmsStr=new StringBuffer();
		
		double jshj=0.00,hjje=0.00,hjse=0.00;
		
		for(InvoiceXm invoiceXm:invoiceXms){
			jshj+=invoiceXm.getTotalAmt();
			double xmje=DzfpClient.formatDouble2(invoiceXm.getTotalAmt()/(1+DzfpClient.SL));
			hjje+=xmje;
            double xmSe=DzfpClient.formatDouble2(invoiceXm.getTotalAmt()-xmje);
			hjse+=xmSe;
			double xmdj=DzfpClient.formatDouble2(xmje/invoiceXm.getXmsl());
			
			
			invoiceXmsStr.append("<COMMON_FPKJ_XMXX>").append("<FPHXZ>").append(invoiceXm.getFphxz()).append("</FPHXZ>")
				.append("<SPBM>").append(invoiceXm.getSpbm()).append("</SPBM>").append("<ZXBM>").append(invoiceXm.getZxbm()).append("</ZXBM>")
				.append("<YHZCBS>").append(invoiceXm.getYhzcbs()).append("</YHZCBS>").append("<LSLBS>").append(invoiceXm.getLslbs()).append("</LSLBS>")
				.append("<ZZSTSGL>").append(invoiceXm.getZzstsgl()).append("</ZZSTSGL>").append("<XMMC>").append(invoiceXm.getXmmc()).append("</XMMC>")
				.append("<GGXH>").append(invoiceXm.getGgxh()).append("</GGXH>").append("<DW>").append(invoiceXm.getDw()).append("</DW>")
				.append("<XMSL>").append(invoiceXm.getXmsl()).append("</XMSL>").append("<XMDJ>").append(xmdj).append("</XMDJ>")
				.append("<XMJE>").append(xmje).append("</XMJE>").append("<SL>").append(DzfpClient.SL).append("</SL>")
				.append("<SE>").append(xmSe).append("</SE>").append("<BY1>").append(invoiceXm.getBy1()).append("</BY1>")
				.append("<BY2>").append(invoiceXm.getBy2()).append("</BY2>").append("<BY3>").append(invoiceXm.getBy3()).append("</BY3>")
				.append("<BY4>").append(invoiceXm.getBy4()).append("</BY4>").append("<BY5>").append(invoiceXm.getBy5()).append("</BY5>")
				.append("</COMMON_FPKJ_XMXX>");
		 }
		
		
		
		StringBuffer content=new StringBuffer("<REQUEST_COMMON_FPKJ class='REQUEST_COMMON_FPKJ'>");
		content.append("<FPQQLSH>").append(this.fpqqlsh).append("</FPQQLSH>").append("<KPLX>").append(this.kplx).append("</KPLX>")
		.append("<XSF_NSRSBH>").append(DzfpClient.XSF_NSRSBH).append("</XSF_NSRSBH>").append("<XSF_MC>").append(DzfpClient.XSF_MC).append("</XSF_MC>")
		.append("<XSF_DZDH>").append(DzfpClient.TXSF_DZDH).append("</XSF_DZDH>").append("<XSF_YHZH>").append(DzfpClient.XSF_YHZH).append("</XSF_YHZH>")
		.append("<GMF_NSRSBH>").append(gmf_nsrsbh).append("</GMF_NSRSBH>").append("<GMF_MC>").append(gmf_mc).append("</GMF_MC>")
		.append("<GMF_DZDH>").append(gmf_dzdh).append("</GMF_DZDH>").append("<GMF_YHZH>").append(gmf_yhzh).append("</GMF_YHZH>")
		.append("<GMF_SJH>").append(gmf_sjh).append("</GMF_SJH>").append("<GMF_DZYX>").append(gmf_dzyx).append("</GMF_DZYX>")
		.append("<FPT_ZH>").append(fpt_zh).append("</FPT_ZH>").append("<KPR>").append(kpr).append("</KPR>")
		.append("<SKR>").append(skr).append("</SKR>").append("<FHR>").append(fhr).append("</FHR>")
		.append("<YFP_DM>").append(yfp_dm).append("</YFP_DM>").append("<YFP_HM>").append(yfp_hm).append("</YFP_HM>")
		.append("<JSHJ>").append(jshj).append("</JSHJ>").append("<HJJE>").append(hjje).append("</HJJE>")
		.append("<HJSE>").append(hjse).append("</HJSE>").append("<BZ>").append(bz).append("</BZ>")
		.append("<HYLX>").append(hylx).append("</HYLX>").append("<BY1>").append(by1).append("</BY1>")
		.append("<BY2>").append(by2).append("</BY2>").append("<BY3>").append(by3).append("</BY3>")
		.append("<BY4>").append(by4).append("</BY4>").append("<BY5>").append(by5).append("</BY5>")
		.append("<BY6>").append(by6).append("</BY6>").append("<BY7>").append(by7).append("</BY7>")
		.append("<BY8>").append(by8).append("</BY8>").append("<BY9>").append(by9).append("</BY9>")
		.append("<BY10>").append(by10).append("</BY10>").append("<COMMON_FPKJ_XMXXS class='COMMON_FPKJ_XMXX' size='"+invoiceXms.size()+"'>")
		.append(invoiceXmsStr).append("</COMMON_FPKJ_XMXXS></REQUEST_COMMON_FPKJ>");
		
		return content.toString();
	}
	
	public List<InvoiceXm> getInvoiceXms() {
		return invoiceXms;
	}

	public void setInvoiceXms(List<InvoiceXm> invoiceXms) {
		this.invoiceXms = invoiceXms;
	}

	
	public Double getKce() {
		return kce;
	}

	public void setKce(Double kce) {
		this.kce = kce;
	}

	public String getFpqqlsh() {
		return fpqqlsh;
	}
	public void setFpqqlsh(String fpqqlsh) {
		this.fpqqlsh = fpqqlsh;
	}
	
	public String getKplx() {
		return kplx;
	}


	public void setKplx(String kplx) {
		this.kplx = kplx;
	}


	public String getBmb_bbh() {
		return bmb_bbh;
	}
	public void setBmb_bbh(String bmb_bbh) {
		this.bmb_bbh = bmb_bbh;
	}
	public String getZsfs() {
		return zsfs;
	}
	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}
	
	public String getGmf_nsrsbh() {
		return gmf_nsrsbh;
	}
	public void setGmf_nsrsbh(String gmf_nsrsbh) {
		this.gmf_nsrsbh = gmf_nsrsbh;
	}
	public String getGmf_mc() {
		return gmf_mc;
	}
	public void setGmf_mc(String gmf_mc) {
		this.gmf_mc = gmf_mc;
	}
	public String getGmf_dzdh() {
		return gmf_dzdh;
	}
	public void setGmf_dzdh(String gmf_dzdh) {
		this.gmf_dzdh = gmf_dzdh;
	}
	public String getGmf_yhzh() {
		return gmf_yhzh;
	}
	public void setGmf_yhzh(String gmf_yhzh) {
		this.gmf_yhzh = gmf_yhzh;
	}
	public String getGmf_sjh() {
		return gmf_sjh;
	}
	public void setGmf_sjh(String gmf_sjh) {
		this.gmf_sjh = gmf_sjh;
	}
	public String getGmf_dzyx() {
		return gmf_dzyx;
	}
	public void setGmf_dzyx(String gmf_dzyx) {
		this.gmf_dzyx = gmf_dzyx;
	}
	public String getFpt_zh() {
		return fpt_zh;
	}
	public void setFpt_zh(String fpt_zh) {
		this.fpt_zh = fpt_zh;
	}
	public String getWx_openid() {
		return wx_openid;
	}
	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}
	
	public String getSkr() {
		return skr;
	}
	public void setSkr(String skr) {
		this.skr = skr;
	}
	public String getFhr() {
		return fhr;
	}
	public void setFhr(String fhr) {
		this.fhr = fhr;
	}
	public String getYfp_dm() {
		return yfp_dm;
	}
	public void setYfp_dm(String yfp_dm) {
		this.yfp_dm = yfp_dm;
	}
	public String getYfp_hm() {
		return yfp_hm;
	}
	public void setYfp_hm(String yfp_hm) {
		this.yfp_hm = yfp_hm;
	}
	
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getHylx() {
		return hylx;
	}
	public void setHylx(String hylx) {
		this.hylx = hylx;
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
	public String getBy6() {
		return by6;
	}
	public void setBy6(String by6) {
		this.by6 = by6;
	}
	public String getBy7() {
		return by7;
	}
	public void setBy7(String by7) {
		this.by7 = by7;
	}
	public String getBy8() {
		return by8;
	}
	public void setBy8(String by8) {
		this.by8 = by8;
	}
	public String getBy9() {
		return by9;
	}
	public void setBy9(String by9) {
		this.by9 = by9;
	}
	public String getBy10() {
		return by10;
	}
	public void setBy10(String by10) {
		this.by10 = by10;
	}
	
}
