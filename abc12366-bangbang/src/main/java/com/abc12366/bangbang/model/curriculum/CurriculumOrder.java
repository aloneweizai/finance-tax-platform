package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程订购表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumOrder implements Serializable {

	/**课程订购ID**varchar(64)**/
	private String orderId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**商品ID**varchar(64)**/
    private String goodsId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**用户昵称**varchar(30)**/
	private String nickname;

	/**姓名**varchar(30)**/
	private String username;

	/**会员等级**varchar(64)**/
	private String memberGrade;

	/**订单名称**varchar(50)**/
	private String orderName;

	/**订购价格**double**/
	private Double orderPrice;

    /**订购积分**double**/
    private Double orderIntegral;

	/**是否积分购买**tinyint(1)**/
	private Integer isIntegral;

	/**订购时间**datetime**/
	private java.util.Date orderTime;

	/**联系电话**varchar(20)**/
	private String phone;

	/**访问IP**varchar(30)**/
	private String visitIP;

	/**访问地址**varchar(100)**/
	private String visitSite;

	/**订购状态**tinyint(1)**/
	private Integer orderStatus;



	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}

	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setMemberGrade(String memberGrade){
		this.memberGrade = memberGrade;
	}

	public String getMemberGrade(){
		return this.memberGrade;
	}

	public void setOrderName(String orderName){
		this.orderName = orderName;
	}

	public String getOrderName(){
		return this.orderName;
	}

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Double orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public void setIsIntegral(Integer isIntegral){
		this.isIntegral = isIntegral;
	}

	public Integer getIsIntegral(){
		return this.isIntegral;
	}

	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}

	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setVisitIP(String visitIP){
		this.visitIP = visitIP;
	}

	public String getVisitIP(){
		return this.visitIP;
	}

	public void setVisitSite(String visitSite){
		this.visitSite = visitSite;
	}

	public String getVisitSite(){
		return this.visitSite;
	}

	public void setOrderStatus(Integer orderStatus){
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus(){
		return this.orderStatus;
	}

}
