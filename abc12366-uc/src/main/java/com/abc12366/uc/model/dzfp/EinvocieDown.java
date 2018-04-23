package com.abc12366.uc.model.dzfp;

import java.io.Serializable;


public class EinvocieDown implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String returnCode;
	private String returnMessage;
    private String PDF_URL;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getPDF_URL() {
		return PDF_URL;
	}
	public void setPDF_URL(String pDF_URL) {
		PDF_URL = pDF_URL;
	}
    
}
