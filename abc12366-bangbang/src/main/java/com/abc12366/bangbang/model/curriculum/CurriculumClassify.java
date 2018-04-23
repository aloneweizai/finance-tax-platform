package com.abc12366.bangbang.model.curriculum;
import java.io.Serializable;


/**
 * 
 * 课程收藏表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumClassify implements Serializable {

	/**分类ID**varchar(64)**/
	private String classifyId;

	/**父分类ID**varchar(64)**/
	private String parentId;

    /**分类名称**varchar(100)**/
    private String classifyName;

	/**排列顺序**int**/
	private Integer priority;

    /**是否显示**int**/
    private Integer isDisplay;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }
}
