package com.abc12366.bangbang.model.question;
import java.io.Serializable;


/**
 * 
 * 邦派勋章表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionMedalFaction implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**勋章ID**varchar(64)**/
	private String medalId;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**获取时间**datetime**/
	private java.util.Date medalTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setMedalId(String medalId){
		this.medalId = medalId;
	}

	public String getMedalId(){
		return this.medalId;
	}

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public void setMedalTime(java.util.Date medalTime){
		this.medalTime = medalTime;
	}

	public java.util.Date getMedalTime(){
		return this.medalTime;
	}

}
