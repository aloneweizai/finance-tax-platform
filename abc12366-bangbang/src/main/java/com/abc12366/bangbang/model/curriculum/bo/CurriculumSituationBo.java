package com.abc12366.bangbang.model.curriculum.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 课程授课情况
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumSituationBo implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**课程标题**varchar(200)**/
    private String title;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

	/**授课方式**varchar(64)**/
	private String teachingMethod;

	/**人数限制**int(11)**/
	private Integer peopleLimit;

    /**报名时间起**datetime**/
    private Date applyTimeBegin;

    /**报名时间止**datetime**/
    private Date applyTimeEnd;

    /**签到时间起**datetime**/
    private Date signTimeBegin;

    /**签到时间止**datetime**/
    private Date signTimeEnd;

    /**累计收入**double**/
    private Double generalIncome;

    /**累计积分**double**/
    private Double aggregateScore;

    /**报名总数**double**/
    private Integer applyNum;

    /**签到总数**double**/
    private Integer signNum;

    /**订单总数**double**/
    private Integer orderNum;

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public Date getApplyTimeBegin() {
        return applyTimeBegin;
    }

    public void setApplyTimeBegin(Date applyTimeBegin) {
        this.applyTimeBegin = applyTimeBegin;
    }

    public Date getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(Date applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public Date getSignTimeBegin() {
        return signTimeBegin;
    }

    public void setSignTimeBegin(Date signTimeBegin) {
        this.signTimeBegin = signTimeBegin;
    }

    public Date getSignTimeEnd() {
        return signTimeEnd;
    }

    public void setSignTimeEnd(Date signTimeEnd) {
        this.signTimeEnd = signTimeEnd;
    }

    public Double getGeneralIncome() {
        return generalIncome;
    }

    public void setGeneralIncome(Double generalIncome) {
        this.generalIncome = generalIncome;
    }

    public Double getAggregateScore() {
        return aggregateScore;
    }

    public void setAggregateScore(Double aggregateScore) {
        this.aggregateScore = aggregateScore;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

}
