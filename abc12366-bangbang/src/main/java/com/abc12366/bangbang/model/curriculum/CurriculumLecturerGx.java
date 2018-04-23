package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程讲师关系表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumLecturerGx implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**讲师ID**varchar(64)**/
    private String lecturerId;

    /**讲师姓名**varchar(30)**/
    private String lecturerName;

    /**讲师图片**varchar(200)**/
    private String lecturerPicture;



	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}


    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLecturerPicture() {
        return lecturerPicture;
    }

    public void setLecturerPicture(String lecturerPicture) {
        this.lecturerPicture = lecturerPicture;
    }
}
