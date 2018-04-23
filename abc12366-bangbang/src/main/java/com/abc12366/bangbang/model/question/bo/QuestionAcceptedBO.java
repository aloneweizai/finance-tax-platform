package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 *问题受理表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionAcceptedBO implements Serializable {

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

	//年份
	private String date;
	//每年总数
	private String total;
	//所有总数
	private String allCount;

	private String userId;


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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
