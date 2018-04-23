package com.abc12366.uc.model.bo;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 15:18
 */
public class UserTaskUpdateBO {
    private String taskId;
    private Boolean status;

    public UserTaskUpdateBO() {
    }

    public UserTaskUpdateBO(String taskId, Boolean status) {
        this.taskId = taskId;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserTaskUpdateBO{" +
                "taskId='" + taskId + '\'' +
                ", status=" + status +
                '}';
    }
}
