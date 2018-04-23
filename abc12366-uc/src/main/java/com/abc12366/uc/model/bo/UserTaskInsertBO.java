package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 11:48
 */
public class UserTaskInsertBO {
    @NotEmpty
    private String taskId;
    @NotNull
    private Boolean status;

    public UserTaskInsertBO() {
    }

    public UserTaskInsertBO(String taskId, Boolean status) {
        this.taskId = taskId;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "UserTaskInsertBO{" +
                "taskId='" + taskId + '\'' +
                ", status=" + status +
                '}';
    }
}
