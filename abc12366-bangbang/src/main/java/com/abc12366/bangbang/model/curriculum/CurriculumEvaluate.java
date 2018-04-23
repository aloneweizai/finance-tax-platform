package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程评价表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumEvaluate implements Serializable {

	/**课程评价ID**varchar(64)**/
	private String evaluateId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**课程评分**tinyint(4)**/
	private Integer grade;

	/**学习感受**varchar(1000)**/
	private String studyFeel;

	/**用户ID**varchar(64)**/
	private String userId;

	/**评价时间**datetime**/
	private java.util.Date evaluateTime;



	public void setEvaluateId(String evaluateId){
		this.evaluateId = evaluateId;
	}

	public String getEvaluateId(){
		return this.evaluateId;
	}

	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setGrade(Integer grade){
		this.grade = grade;
	}

	public Integer getGrade(){
		return this.grade;
	}

	public void setStudyFeel(String studyFeel){
		this.studyFeel = studyFeel;
	}

	public String getStudyFeel(){
		return this.studyFeel;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setEvaluateTime(java.util.Date evaluateTime){
		this.evaluateTime = evaluateTime;
	}

	public java.util.Date getEvaluateTime(){
		return this.evaluateTime;
	}

}
