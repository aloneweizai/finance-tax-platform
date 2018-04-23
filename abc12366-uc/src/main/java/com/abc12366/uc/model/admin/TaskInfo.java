package com.abc12366.uc.model.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 管理定时任务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-07 9:44 AM
 * @since 1.0.0
 */
public class TaskInfo {

    private int id = 0;

    /**任务名称*/
    @NotEmpty
    @Length(min = 1, max = 200)
    private String jobName;

    /**任务分组*/
    @NotEmpty
    @Length(min = 1, max = 200)
    private String jobGroup;

    /**任务描述*/
    @Length(min = 0, max = 250)
    private String jobDescription;

    /**任务状态*/
    @Length(min = 0, max = 10)
    private String jobStatus;

    /**任务表达式*/
    @NotEmpty
    @Length(min = 1, max = 120)
    private String cronExpression;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
