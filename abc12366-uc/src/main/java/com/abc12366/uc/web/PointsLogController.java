package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsLogUcBO;
import com.abc12366.uc.service.PointsLogService;
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
 * 积分规则日志接口
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:22
 */
@RestController
@RequestMapping(path = "/upoints/log", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class PointsLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsLogController.class);

    @Autowired
    private PointsLogService pointsLogService;

    /**
     * 积分日志列表
     * @param name
     * @param code
     * @param type
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String name, @RequestParam(required = false)
    String code,
                                     @RequestParam(required = false) String type, @RequestParam(required = false)
                                         String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}:{}", name, code, type, userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, String> map = new HashMap<>();
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
        List<PointsLogBO> logList = pointsLogService.selectList(map);
        LOGGER.info("{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }

    /**
     * 新增积分日志
     * @param pointsLogBO
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody PointsLogBO pointsLogBO) {
        LOGGER.info("{}", pointsLogBO);
        PointsLogBO points_log = pointsLogService.insert(pointsLogBO);
        LOGGER.info("{}", points_log);
        return ResponseEntity.ok(Utils.kv("data", points_log));
    }

    /**
     * 查询当前用户
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

        List<PointsLogUcBO> logList = pointsLogService.selectListByUser(map);
        LOGGER.info("{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }

}
