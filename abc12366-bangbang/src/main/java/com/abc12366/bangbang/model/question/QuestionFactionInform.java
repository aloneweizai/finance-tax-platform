package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 邦派通知表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionInform implements Serializable {

	/**通知ID**varchar(64)**/
	private String id;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**通知内容**varchar(500)**/
	private String content;

	/**创建人ID**varchar(64)**/
	private String createUserId;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**更新时间**datetime**/
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

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
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

}
