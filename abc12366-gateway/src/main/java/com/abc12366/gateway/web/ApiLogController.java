package com.abc12366.gateway.web;

import com.abc12366.gateway.model.AdminLog;
import com.abc12366.gateway.model.ApiLog;
import com.abc12366.gateway.model.bo.AdminLogBO;
import com.abc12366.gateway.service.AdminLogService;
import com.abc12366.gateway.service.ApiLogService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 接口访问日志控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-28 3:51 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ApiLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ApiLogService apiLogService;

    @Autowired
    private AdminLogService adminLogService;

    /**
     * 接口日志-统计APP访问列表查询
     *
     * @param pageNum   当前页
     * @param pageSize  每页大小
     * @param startTime 查询日期
     * @return ResponseEntity
     */
    @GetMapping(path = "/log/api")
    public ResponseEntity selectApiList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                        @RequestParam(value = "startTime", required = false) String startTime) {

        if (startTime != null && !"".equals(startTime)) {
            startTime = DateUtils.getDateFormat(DateUtils.strToDate(startTime), "yyyyMMdd");
        } else { // 默认为当天
            startTime = DateUtils.getDateFormat(new Date(), "yyyyMMdd");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        LOGGER.info("{}", map);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<ApiLog> dataList = apiLogService.selectApiList(map);
        LOGGER.info("{}", dataList);
        PageInfo<ApiLog> pageInfo = new PageInfo<>(dataList);
        return ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo.getTotal()));
    }

    /**
     * 接口日志-根据APPID统计API列表查询
     *
     * @param pageNum   当前页
     * @param pageSize  每页大小
     * @param startTime 查询日期
     * @param appId     应用ID
     * @return ResponseEntity
     */
    @GetMapping(path = "/log/api/app")
    public ResponseEntity selectApiListByAppId(
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
            @RequestParam(value = "appId", required = false) String appId,
            @RequestParam(value = "startTime", required = false) String startTime) {

        if (startTime != null && !"".equals(startTime)) {
            startTime = DateUtils.getDateFormat(DateUtils.strToDate(startTime), "yyyyMMdd");
        } else { // 默认为当天
            startTime = DateUtils.getDateFormat(new Date(), "yyyyMMdd");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("appId", appId);
        map.put("startTime", startTime);
        LOGGER.info("{}", map);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<ApiLog> dataList = apiLogService.selectApiListByAppId(map);
        LOGGER.info("{}", dataList);
        PageInfo<ApiLog> pageInfo = new PageInfo<>(dataList);
        return ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo.getTotal()));
    }

    /**
     * 接口日志-根据uri,version,method列表查询
     *
     * @param pageNum   当前页
     * @param pageSize  每页大小
     * @param startTime 查询日期
     * @param uri       地址
     * @param version    版本
     * @param method     方法
     * @return ResponseEntity
     */
    @GetMapping(path = "/log/api/list")
    public ResponseEntity selectApiListByApiId(
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
            @RequestParam(value = "appId", required = false) String appId,
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "method", required = false) String method,
            @RequestParam(value = "uri", required = false) String uri,
            @RequestParam(value = "startTime", required = false) String startTime) throws IOException {

        if (startTime != null && !"".equals(startTime)) {
            startTime = DateUtils.getDateFormat(DateUtils.strToDate(startTime), "yyyyMMdd");
        } else { // 默认为当天
            startTime = DateUtils.getDateFormat(new Date(), "yyyyMMdd");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("version", version);
        map.put("method", method);
        if (uri != null && !"".equals(uri)) {
            map.put("uri", new String(new BASE64Decoder().decodeBuffer(uri)));
        }
        map.put("startTime", startTime);
        map.put("appId", appId);
        LOGGER.info("{}", map);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<ApiLog> dataList = apiLogService.selectApiListByApiId(map);
        LOGGER.info("{}", dataList);
        PageInfo<ApiLog> pageInfo = new PageInfo<>(dataList);
        return ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo.getTotal()));
    }

    /**
     * 管理员操作日志查询
     *
     * @param username     用户名
     * @param nickname     昵称
     * @param businessData 业务数据
     * @param businessName 业务名称
     * @param startDate    开始日期
     * @param endDate      截止日期
     * @param pageNum      当前页
     * @param pageSize     每页大小
     * @return ResponseEntity
     */
    @GetMapping(path = "/adminlog")
    public ResponseEntity selectList(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "nickname", required = false) String nickname,
                                     @RequestParam(value = "businessData", required = false) String businessData,
                                     @RequestParam(value = "businessName", required = false) String businessName,
                                     @RequestParam(value = "startDate", required = false) String startDate,
                                     @RequestParam(value = "endDate", required = false) String endDate,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize) {
        if (startDate == null || "".equals(startDate)) {
            startDate = DateUtils.getDateFormat(new Date(), "yyyy-MM-dd");
        }
        if (endDate == null || "".equals(endDate)) {
            endDate = DateUtils.getDateFormat(new Date(), "yyyy-MM-dd");
        }
        AdminLog adminLog = new AdminLog.Builder()
                .username(username)
                .nickname(nickname)
                .businessName(businessName)
                .businessData(businessData)
                .startDate(DateUtils.strToDate(startDate))
                .endDate(DateUtils.strToDate(endDate))
                .build();
        LOGGER.info("{}", adminLog);

        List<AdminLog> dataList = adminLogService.selectList(pageNum, pageSize, adminLog);
        PageInfo<AdminLog> pageInfo = new PageInfo<>(dataList);

        ResponseEntity re = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
        LOGGER.info("{}", re);
        return re;
    }

    /**
     * 管理员操作日志新增
     *
     * @param bo 日志BO
     * @return ResponseEntity
     */
    @PostMapping(path = "/adminlog")
    public ResponseEntity insert(@Valid @RequestBody AdminLogBO bo) {
        LOGGER.info("{}", bo);

        CompletableFuture<AdminLog> data = adminLogService.insert(bo);
        CompletableFuture.allOf(data);

        ResponseEntity re = ResponseEntity.ok(Utils.kv("data", data));
        LOGGER.info("{}", re);
        return re;
    }
}
