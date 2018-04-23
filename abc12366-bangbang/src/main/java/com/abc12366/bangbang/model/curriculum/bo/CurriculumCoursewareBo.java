package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;


/**
 * 
 * 课件表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumCoursewareBo implements Serializable {

	/**课件ID**varchar(64)**/
	private String coursewareId;

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**章节ID**varchar(64)**/
    private String chapterId;

	/**课件类型**varchar(64)**/
	private String type;

	/**课件标题**varchar(100)**/
	private String title;

	/**上传方式**tinyint(1)**/
	private Integer uploadWay;

	/**视频链接**varchar(200)**/
	private String link;

	/**文件名称**varchar(100)**/
	private String fileName;

	/**文件地址**varchar(200)**/
	private String fileSite;

	/**文件大小**varchar(10)**/
	private String fileSize;

	/**时长**varchar(10)**/
	private String duration;

	/**是否允许下载**tinyint(1)**/
	private Integer isDownload;

    /**排列顺序**tinyint(4)**/
    private Integer coursewareSeq;

    /**创建人ID**varchar(64)**/
    private String createrId;

    /**创建人名称**varchar(30)**/
    private String createrName;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date updateTime;

    /**课程收费**tinyint(1)**/
    private Integer isFree;



	public void setCoursewareId(String coursewareId){
		this.coursewareId = coursewareId;
	}

	public String getCoursewareId(){
		return this.coursewareId;
	}

	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
	}

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

    public Integer getUploadWay() {
        return uploadWay;
    }

    public void setUploadWay(Integer uploadWay) {
        this.uploadWay = uploadWay;
    }

    public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return this.link;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName(){
		return this.fileName;
	}

	public void setFileSite(String fileSite){
		this.fileSite = fileSite;
	}

	public String getFileSite(){
		return this.fileSite;
	}

	public void setFileSize(String fileSize){
		this.fileSize = fileSize;
	}

	public String getFileSize(){
		return this.fileSize;
	}

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return this.duration;
	}

	public void setIsDownload(Integer isDownload){
		this.isDownload = isDownload;
	}

	public Integer getIsDownload(){
		return this.isDownload;
	}

    public Integer getCoursewareSeq() {
        return coursewareSeq;
    }

    public void setCoursewareSeq(Integer coursewareSeq) {
        this.coursewareSeq = coursewareSeq;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}


    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }
}
