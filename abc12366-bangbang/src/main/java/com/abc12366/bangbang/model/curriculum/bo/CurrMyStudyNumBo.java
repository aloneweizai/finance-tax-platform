package com.abc12366.bangbang.model.curriculum.bo;

import java.io.Serializable;


/**
 * 
 * 课程学习统计
 * 
 **/
@SuppressWarnings("serial")
public class CurrMyStudyNumBo implements Serializable {

    /**本月学习课程数**/
    private Integer studyNumMonth;

    /**本年学习课程数**/
    private Integer studyNumYear;

    /**学习勤奋榜**/
    private String studyQfb;

    public Integer getStudyNumMonth() {
        return studyNumMonth;
    }

    public void setStudyNumMonth(Integer studyNumMonth) {
        this.studyNumMonth = studyNumMonth;
    }

    public Integer getStudyNumYear() {
        return studyNumYear;
    }

    public void setStudyNumYear(Integer studyNumYear) {
        this.studyNumYear = studyNumYear;
    }

    public String getStudyQfb() {
        return studyQfb;
    }

    public void setStudyQfb(String studyQfb) {
        this.studyQfb = studyQfb;
    }
}
