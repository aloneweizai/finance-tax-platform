package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;


/**
 * 
 * 课程报名表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumApplyBo implements Serializable {

	/**课程报名ID**varchar(64)**/
	private String applyId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**用户昵称**varchar(30)**/
	private String nickname;

	/**姓名**varchar(30)**/
	private String username;

	/**报名时间**datetime**/
	private java.util.Date applyTime;

	/**联系电话**varchar(20)**/
	private String phone;

	/**签到时间**datetime**/
	private java.util.Date signTime;

	/**签到IP**varchar(30)**/
	private String signIP;

	/**签到地点**varchar(50)**/
	private String signSite;

    /**工作单位**varchar(100)**/
    private String company;

    /**职位**varchar(64)**/
    private String position;



	public void setApplyId(String applyId){
		this.applyId = applyId;
	}

	public String getApplyId(){
		return this.applyId;
	}

	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
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

	public void setApplyTime(java.util.Date applyTime){
		this.applyTime = applyTime;
	}

	public java.util.Date getApplyTime(){
		return this.applyTime;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setSignTime(java.util.Date signTime){
		this.signTime = signTime;
	}

	public java.util.Date getSignTime(){
		return this.signTime;
	}

	public void setSignIP(String signIP){
		this.signIP = signIP;
	}

	public String getSignIP(){
		return this.signIP;
	}

	public void setSignSite(String signSite){
		this.signSite = signSite;
	}

	public String getSignSite(){
		return this.signSite;
	}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
