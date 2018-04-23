package com.abc12366.uc.model.pay;

import java.io.Serializable;
/**
 * 对账单地址
 * @author zhushuai 2017-8-5
 *
 */
public class BillListReq implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String bill_type="trade";
    private String bill_date;
    
	public String getBill_type() {
		return bill_type;
	}
	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
    
    
}
