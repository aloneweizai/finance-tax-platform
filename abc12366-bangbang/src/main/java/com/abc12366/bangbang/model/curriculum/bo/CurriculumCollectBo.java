package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 课程收藏表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumCollectBo implements Serializable {

	/**收藏ID**varchar(64)**/
	private String collectId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**用户ID**varchar(64)**/
	private String userId;

    /**收藏时间**data**/
    private java.util.Date collectTime;



	public void setCollectId(String collectId){
		this.collectId = collectId;
	}

	public String getCollectId(){
		return this.collectId;
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


    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
