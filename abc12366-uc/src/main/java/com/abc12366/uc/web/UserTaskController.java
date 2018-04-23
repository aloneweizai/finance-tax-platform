package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.MyTaskSurvey;
import com.abc12366.uc.model.bo.MyTaskBO;
import com.abc12366.uc.service.UserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 11:39
 */
@RestController
@RequestMapping(path = "/task", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserTaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VipPrivilegeController.class);

    @Autowired
    private UserTaskService userTaskService;

    //我的任务统计：UC我的任务模块顶排统计列表
    @GetMapping(path = "/mytask/{userId}")
    public ResponseEntity selectMyTask(@PathVariable String userId) {
        LOGGER.info("{}", userId);
        MyTaskBO myTaskBO = userTaskService.selectMyTask(userId);
        LOGGER.info("{}", myTaskBO);
        return ResponseEntity.ok(Utils.kv("data", myTaskBO));
    }

    //我的任务概况：UC、模块顶排用户任务概况展示，包括本月完成任务获取的经验值、积分，以及本月完成任务数量
    @GetMapping(path = "/mytask/survey/{userId}")
    public ResponseEntity selectMyTaskSurvey(@PathVariable String userId) {
        LOGGER.info("{}", userId);
        MyTaskSurvey myTaskBO = userTaskService.selectMyTaskSurvey(userId);
        LOGGER.info("{}", myTaskBO);
        return ResponseEntity.ok(Utils.kv("data", myTaskBO));
    }

//    @PostMapping(path = "/{userId}")
//    public ResponseEntity insert(@Valid @RequestBody UserTaskInsertBO userTaskInsertBO, @PathVariable String userId) {
//        LOGGER.info("{}:{}", userTaskInsertBO, userId);
//        UserTaskBO userTaskBO = userTaskService.insert(userTaskInsertBO, userId);
//        LOGGER.info("{}", userTaskBO);
//        return ResponseEntity.ok(Utils.kv("data", userTaskBO));
//    }
//
//    @PutMapping(path = "/{userId}/{id}")
//    public ResponseEntity update(@RequestBody UserTaskUpdateBO userTaskUpdateBO, @PathVariable String userId,
//                                 @PathVariable String id) {
//        LOGGER.info("{}:{}:{}", userTaskUpdateBO, userId, id);
//        UserTaskBO userTaskBO = userTaskService.update(userTaskUpdateBO, userId, id);
//        LOGGER.info("{}", userTaskBO);
//        return ResponseEntity.ok(Utils.kv("data", userTaskBO));
//    }
//
//    @DeleteMapping(path = "/{userId}/{id}")
//    public ResponseEntity delete(@PathVariable String userId, @PathVariable String id) {
//        LOGGER.info("{}:{}", userId, id);
//        Map<String, String> map = new HashMap<>();
//        map.put("userId", userId);
//        map.put("id", id);
//        userTaskService.delete(map);
//        return ResponseEntity.ok(Utils.kv());
//    }
}
