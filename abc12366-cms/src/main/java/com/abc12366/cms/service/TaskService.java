package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.TaskBo;

import java.util.List;
import java.util.Map;

public interface TaskService {

    List<TaskBo> selectList(Map<String, Object> map);

    TaskBo save(TaskBo taskBo);

    TaskBo selectTask(String taskId);

    TaskBo update(TaskBo taskBo);

    String delete(String taskId);

    String deleteList(String[] taskIds);

}
