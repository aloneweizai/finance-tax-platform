package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;


/**
 * 
 * 课程浏览表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumBrowseBo implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**课程浏览ID**varchar(64)**/
	private String browseId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**姓名**varchar(30)**/
	private String username;

	/**用户昵称**varchar(50)**/
	private String nickname;

	/**会员等级**varchar(64)**/
	private String memberGrade;

	/**访问IP**varchar(30)**/
	private String visitIP;

	/**访问地点**varchar(100)**/
	private String visitSite;



	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setBrowseId(String browseId){
		this.browseId = browseId;
	}

	public String getBrowseId(){
		return this.browseId;
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
