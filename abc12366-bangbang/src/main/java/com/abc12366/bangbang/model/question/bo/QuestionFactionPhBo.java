package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;


/**
 * 
 * 邦派表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionPhBo implements Serializable {

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**邦派名称**varchar(200)**/
	private String factionName;

	/**邦派图片**varchar(300)**/
	private String factionImg;

    /**邦派荣誉值****/
    private String honor;

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getFactionImg() {
        return factionImg;
    }

    public void setFactionImg(String factionImg) {
        this.factionImg = factionImg;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }
}
