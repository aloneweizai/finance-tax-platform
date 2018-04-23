package com.abc12366.uc.model.dzfp;

import org.hibernate.validator.constraints.NotEmpty;

public class DzfqQueryReq {
	
	@NotEmpty
	private String fpqqlsh;
	@NotEmpty
	private String xsf_nsrsbh;
	
	public String tosendXml(){
		StringBuffer content=new StringBuffer("<REQUEST_COMMON_FPCX class='REQUEST_COMMON_FPCX'>");
		content.append("<FPQQLSH>").append(fpqqlsh).append("</FPQQLSH>").append("<XSF_NSRSBH>").append(xsf_nsrsbh).append("</XSF_NSRSBH>");
		content.append("</REQUEST_COMMON_FPCX>");
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

}
