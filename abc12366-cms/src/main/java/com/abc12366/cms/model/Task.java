package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * 任务表
 **/
@SuppressWarnings("serial")
public class Task implements Serializable {

    /**
     * 任务ID**varchar(64)
     **/
    private String taskId;

    /**
     * 任务执行代码**varchar(255)
     **/
    private String taskCode;

    /**
     * 任务类型(1首页静态化、2栏目页静态化、3内容页静态化、4采集、5分发)**tinyint(1)
     **/
    private Integer taskType;

    /**
     * 任务名称**varchar(255)
     **/
    private String taskName;

    /**
     * 任务类**varchar(255)
     **/
    private String jobClass;

    /**
     * 执行周期分类(1非表达式 2 cron表达式)**tinyint(1)
     **/
    private Integer execycle;

    /**
     * 每月的哪天**int(11)
     **/
    private Integer dayOfMonth;

    /**
     * 周几**tinyint(1)
     **/
    private Integer dayOfWeek;

    /**
     * 小时**int(11)
     **/
    private Integer hour;

    /**
     * 分钟**int(11)
     **/
    private Integer minute;

    /**
     * 间隔小时**int(11)
     **/
    private Integer intervalHour;

    /**
     * 间隔分钟**int(11)
     **/
    private Integer intervalMinute;

    /**
     * 1分钟、2小时、3日、4周、5月**tinyint(1)
     **/
    private Integer taskIntervalUnit;

    /**
     * 规则表达式**varchar(255)
     **/
    private String cronExpression;

    /**
     * 是否启用**tinyint(1)
     **/
    private Integer isEnable;

    /**
     * 任务说明**varchar(255)
     **/
    private String taskRemark;

    /**
     * 站点**varchar(64)
     **/
    private String siteId;

    /**
     * 创建者**varchar(64)
     **/
    private String userId;

    /**
     * 创建时间**datetime
     **/
    private java.util.Date createTime;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return this.taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Integer getTaskType() {
        return this.taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getJobClass() {
        return this.jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public Integer getExecycle() {
        return this.execycle;
    }

    public void setExecycle(Integer execycle) {
        this.execycle = execycle;
    }

    public Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getHour() {
        return this.hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getIntervalHour() {
        return this.intervalHour;
    }

    public void setIntervalHour(Integer intervalHour) {
        this.intervalHour = intervalHour;
    }

    public Integer getIntervalMinute() {
        return this.intervalMinute;
    }

    public void setIntervalMinute(Integer intervalMinute) {
        this.intervalMinute = intervalMinute;
    }

    public Integer getTaskIntervalUnit() {
        return this.taskIntervalUnit;
    }

    public void setTaskIntervalUnit(Integer taskIntervalUnit) {
        this.taskIntervalUnit = taskIntervalUnit;
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getTaskRemark() {
        return this.taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

}
