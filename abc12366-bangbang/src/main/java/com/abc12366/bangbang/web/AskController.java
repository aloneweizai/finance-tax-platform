package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.AskLog;
import com.abc12366.bangbang.model.bo.*;
import com.abc12366.bangbang.service.AskLogService;
import com.abc12366.bangbang.service.AskService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 15:51
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AskController.class);

    @Autowired
    AskService askService;

    @Autowired
    private AskLogService askLogService;

    @GetMapping(path = "/asks")
    public ResponseEntity selectListForAdmin(@RequestParam(required = false) String ask,
                                             @RequestParam(required = false) String detail,
                                             @RequestParam(required = false) String type,
                                             @RequestParam(required = false) String status,
                                             @RequestParam(required = false) Boolean isSolve,
                                             @RequestParam(required = false) String userId,
                                             @RequestParam(required = false) String askedUserId,
                                             @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}:{}:{}:{}:{}", ask, detail, type, status, isSolve, userId, askedUserId, page, size);
        if (ask != null && StringUtils.isEmpty(ask)) {
            ask = null;
        }
        if (detail != null && StringUtils.isEmpty(detail)) {
            detail = null;
        }
        if (type != null && StringUtils.isEmpty(type)) {
            type = null;
        }
        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        if (isSolve != null && StringUtils.isEmpty(isSolve)) {
            isSolve = null;
        }
        if (userId != null && StringUtils.isEmpty(userId)) {
            userId = null;
        }
        if (askedUserId != null && StringUtils.isEmpty(askedUserId)) {
            askedUserId = null;
        }
        AsksQueryParamBO asksQueryParamBO = new AsksQueryParamBO(ask, detail, type, status, isSolve, userId,
                askedUserId);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<AskBO> askList = askService.selectListForAdmin(asksQueryParamBO);
        LOGGER.info("{}", askList);
        return (askList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) askList, "total", ((Page) askList).getTotal()));
    }

    @GetMapping(path = "/ask")
    public ResponseEntity selectListForUser(@RequestParam(required = false) String ask,
                                            @RequestParam(required = false) String detail,
                                            @RequestParam(required = false) String type,
                                            @RequestParam(required = false) Boolean isSolve,
                                            @RequestParam(required = false) String userId,
                                            @RequestParam(required = false) String askedUserId,
                                            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}:{}:{}:{}", ask, detail, type, isSolve, userId, askedUserId, page, size);
        if (ask != null && StringUtils.isEmpty(ask)) {
            ask = null;
        }
        if (detail != null && StringUtils.isEmpty(detail)) {
            detail = null;
        }
        if (type != null && StringUtils.isEmpty(type)) {
            type = null;
        }
        if (isSolve != null && StringUtils.isEmpty(isSolve)) {
            isSolve = null;
        }
        if (userId != null && StringUtils.isEmpty(userId)) {
            userId = null;
        }
        if (askedUserId != null && StringUtils.isEmpty(askedUserId)) {
            askedUserId = null;
        }
        AskQueryParamBO askQueryParamBO = new AskQueryParamBO(ask, detail, type, isSolve, userId, askedUserId);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<AskBO> askList = askService.selectListForUser(askQueryParamBO);
        LOGGER.info("{}", askList);
        return (askList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) askList, "total", ((Page) askList).getTotal()));
    }

    @PostMapping(path = "/ask")
    public ResponseEntity insert(@Valid @RequestBody AskInsertBO askInsertBO) {
        LOGGER.info("{}", askInsertBO);
        AskBO askBO = askService.insert(askInsertBO);
        LOGGER.info("{}", (askBO == null) ? null : askBO);
        return ResponseEntity.ok(Utils.kv("data", askBO));
    }

    @GetMapping(path = "/ask/{id}")
    public ResponseEntity selectOne(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        AskBO askBO = askService.selectOne(id);

        //记日志
        String userId = (String) request.getAttribute(Constant.USER_ID);
        AskLog askLog = new AskLog();
        askLog.setId(Utils.uuid());
        askLog.setAskId(id);
        askLog.setCreateTime(new Date());
        if (userId != null && !userId.trim().equals("")) {
            askLog.setUserId(userId);
        }
        askLogService.insert(askLog);

        LOGGER.info("{}", (askBO == null) ? null : askBO);
        return ResponseEntity.ok(Utils.kv("data", askBO));
    }

    @PutMapping(path = "/ask/{id}")
    public ResponseEntity update(@PathVariable String id, @Valid @RequestBody AskUpdateBO askUpdateBO,
                                 HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = request.getHeader(Constant.USER_TOKEN_HEAD);
        AskBO askBO = askService.update(id, askUpdateBO, userId);
        LOGGER.info("{}", (askBO == null) ? null : askBO);
        return ResponseEntity.ok(Utils.kv("data", askBO));
    }

    @DeleteMapping(path = "/ask/{id}")
    public ResponseEntity delete(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = request.getHeader(Constant.USER_TOKEN_HEAD);
        askService.delete(id, userId);
        return ResponseEntity.ok(Utils.kv());
    }

    @PutMapping(path = "/block/ask/{id}")
    public ResponseEntity block(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id);
        String userId = request.getHeader(Constant.USER_TOKEN_HEAD);
        askService.block(id, userId);
        return ResponseEntity.ok(Utils.kv());
    }
}
