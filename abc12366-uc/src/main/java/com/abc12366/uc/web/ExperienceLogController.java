package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.ExpLogUcBO;
import com.abc12366.uc.model.bo.ExperienceLogBO;
import com.abc12366.uc.model.bo.ExperienceLogQueryBO;
import com.abc12366.uc.service.ExperienceLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 经验值日志接口控制器
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 14:38
 */
@RestController
@RequestMapping(path = "/uexp/log", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ExperienceLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceLogController.class);

    @Autowired
    private ExperienceLogService experienceLogService;

    /**
     * 新增经验值日志
     * @param experienceLogBO
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody ExperienceLogBO experienceLogBO) {
        LOGGER.info("{}", experienceLogBO);
        ExperienceLogQueryBO experienceLogBOReturn = experienceLogService.insert(experienceLogBO);
        LOGGER.info("{}", experienceLogBOReturn);
        return ResponseEntity.ok(Utils.kv("data", experienceLogBOReturn));
    }

    /**
     * 查询经验值日志列表
     * @param name
     * @param code
     * @param type
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String code,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String userId,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {

        LOGGER.info("{}:{}:{}:{}:{}:{}", name, code, type, userId, page, size);
        Map<String, Object> map = new HashMap<>();
        if (name != null && StringUtils.isEmpty(name)) {
            name = null;
        }
        if (code != null && StringUtils.isEmpty(code)) {
            code = null;
        }
        if (type != null && StringUtils.isEmpty(type)) {
            type = null;
        }
        if (userId != null && StringUtils.isEmpty(userId)) {
            userId = null;
        }
        map.put("name", name);
        map.put("code", code);
        map.put("type", type);
        map.put("userId", userId);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ExperienceLogQueryBO> logList = experienceLogService.selectList(map);
        LOGGER.info("{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }

    /**
     * 用户登录后查询用户经验值日志列表
     * @param start
     * @param end
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping(path = "/user")
    public ResponseEntity selectListByUser(@RequestParam(required = false) String start,
                                           @RequestParam(required = false) String end,
                                           HttpServletRequest request,
                                           @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}", start, end, request, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> map = new HashMap<>();
        if (start != null && "".equals(start.trim())) {
            start = null;
        }
        if (end != null && "".equals(end.trim())) {
            end = null;
        }
        Date startDate = null;
        Date endDate = null;
        if (start != null) {
            startDate = DateUtils.strToDate(start);
        }
        if (end != null) {
            Date endDateTmp = DateUtils.strToDate(end);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDateTmp);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            endDate = calendar.getTime();
        }

        map.put("userId", Utils.getUserId(request));
        map.put("start", startDate);
        map.put("end", endDate);

        List<ExpLogUcBO> logList = experienceLogService.selectListByUser(map);
        LOGGER.info("{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }
}
