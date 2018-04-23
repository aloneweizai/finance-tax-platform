package com.abc12366.bangbang.model.question;

/**
 * 
 * 帮派每个月积分分配记录
 * 
 **/
public class QuestionFactionRewardSetting  {

	/**PK**/
	private String id;

	/**帮派id**/
	private String factionId;

	/**日期月份**/
	private String date;

	/**奖励积分**/
	private Integer rewardsPoints;

	/**管理员id**/
	private String updateAdmin;

	/**分配时间**/
	private java.util.Date updateTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setFactionId(String factionId){
		this.factionId = factionId;
	}

	public String getFactionId(){
		return this.factionId;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return this.date;
	}

	public void setRewardsPoints(Integer rewardsPoints){
		this.rewardsPoints = rewardsPoints;
	}

	public Integer getRewardsPoints(){
		return this.rewardsPoints;
	}

	public void setUpdateAdmin(String updateAdmin){
		this.updateAdmin = updateAdmin;
	}

	public String getUpdateAdmin(){
		return this.updateAdmin;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

}
