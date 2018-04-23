package com.abc12366.cms.model.questionnaire.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * 答题表
 **/
@SuppressWarnings("serial")
public class AnswertjBO implements Serializable {

    /**
     * 答题开始时间
     **/
    private java.util.Date startTime;

    /**
     * 答题结束时间
     **/
    private java.util.Date endTime;

    /**
     * 答案内容
     **/
    private String content;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
