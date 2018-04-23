package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 邦派奖励分配表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionAllocation implements Serializable {

	/**分配ID**varchar(64)**/
	private String id;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**用户级别**varchar(64)**/
	private String userRank;

	/**分配比例**double**/
	private Double proportion;

	/**分配积分**int(11)**/
	private Integer integral;

	/**创建人ID**varchar(64)**/
	private String createUserId;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**更新时间**datetime**/
	private java.util.Date updateTime;

	/**状态：1、新增，2、审批通过**int(11)**/
	private Integer state;

    /**奖励理由**varchar(1000)**/
    private String awardReason;



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

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserRank(String userRank){
		this.userRank = userRank;
	}

	public String getUserRank(){
		return this.userRank;
	}

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public void setIntegral(Integer integral){
		this.integral = integral;
	}

	public Integer getIntegral(){
		return this.integral;
	}

	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	public String getCreateUserId(){
		return this.createUserId;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

    public String getAwardReason() {
        return awardReason;
    }

    public void setAwardReason(String awardReason) {
        this.awardReason = awardReason;
    }
}
