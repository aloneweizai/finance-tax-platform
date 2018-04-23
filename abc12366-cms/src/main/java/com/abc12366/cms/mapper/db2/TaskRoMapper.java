package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Task;
import com.abc12366.cms.model.bo.TaskBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TaskMapper数据库操作接口类
 **/

public interface TaskRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Task selectByPrimaryKey(@Param("taskId") String taskId);

    /**
     * 查询（根据主键ID查询）
     **/
    List<TaskBo> selectList(Map<String, Object> map);

    /**
     * 查询（根据主键ID查询）
     **/
    Integer selectCnt(TaskBo taskBo);

}