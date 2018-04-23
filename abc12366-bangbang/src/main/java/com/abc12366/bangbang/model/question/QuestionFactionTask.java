package com.abc12366.bangbang.model.question;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 邦派任务表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionTask implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**时间(年月)**int(10)**/
	private Integer taskTime;

	/**任务数量**int(10)**/
	private Integer taskNum;

	/**任务完成数量**int(10)**/
	private Integer taskFinishNum;

    /**邦派任务类型**varchar(64)**/
    private String taskType;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date updateTime;

    /**完成时间**datetime**/
    private java.util.Date finishTime;

    /**是否完成**int(10)**/
    private Integer isFinish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public Integer getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Integer taskTime) {
        this.taskTime = taskTime;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getTaskFinishNum() {
        return taskFinishNum;
    }

    public void setTaskFinishNum(Integer taskFinishNum) {
        this.taskFinishNum = taskFinishNum;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }
}
