package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 帮邦关注表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionAttention implements Serializable {

	/**关注ID**varchar(64)**/
	private String attentionId;

	/**被关注用户ID**varchar(64)**/
	private String attentionUserId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**关注时间**datetime**/
	private java.util.Date attentionTime;

    /**是否已读**int(1)**/
    private Integer isRead;




    public void setAttentionId(String attentionId){
		this.attentionId = attentionId;
	}

	public String getAttentionId(){
		return this.attentionId;
	}

	public void setAttentionUserId(String attentionUserId){
		this.attentionUserId = attentionUserId;
	}

	public String getAttentionUserId(){
		return this.attentionUserId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setAttentionTime(java.util.Date attentionTime){
		this.attentionTime = attentionTime;
	}

	public java.util.Date getAttentionTime(){
		return this.attentionTime;
	}

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
