package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 章节课件关联表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumChapterCourseware implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

	/**章节ID**varchar(64)**/
	private String chapterId;

	/**课件ID**varchar(64)**/
	private String coursewareId;

	/**课件顺序**tinyint(4)**/
	private Integer coursewareSeq;



	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

	public void setChapterId(String chapterId){
		this.chapterId = chapterId;
	}

	public String getChapterId(){
		return this.chapterId;
	}

	public void setCoursewareId(String coursewareId){
		this.coursewareId = coursewareId;
	}

	public String getCoursewareId(){
		return this.coursewareId;
	}

	public void setCoursewareSeq(Integer coursewareSeq){
		this.coursewareSeq = coursewareSeq;
	}

	public Integer getCoursewareSeq(){
		return this.coursewareSeq;
	}

}
