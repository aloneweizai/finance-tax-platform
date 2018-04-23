package com.abc12366.message.web;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.BusinessBatchMessage;
import com.abc12366.message.model.BusinessMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;
import com.abc12366.message.model.bo.BusinessMessageAdmin;
import com.abc12366.message.service.BusinessMsgService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * 业务消息服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 11:20 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/business", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class BusinessMsgController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessMsgController.class);

    @Autowired
    private BusinessMsgService businessMsgService;

    /**
     * 获取当前用户业务消息列表
     *
     * @param type     消息类型
     * @param busiType 业务类型
     * @param status   状态
     * @param page     当前页
     * @param size     每页大小
     * @param request  HttpServletRequest
     * @return ResponseEntity
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(required = false) String type,
                                     @RequestParam(required = false) String busiType,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false) String dateStr,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     HttpServletRequest request) {
        LOGGER.info("{},{}", page, size);

        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = (String) request.getAttribute(Constant.USER_ID);

        if (!StringUtils.isEmpty(userId)) {
            BusinessMessage bm = new BusinessMessage.Builder().userId(userId).type(type).busiType(busiType).status
                    (status).build();
            bm.setDateStr(org.apache.commons.lang3.StringUtils.isNotEmpty(dateStr)?dateStr:DateUtils.getDateFormat(new Date(), "yyyyMM"));
            List<BusinessMessage> dataList = businessMsgService.selectList(bm, page, size);

            PageInfo<BusinessMessage> pageInfo = new PageInfo<>(dataList);
            responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal(),
                    "time", DateUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")));
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 获取当前业务消息列表（缓存）
     *
     * @return ResponseEntity
     */
    @GetMapping(path = "/unread")
    public ResponseEntity selectListForUnread(HttpServletRequest request) {

        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = (String) request.getAttribute(Constant.USER_ID);
        if (!StringUtils.isEmpty(userId)) {
            BusinessMessage bm = new BusinessMessage.Builder().userId(userId).status("1").build();
            List<BusinessMessage> dataList = businessMsgService.selectUnreadList(bm);

            PageInfo<BusinessMessage> pageInfo = new PageInfo<>(dataList);
            responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                    "time", DateUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")));
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查询业务消息未读数量
     *
     * @param type     消息类型，默认查询所有类型消息
     * @param busiType 业务类型，默认查询所有类型
     * @return 消息未读总数
     */
    @GetMapping("/unread/count")
    public ResponseEntity unreadCount(@RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "busiType", required = false) String busiType) {
        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = Utils.getUserId();

        if (!StringUtils.isEmpty(userId)) {
            BusinessMessage bm = new BusinessMessage.Builder()
                    .userId(userId).type(type)
                    .busiType(busiType)
                    .status("1")
                    .build();
            int count = businessMsgService.selectUnreadList(bm).size();
            responseEntity = ResponseEntity.ok(Utils.kv("data", count));
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 发送业务消息
     *
     * @param data BusinessMessage
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody BusinessMessage data) {
        LOGGER.info("{}", data);

        data = businessMsgService.insert(data);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 发送批量业务消息
     *
     * @param data BusinessMessage
     * @return ResponseEntity
     */
    @PostMapping(path = "/batch")
    public ResponseEntity insert(@Valid @RequestBody BusinessBatchMessage data) {
        LOGGER.info("{}", data);

        List<BusinessMessage> dataList = businessMsgService.insert(data);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", dataList));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 用户查看消息，如果消息状态为'未读'，则将消息状态置为'已读'
     *
     * @param id 消息ID
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        BusinessMessage data = businessMsgService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 直接将'未读'消息置为'已读'，不需要进入消息
     *
     * @param id 消息ID
     * @return ResponseEntity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        BusinessMessage data = businessMsgService.update(new BusinessMessage.Builder().id(id).status("2").build());
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 用户删除消息，物理删除
     *
     * @param id      消息ID
     * @param request 获取USER_ID
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id, HttpServletRequest request) {
        LOGGER.info("{}", id);

        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = (String) request.getAttribute(Constant.USER_ID);

        if (!StringUtils.isEmpty(userId)) {
            BusinessMessage data = new BusinessMessage.Builder().id(id).userId(userId).build();
            BodyStatus body = businessMsgService.delete(data);
            responseEntity = ResponseEntity.ok(body);
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 发送业务消息(提供给系统使用,不做token校验)
     *
     * @param data BusinessMessage
     * @return ResponseEntity
     */
    @PostMapping(path = "/system")
    public ResponseEntity insertBySystem(@Valid @RequestBody BusinessMessage data) {
        LOGGER.info("{}", data);

        data = businessMsgService.insert(data);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 根据用户名查询业务消息列表
     *
     * @param type     消息类型
     * @param busiType 业务类型
     * @param status   状态
     * @param page     当前页
     * @param size     每页大小
     * @return ResponseEntity
     */
    @GetMapping(path = "/username")
    public ResponseEntity selectListByUsername(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String busiType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String dateStr,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("根据用户名查询业务消息列表：username：{}，{},{}", username, page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("username", StringUtils.isEmpty(username) ? null : username.trim());
        map.put("type", StringUtils.isEmpty(type)?null:type.trim());
        map.put("busiType", StringUtils.isEmpty(busiType)?null:busiType.trim());
        map.put("status", StringUtils.isEmpty(status)?null:status.trim());
        map.put("dateStr", org.apache.commons.lang3.StringUtils.isNotEmpty(dateStr)?dateStr:DateUtils.getDateFormat(new Date(), "yyyyMM"));
        if (!StringUtils.isEmpty(startDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.strToDate(startDate));
            map.put("startDate", calendar.getTime());
        }
        if (!StringUtils.isEmpty(endDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.strToDate(endDate));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            map.put("endDate", calendar.getTime());
        }
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());
        List<BusinessMessageAdmin> dataList = businessMsgService.selectListByUsername(map, page, size);

        if (!StringUtils.isEmpty(dataList) && dataList.size() > 0) {
            PageInfo<BusinessMessageAdmin> pageInfo = new PageInfo<>(dataList);
            responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
        }

        LOGGER.info("发送给{}的消息有：{}", username, dataList);
        return responseEntity;
    }

    /**
     * 批量将消息置为已读
     *
     * @param bo 消息ID集合
     * @return ResponseEntity
     */
    @PutMapping(path = "/batch")
    public ResponseEntity batchUpdateToRead(@RequestBody BatchUpdateMsgToReadBO bo) {
        LOGGER.info("{}", bo);
        businessMsgService.batchUpdateToRead(bo);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
