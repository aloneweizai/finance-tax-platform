package com.abc12366.uc.model.bo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * @author lizhongwei
 * @create 2017-01-02 
 * 办税日历
 *
 **/
@SuppressWarnings("serial")
public class UserBsrlBO implements Serializable {

	/**PK**/
	private String calId;

	/**申报月份**/
    @NotEmpty
    @Size(max = 10)
	private String sbyf;

	/**申报时间**/
    @NotEmpty
    @Size(max = 10)
	private String sbrq;

	/**修改时间**/
    @Size(max = 10)
	private String xgsj;

	/**描述**/
    @Size(max = 250)
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
