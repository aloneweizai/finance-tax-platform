package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容审核信息表
 **/
@SuppressWarnings("serial")
public class ContentCheck implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 审核步数**tinyint(4)
     **/
    private Integer checkStep;

    /**
     * 审核意见**varchar(255)
     **/
    private String checkOpinion;

    /**
     * 是否退回**tinyint(1)
     **/
    private Integer isRejected;

    /**
     * reviewer**int(11)
     **/
    private Integer reviewer;

    /**
     * 终审时间**datetime
     **/
    private java.util.Date checkDate;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getCheckStep() {
        return this.checkStep;
    }

    public void setCheckStep(Integer checkStep) {
        this.checkStep = checkStep;
    }

    public String getCheckOpinion() {
        return this.checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public Integer getIsRejected() {
        return this.isRejected;
    }

    public void setIsRejected(Integer isRejected) {
        this.isRejected = isRejected;
    }

    public Integer getReviewer() {
        return this.reviewer;
    }

    public void setReviewer(Integer reviewer) {
        this.reviewer = reviewer;
    }

    public java.util.Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(java.util.Date checkDate) {
        this.checkDate = checkDate;
    }

}
