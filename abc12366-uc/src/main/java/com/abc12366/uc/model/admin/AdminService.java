package com.abc12366.uc.model.admin;
import java.io.Serializable;


/**
 * 
 * 客服经理表
 * 
 **/
@SuppressWarnings("serial")
public class AdminService implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**员工工号**varchar(64)**/
	private String userId;

	/**姓名**varchar(32)**/
	private String username;

	/**联系电话**varchar(20)**/
	private String phone;

	/**形象照片**varchar(512)**/
	private String userPicturePath;

	/**个人介绍**varchar(1000)**/
	private String intro;

	/**状态**tinyint(1)**/
	private Integer status;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date lastUpdate;

	/**创建人**varchar(64)**/
	private String createUserId;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setUserPicturePath(String userPicturePath){
		this.userPicturePath = userPicturePath;
	}

	public String getUserPicturePath(){
		return this.userPicturePath;
	}

	public void setIntro(String intro){
		this.intro = intro;
	}

	public String getIntro(){
		return this.intro;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	public String getCreateUserId(){
		return this.createUserId;
	}

}
