package com.abc12366.uc.model.dzfp;

import org.hibernate.validator.constraints.NotEmpty;

public class DzfpDownloadReq {
	@NotEmpty
	private String fpqqlsh;
	@NotEmpty
	private String xsf_nsrsbh;
	@NotEmpty
	private String fp_dm;
	@NotEmpty
	private String fp_hm;
	
	public String tosendXml(){
		StringBuffer content=new StringBuffer("<REQUEST_COMMON_FPXZDZCX class='REQUEST_COMMON_FPXZDZCX'>");
		content.append("<FPQQLSH>").append(fpqqlsh).append("</FPQQLSH>").append("<XSF_NSRSBH>").append(xsf_nsrsbh).append("</XSF_NSRSBH>");
		content.append("<FP_DM>").append(fp_dm).append("</FP_DM>").append("<FP_HM>").append(fp_hm).append("</FP_HM>");
		content.append("</REQUEST_COMMON_FPXZDZCX>");
		return content.toString();
	}
	
	public String getFpqqlsh() {
		return fpqqlsh;
	}
	public void setFpqqlsh(String fpqqlsh) {
		this.fpqqlsh = fpqqlsh;
	}
	public String getXsf_nsrsbh() {
		return xsf_nsrsbh;
	}
	public void setXsf_nsrsbh(String xsf_nsrsbh) {
		this.xsf_nsrsbh = xsf_nsrsbh;
	}
	public String getFp_dm() {
		return fp_dm;
	}
	public void setFp_dm(String fp_dm) {
		this.fp_dm = fp_dm;
	}
	public String getFp_hm() {
		return fp_hm;
	}
	public void setFp_hm(String fp_hm) {
		this.fp_hm = fp_hm;
	}
	
	
}
