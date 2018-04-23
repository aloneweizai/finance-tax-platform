package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 邦派勋章表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionMedalFactionBo implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**勋章ID**varchar(64)**/
	private String medalId;

	/**邦派ID**varchar(64)**/
	private String factionId;

    /**勋章图片**/
    private String image;

    /**勋章名称**/
    private String name;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
