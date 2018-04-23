package com.abc12366.uc.web.admin;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.TaskInfo;
import com.abc12366.uc.service.admin.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 任务管理
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-07 9:42 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/admin/cron", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TaskManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManageController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 任务列表
     *
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList() {

        List<TaskInfo> dataList = taskService.list();
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", dataList));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 保存定时任务
     *
     * @param info TaskInfo
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody TaskInfo info) {
        LOGGER.info("{}", info);

        if (info.getId() == 0) {
            taskService.addJob(info);
        } else {
            taskService.edit(info);
        }
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 删除定时任务
     *
     * @param jobName  String
     * @param jobGroup String
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{jobName}/{jobGroup}")
    public ResponseEntity delete(@PathVariable String jobName, @PathVariable String jobGroup) {
        LOGGER.info("{}, {}", jobName, jobGroup);

        taskService.delete(jobName, jobGroup);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 暂停定时任务
     *
     * @param jobName  String
     * @param jobGroup String
     * @return ResponseEntity
     */
    @GetMapping(value = "/pause/{jobName}/{jobGroup}")
    public ResponseEntity pause(@PathVariable String jobName, @PathVariable String jobGroup) {
        LOGGER.info("{}, {}", jobName, jobGroup);

        taskService.pause(jobName, jobGroup);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 重新开始定时任务
     *
     * @param jobName  String
     * @param jobGroup String
     * @return ResponseEntity
     */
    @GetMapping(value = "/resume/{jobName}/{jobGroup}")
    public ResponseEntity resume(@PathVariable String jobName, @PathVariable String jobGroup) {
        LOGGER.info("{}, {}", jobName, jobGroup);

        taskService.resume(jobName, jobGroup);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
