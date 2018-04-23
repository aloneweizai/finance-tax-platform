package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程学习表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumStudy implements Serializable {

	/**课程学习ID**varchar(64)**/
	private String studyId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**课件ID**varchar(64)**/
	private String coursewareId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**姓名**varchar(30)**/
	private String username;

	/**用户昵称**varchar(50)**/
	private String nickname;

	/**会员等级**varchar(64)**/
	private String memberGrade;

	/**学习时间**datetime**/
	private java.util.Date studyTime;

	/**学习时长**varchar(10)**/
	private String studyDuration;

	/**课件时长**varchar(10)**/
	private String coursewareDuration;

	/**访问IP**varchar(30)**/
	private String visitIP;

	/**访问地点**varchar(200)**/
	private String visitSite;



	public void setStudyId(String studyId){
		this.studyId = studyId;
	}

	public String getStudyId(){
		return this.studyId;
	}

	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setCoursewareId(String coursewareId){
		this.coursewareId = coursewareId;
	}

	public String getCoursewareId(){
		return this.coursewareId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setMemberGrade(String memberGrade){
		this.memberGrade = memberGrade;
	}

	public String getMemberGrade(){
		return this.memberGrade;
	}

	public void setStudyTime(java.util.Date studyTime){
		this.studyTime = studyTime;
	}

	public java.util.Date getStudyTime(){
		return this.studyTime;
	}

	public void setStudyDuration(String studyDuration){
		this.studyDuration = studyDuration;
	}

	public String getStudyDuration(){
		return this.studyDuration;
	}

	public void setCoursewareDuration(String coursewareDuration){
		this.coursewareDuration = coursewareDuration;
	}

	public String getCoursewareDuration(){
		return this.coursewareDuration;
	}

	public void setVisitIP(String visitIP){
		this.visitIP = visitIP;
	}

	public String getVisitIP(){
		return this.visitIP;
	}

	public void setVisitSite(String visitSite){
		this.visitSite = visitSite;
	}

	public String getVisitSite(){
		return this.visitSite;
	}

}
