package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 *问题受理表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionAccepted implements Serializable {

	/****/
	private Integer id;

	/****/
	private String nsrsbh;

	/****/
	private String nsrmc;

	/****/
	private String productName;

	/****/
	private String type;

	/****/
	private String name;

	/****/
	private String phone;

	/****/
	private String visitDate;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
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

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return this.productName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setVisitDate(String visitDate){
		this.visitDate = visitDate;
	}

	public String getVisitDate(){
		return this.visitDate;
	}

}
