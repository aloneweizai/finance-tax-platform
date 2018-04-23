package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * 站点发布表
 **/
@SuppressWarnings("serial")
public class SiteIssueBo implements Serializable {

    /****
     * varchar(64)
     **/
    private String issueId;

    /**
     * 模板名称**varchar(100)
     **/
    private String templateName;

    /**
     * 操作人ID**varchar(100)
     **/
    private String userId;

    /**
     * 操作人姓名**varchar(100)
     **/
    private String userName;

    /**
     * 发布状态**varchar(100)
     **/
    private String issueState;

    /**
     * 发布时间**date
     **/
    private java.util.Date issueDate;

    /**
     * 开始时间**date
     **/
    private java.util.Date startDate;

    /**
     * 版本号**varchar(50)
     **/
    private String versions;

    /**
     * 修改时间**date
     **/
    private java.util.Date updateDate;


    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIssueState() {
        return this.issueState;
    }

    public void setIssueState(String issueState) {
        this.issueState = issueState;
    }

    public java.util.Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(java.util.Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

}
