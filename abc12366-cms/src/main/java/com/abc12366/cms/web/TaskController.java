package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.IdsBo;
import com.abc12366.cms.model.bo.TaskBo;
import com.abc12366.cms.service.TaskService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务模块
 *
 * @author xieyanmao
 * @create 2017-06-27
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/task", headers = Constant.VERSION_HEAD + "=1")
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 查询定时任务列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> dataMap = new HashMap<>();
        List<TaskBo> dataList = taskService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询定时任务列表信息(无需登录)
     */
    @GetMapping(path = "/list")
    public ResponseEntity list() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("isEnable", 1);
        List<TaskBo> dataList = taskService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 新增定时任务信息
     */
    @PostMapping
    public ResponseEntity save(@RequestBody TaskBo taskBo) {
        LOGGER.info("{}", taskBo);
        //新增定时任务信息
        taskBo = taskService.save(taskBo);
        LOGGER.info("{}", taskBo);
        return ResponseEntity.ok(Utils.kv("data", taskBo));
    }

    /**
     * 查询单个定时任务详细
     */
    @GetMapping(path = "/{taskId}")
    public ResponseEntity selectOne(@PathVariable String taskId) {
        LOGGER.info("{}", taskId);
        //查询定时任务信息
        TaskBo taskBo = taskService.selectTask(taskId);
        LOGGER.info("{}", taskBo);
        return ResponseEntity.ok(Utils.kv("data", taskBo));
    }

    /**
     * 更新定时任务信息
     */
    @PutMapping(path = "/{taskId}")
    public ResponseEntity update(@PathVariable String taskId,
                                 @Valid @RequestBody TaskBo taskBo) {

        LOGGER.info("{}", taskBo);
        //更新定时任务信息
        taskBo = taskService.update(taskBo);
        LOGGER.info("{}", taskBo);
        return ResponseEntity.ok(Utils.kv("data", taskBo));
    }

    /**
     * 删除定时任务信息
     */
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity delete(@PathVariable String taskId) {
        LOGGER.info("{}", taskId);
        //删除定时任务信息
        String rtn = taskService.delete(taskId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 批量删除定时任务信息
     */
    @PostMapping(path = "/deleteList")
    public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //删除定时任务信息
        String rtn = taskService.deleteList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }


}
