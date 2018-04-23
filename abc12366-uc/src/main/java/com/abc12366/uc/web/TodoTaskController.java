package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.TodoTask;
import com.abc12366.uc.model.TodoTaskFront;
import com.abc12366.uc.service.TodoTaskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 待办任务接口控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-12
 * Time: 14:36
 */
@RestController
@RequestMapping(path = "/todo/task", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TodoTaskController {
    private static Logger LOGGER = LoggerFactory.getLogger(TodoTaskController.class);

    @Autowired
    private TodoTaskService todoTaskService;

    /**
     * 根据系统任务类型查询用户待办任务列表
     * @param type 系统任务类型
     * @param userId 用户ID
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.TodoTask}
     */
    @GetMapping(path = "/{type}/{userId}")
    public ResponseEntity selectList(@PathVariable("type") String type, @PathVariable("userId") String userId) {
        LOGGER.info("{}:{}", type, userId);
        List<TodoTask> taskList = todoTaskService.selectList(type, userId);
        return ResponseEntity.ok(Utils.kv("dataList", taskList));
    }

    /**
     * 查询用户日常任务列表
     * @param userId 用户ID
     * @param page 页数
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.TodoTask}
     */
    @GetMapping(path = "/normal")
    public ResponseEntity selectNormalTaskList(@RequestParam(value = "userId") String userId,
                                               @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                               @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TodoTaskFront> taskList = todoTaskService.selectNormalTaskList(userId);
        return (taskList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) taskList, "total", ((Page) taskList).getTotal()));
    }

    /**
     * 查询用户一次性（多为成长任务）任务列表
     * @param userId 用户ID
     * @param page 页数
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.TodoTask}
     */
    @GetMapping(path = "/onetime")
    public ResponseEntity selectOnetimeTaskList(@RequestParam(value = "userId") String userId,
                                                @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TodoTaskFront> taskList = todoTaskService.selectOnetimeTaskList(userId);
        return (taskList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) taskList, "total", ((Page) taskList).getTotal()));
    }

    /**
     * 查询用户日常任务列表
     * @param userId 用户ID
     * @param page 页数
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.TodoTask}
     */
    @GetMapping(path = "/special")
    public ResponseEntity selectSpecialTaskList(@RequestParam(value = "userId") String userId,
                                                @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TodoTaskFront> taskList = todoTaskService.selectSpecialTaskList(userId);
        return (taskList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) taskList, "total", ((Page) taskList).getTotal()));
    }

    /**
     * 查询用户帮帮任务列表
     * @param userId 用户ID
     * @param page 页数
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.TodoTask}
     */
    @GetMapping(path = "/bangbang")
    public ResponseEntity selectBangbangTaskList(@RequestParam(value = "userId") String userId,
                                                @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TodoTaskFront> taskList = todoTaskService.selectBangbangTaskList(userId);
        return (taskList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) taskList, "total", ((Page) taskList).getTotal()));
    }

    /**
     * 用户做任务接口：做任务并且计算奖励，用于用户业务操作任务埋点，
     * @param userId 用户ID
     * @param taskCode 系统任务编码 参考：{@linkplain TaskConstant}
     */
    @PostMapping(path = "/do/award/{userId}/{taskCode}")
    public ResponseEntity doTaskAward(@PathVariable("userId") String userId, @PathVariable("taskCode") String taskCode) {
        LOGGER.info("用户做任务,用户ID：{}，任务编码：{}", userId, taskCode);
        todoTaskService.doTask(userId, taskCode);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 用户做任务接口：做任务不计算奖励，用于用户业务操作任务埋点，多用于奖励规则比较复杂需要单做的业务
     * @param userId 用户ID
     * @param taskCode 系统任务编码
     */
    @PostMapping(path = "/do/noaward/{userId}/{taskCode}")
    public ResponseEntity doTaskNoAward(@PathVariable("userId") String userId, @PathVariable("taskCode") String taskCode) {
        todoTaskService.doTaskWithouComputeAward(userId, taskCode);
        return ResponseEntity.ok(Utils.kv());
    }
}
