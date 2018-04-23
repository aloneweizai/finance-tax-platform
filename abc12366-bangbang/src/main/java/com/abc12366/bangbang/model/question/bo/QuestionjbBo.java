package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 问题表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionjbBo implements Serializable {

	/**来源ID**varchar(64)**/
    private String sourceId;

    /**来源类型**varchar(64)**/
    private String sourceType;

	/**问题**varchar(300)**/
	private String title;

	/**内容**longtext**/
	private String answer;

	/**问题状态**varchar(20)**/
	private String status;

	/**创建时间**datetime**/
	private Date createTime;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
