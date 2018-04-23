package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.admin.TaskInfo;

import java.util.List;

/**
 * 定时任务服务接口
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-07 9:48 AM
 * @since 1.0.0
 */
public interface TaskService {

    /**
     * 查询定时任务列表
     *
     * @return List<TaskInfo>
     */
    List<TaskInfo> list();

    /**
     * 新增任务
     *
     * @param info TaskInfo
     */
    void addJob(TaskInfo info);

    /**
     * 编辑任务
     *
     * @param info TaskInfo
     */
    void edit(TaskInfo info);

    /**
     * 删除任务
     *
     * @param jobName  job名称
     * @param jobGroup job组
     */
    void delete(String jobName, String jobGroup);

    /**
     * 暂停任务
     *
     * @param jobName  job名称
     * @param jobGroup job组
     */
    void pause(String jobName, String jobGroup);

    /**
     * 激活任务
     *
     * @param jobName  job名称
     * @param jobGroup job组
     */
    void resume(String jobName, String jobGroup);
}
