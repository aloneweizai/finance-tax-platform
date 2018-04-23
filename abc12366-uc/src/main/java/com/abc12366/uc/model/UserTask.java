package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 11:43
 */
public class UserTask {
    private String id;
    private String userId;
    private String taskId;
    private boolean status;
    private Date createTime;
    private Date lastUpdate;

    public UserTask() {
    }

    public UserTask(String id, String userId, String taskId, boolean status, Date createTime, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
