package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.PrivilegeLog;
import com.abc12366.uc.service.PrivilegeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员权益日志接口控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-25
 * Time: 11:38
 */
@RestController
@RequestMapping(path = "/privilege/log", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class PrivilegeLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeLogController.class);

    @Autowired
    private PrivilegeLogService privilegeLogService;

    /**
     * 新增一条日志
     * @param privilegeLog
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody PrivilegeLog privilegeLog){
        LOGGER.info("{}", privilegeLog);
        PrivilegeLog log = privilegeLogService.insert(privilegeLog);
        return ResponseEntity.ok(Utils.kv("data", log));
    }

    /**
     * 查询用户月度权益日志列表
     * @param logType
     * @param userId
     * @return
     */
    @GetMapping(path = "/month/{logType}/{userId}")
    public ResponseEntity selectListMonth(@PathVariable String logType, @PathVariable String userId){
        LOGGER.info("{}:{}", logType, userId);
        List<PrivilegeLog> logList = privilegeLogService.selectListMonth(logType, userId);
        return ResponseEntity.ok(Utils.kv("dataList", logList));
    }

    /**
     * 查询用户年度权益日志列表
     * @param logType
     * @param userId
     * @return
     */
    @GetMapping(path = "/year/{logType}/{userId}")
    public ResponseEntity selectListYear(@PathVariable String logType, @PathVariable String userId){
        LOGGER.info("{}:{}", logType, userId);
        List<PrivilegeLog> logList = privilegeLogService.selectListYear(logType, userId);
        return ResponseEntity.ok(Utils.kv("dataList", logList));
    }

    /**
     * 查询用户所有权益日志
     * @param logType
     * @param userId
     * @return
     */
    @GetMapping(path = "/{logType}/{userId}")
    public ResponseEntity selectList(@PathVariable String logType, @PathVariable String userId){
        LOGGER.info("{}:{}", logType, userId);
        List<PrivilegeLog> logList = privilegeLogService.selectList(logType, userId);
        return ResponseEntity.ok(Utils.kv("dataList", logList));
    }
}
