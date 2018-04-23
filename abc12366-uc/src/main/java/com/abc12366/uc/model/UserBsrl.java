package com.abc12366.uc.model;
import java.io.Serializable;


/**
 * @author lizhongwei
 * @create 2017-01-02
 * 办税日历
 *
 **/
@SuppressWarnings("serial")
public class UserBsrl implements Serializable {

	/**PK**/
	private String calId;

	/**申报月份**/
	private String sbyf;

	/**申报时间**/
	private String sbrq;

	/**修改时间**/
	private String xgsj;

	/**描述**/
	private String description;



	public void setCalId(String calId){
		this.calId = calId;
	}

	public String getCalId(){
		return this.calId;
	}

	public void setSbyf(String sbyf){
		this.sbyf = sbyf;
	}

	public String getSbyf(){
		return this.sbyf;
	}

	public void setSbrq(String sbrq){
		this.sbrq = sbrq;
	}

	public String getSbrq(){
		return this.sbrq;
	}

	public void setXgsj(String xgsj){
		this.xgsj = xgsj;
	}

	public String getXgsj(){
		return this.xgsj;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

}
