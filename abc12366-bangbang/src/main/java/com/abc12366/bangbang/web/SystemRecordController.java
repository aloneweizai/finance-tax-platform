package com.abc12366.bangbang.web;

/**
 * 用户日志控制器
 *
 * @author: lingsuzhi <554600654@qq.com.com>
 * @create: 2017-08-16
 * @since 1.0.0
 */

import com.abc12366.bangbang.model.SystemRecord;
import com.abc12366.bangbang.model.bo.SystemRecordBO;
import com.abc12366.bangbang.model.bo.SystemRecordInsertBO;
import com.abc12366.bangbang.service.SystemRecordService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/system/record", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SystemRecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRecordController.class);

    @Autowired
    private SystemRecordService systemRecordService;

    /**
     * 查询用户日志列表
     *
     * @param appName  使用系统
     * @param location 访问地点
     * @param yyyyMMdd 查询日期
     * @param feature  操作功能
     * @param username 用户名
     * @param page     当前页
     * @param size     每页大小
     * @return ResponseEntity SystemRecordBO实体
     * @see com.abc12366.bangbang.model.bo.SystemRecordBO
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String appName,
                                     @RequestParam(required = false) String location,
                                     @RequestParam(required = false) String yyyyMMdd,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String feature,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {

        Map<String, String> map = new HashMap<>(16);

        if (StringUtils.isNotEmpty(yyyyMMdd)) {
            map.put("yyyyMMdd", yyyyMMdd);
        } else {
            map.put("yyyyMMdd", DateUtils.getDataString());
        }
        if (StringUtils.isNotEmpty(appName)) {
            map.put("appName", appName);
        }
        if (StringUtils.isNotEmpty(location)) {
            map.put("location", location);
        }
        if (StringUtils.isNotEmpty(username)) {
            map.put("username", username);
        }
        if (StringUtils.isNotEmpty(feature)) {
            map.put("feature", feature);
        }

        List<SystemRecordBO> systemRecordList = systemRecordService.selectList(map, page, size);
        PageInfo<SystemRecordBO> pageInfo = new PageInfo<>(systemRecordList);

        LOGGER.info("{}", systemRecordList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 查看用户日志
     *
     * @param yyyyMMdd 查询日期
     * @param id       日志ID
     * @return ResponseEntity SystemRecordBO实体
     * @see com.abc12366.bangbang.model.bo.SystemRecordBO
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id,
                                    @RequestParam("yyyyMMdd") String yyyyMMdd) {
        LOGGER.info("{},{}", yyyyMMdd, id);

        SystemRecord sr = new SystemRecord();
        sr.setId(id);
        sr.setYyyyMMdd(yyyyMMdd);
        SystemRecordBO systemRecordBO = systemRecordService.selectOne(sr);
        LOGGER.info("{}", systemRecordBO);
        return ResponseEntity.ok(Utils.kv("data", systemRecordBO));
    }

    /**
     * 异步新增日志
     *
     * @param systemRecordInsertBO SystemRecordInsertBO
     * @return ResponseEntity
     * @throws ExecutionException   执行器异常
     * @throws InterruptedException 中断异常
     * @see SystemRecordInsertBO
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody SystemRecordInsertBO systemRecordInsertBO, HttpServletRequest request)
            throws
            ExecutionException, InterruptedException {
        LOGGER.info("{}", systemRecordInsertBO);
        CompletableFuture<SystemRecordBO> systemRecordBOReturn = systemRecordService.insert(systemRecordInsertBO,
                request);
        CompletableFuture.allOf(systemRecordBOReturn);
        LOGGER.info("{}", systemRecordBOReturn.get());
        return ResponseEntity.ok(Utils.kv("data", systemRecordBOReturn.get()));
    }

}
