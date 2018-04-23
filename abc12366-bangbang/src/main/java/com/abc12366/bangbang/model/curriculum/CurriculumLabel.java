package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程标签表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumLabel implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**标签ID**varchar(64)**/
	private String labelId;

    /**标签ID**varchar(30)**/
    private String labelName;



	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setLabelId(String labelId){
		this.labelId = labelId;
	}

	public String getLabelId(){
		return this.labelId;
	}

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
