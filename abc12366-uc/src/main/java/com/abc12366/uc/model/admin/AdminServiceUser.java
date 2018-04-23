package com.abc12366.uc.model.admin;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 客服经理用户关联表
 * 
 **/
@SuppressWarnings("serial")
public class AdminServiceUser implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**用户ID**varchar(64)**/
	private String userId;

	/**用户昵称**varchar(50)**/
	private String nickname;

	/**用户姓名**varchar(32)**/
	private String username;

	/**会员等级**varchar(64)**/
	private String gradeId;

	/**会员有效期**datetime**/
	private java.util.Date validity;

	/**客户经理**varchar(64)**/
	private String serviceId;

    /**创建时间**datetime**/
    private java.util.Date createTime;

    /**修改时间**datetime**/
    private java.util.Date lastUpdate;



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

	public void setGradeId(String gradeId){
		this.gradeId = gradeId;
	}

	public String getGradeId(){
		return this.gradeId;
	}

	public void setValidity(java.util.Date validity){
		this.validity = validity;
	}

	public java.util.Date getValidity(){
		return this.validity;
	}

	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}

	public String getServiceId(){
		return this.serviceId;
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
