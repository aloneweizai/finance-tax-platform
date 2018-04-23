package com.abc12366.bangbang.model.curriculum.bo;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 章节表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumChapterBo implements Serializable {

	/**章节ID**varchar(64)**/
	private String chapterId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**章节名称**varchar(100)**/
	private String chapterName;

	/**章节顺序**tinyint(4)**/
	private Integer chapterSeq;

    /**课件**/
    private List<CurriculumCoursewareBo> coursewareList;



	public void setChapterId(String chapterId){
		this.chapterId = chapterId;
	}

	public String getChapterId(){
		return this.chapterId;
	}

	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setChapterName(String chapterName){
		this.chapterName = chapterName;
	}

	public String getChapterName(){
		return this.chapterName;
	}

	public void setChapterSeq(Integer chapterSeq){
		this.chapterSeq = chapterSeq;
	}

	public Integer getChapterSeq(){
		return this.chapterSeq;
	}

    public List<CurriculumCoursewareBo> getCoursewareList() {
        return coursewareList;
    }

    public void setCoursewareList(List<CurriculumCoursewareBo> coursewareList) {
        this.coursewareList = coursewareList;
    }
}
