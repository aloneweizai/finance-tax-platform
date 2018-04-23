package com.abc12366.uc.service;

import com.abc12366.uc.model.TodoTask;
import com.abc12366.uc.model.TodoTaskFront;
import com.abc12366.uc.model.bo.SysTaskBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-12
 * Time: 14:43
 */
public interface TodoTaskService {
    /**
     * 插入代办任务
     *
     * @param todoTask {@linkplain com.abc12366.uc.model.TodoTask}
     */
    void insert(TodoTask todoTask);

    /**
     * 用户首次登录后生成用户日常任务
     *
     * @param userId 用户ID
     */
    void generateTodoTaskList(String userId, String type);

    /**
     * 查询用户代办任务列表
     *
     * @param type 任务类型
     * @param userId 用户ID
     * @return  List<TodoTask> {@linkplain com.abc12366.uc.model.TodoTask}
     */
    List<TodoTask> selectList(@Param("type") String type, @Param("userId") String userId);

    /**
     * 查询用户当天一项任务
     *
     * @param userId 用户ID
     * @param sysTaskId 系统任务ID
     * @return TodoTask {@linkplain com.abc12366.uc.model.TodoTask}
     */
    TodoTask selectOne(@Param("userId") String userId, @Param("sysTaskId") String sysTaskId);

    /**
     * 用户完成任务记日志方法，并且计算奖励
     *
     * @param userId 用户ID
     * @param taskCode 系统任务编码
     */
    void doTask(String userId, String taskCode);

    /**
     * 用户完成任务记日志方法，不计算奖励
     * 多用于奖励规则比较复杂需要单做的业务方法里
     *
     * @param userId 用户ID
     * @param taskCode 系统任务编码
     */
    boolean doTaskWithouComputeAward(String userId, String taskCode);

    /**
     * 更新用户当天一项任务
     *
     * @param todoTask {@linkplain com.abc12366.uc.model.TodoTask}
     */
    void update(TodoTask todoTask);

    /**
     * 计算用户做任务获得的奖励
     *
     * @param userId 用户ID
     * @param todoTask {@linkplain com.abc12366.uc.model.TodoTask}
     */
    void computeAward(String userId, TodoTask todoTask);

    /**
     * 生成用户所有待办任务
     * @param userId 用户ID
     */
    void generateAllTodoTaskList(String userId);

    /**
     * 查询用户日常任务列表
     * @param userId 用户ID
     * @return List<TodoTaskFront> {@linkplain com.abc12366.uc.model.TodoTaskFront}
     */
    List<TodoTaskFront> selectNormalTaskList(String userId);

    /**
     * 查询用户一次性任务列表
     * @param userId 用户ID
     * @return List<TodoTaskFront> {@linkplain com.abc12366.uc.model.TodoTaskFront}
     */
    List<TodoTaskFront> selectOnetimeTaskList(String userId);

    /**
     * 查询用户特殊任务列表
     * @param userId 用户ID
     * @return List<TodoTaskFront> {@linkplain com.abc12366.uc.model.TodoTaskFront}
     */
    List<TodoTaskFront> selectSpecialTaskList(String userId);

    /**
     * 生成一条待办任务
     * @param userId 用户ID
     * @param sysTaskBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    void generateOneTodoTask(String userId, SysTaskBO sysTaskBO);

    /**
     * 查询用户帮帮任务列表
     * @param userId 用户ID
     * @return List<TodoTaskFront> {@linkplain com.abc12366.uc.model.TodoTaskFront}
     */
    List<TodoTaskFront> selectBangbangTaskList(String userId);

    
	int tasksArchiving();
}
