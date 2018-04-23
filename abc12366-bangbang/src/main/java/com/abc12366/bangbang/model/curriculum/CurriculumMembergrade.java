package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程会员等级关联表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumMembergrade implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**会员等级**varchar(64)**/
	private String memberGrade;

    /**会员等级名称****/
    private String memberGradeName;



	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setMemberGrade(String memberGrade){
		this.memberGrade = memberGrade;
	}

	public String getMemberGrade(){
		return this.memberGrade;
	}

    public String getMemberGradeName() {
        return memberGradeName;
    }

    public void setMemberGradeName(String memberGradeName) {
        this.memberGradeName = memberGradeName;
    }
}
