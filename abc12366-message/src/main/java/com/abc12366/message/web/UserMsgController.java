package com.abc12366.message.web;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.UserBatchMessage;
import com.abc12366.message.model.UserMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;
import com.abc12366.message.model.bo.UserMessageAdmin;
import com.abc12366.message.model.bo.UserMessageForBangbang;
import com.abc12366.message.service.UserMsgService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户消息服务
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2017-07-27 11:20 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user/msg", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserMsgController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMsgController.class);

    @Autowired
    private UserMsgService userMsgService;

    /**
     * 获取当前用户消息列表
     *
     * @param fromNickname 发送用户昵称
     * @param fromUserId   发送用户ID
     * @param toNickname   接收用户昵称
     * @param toUserId     接收用户ID
     * @param type         类型
     * @param status       状态
     * @param page         当前页
     * @param size         每页大小
     * @return ResponseEntity
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(required = false) String fromNickname,
                                     @RequestParam(required = false) String fromUserId,
                                     @RequestParam(required = false) String toNickname,
                                     @RequestParam(required = false) String toUserId,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{},{},{},{},{},{}", fromNickname, fromUserId, toNickname, toUserId, type, status, page,
                size);

        UserMessage um = new UserMessage.Builder()
                .fromNickname(fromNickname)
                .fromUserId(fromUserId)
                .toNickname(toNickname)
                .toUserId(toUserId)
                .type(type)
                .status(status)
                .createTime(new Timestamp(System.currentTimeMillis())).build();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(startDate)){
        	um.setStartDate(DateUtils.strToDate(startDate+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        }
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(endDate)){
        	um.setEndDate(DateUtils.strToDate(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        }
        List<UserMessage> dataList = userMsgService.selectList(um, page, size);

        PageInfo<UserMessage> pageInfo = new PageInfo<>(dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal(),
                "time", DateUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")));
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
            UserMessage um = new UserMessage.Builder().toUserId(userId).status("1").build();
            List<UserMessage> dataList = userMsgService.selectUnreadList(um);

            PageInfo<UserMessage> pageInfo = new PageInfo<>(dataList);
            responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                    "time", DateUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")));
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查询用户消息未读数量
     *
     * @param type 消息类型，默认查询所有消息
     * @return 消息未读总数
     */
    @GetMapping("/unread/count")
    public ResponseEntity unreadCount(@RequestParam(value = "type", required = false) String type) {
        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = Utils.getUserId();

        if (!StringUtils.isEmpty(userId)) {
            UserMessage um = new UserMessage.Builder().toUserId(userId).type(type).status("1").build();
            int count = userMsgService.selectUnreadList(um).size();
            responseEntity = ResponseEntity.ok(Utils.kv("data", count));
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 发送用户消息
     *
     * @param data UserMessage
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody UserMessage data) {
        LOGGER.info("{}", data);

        data = userMsgService.insert(data);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 发送批量用户消息
     *
     * @param data BusinessMessage
     * @return ResponseEntity
     */
    @PostMapping(path = "/batch")
    public ResponseEntity insert(@Valid @RequestBody UserBatchMessage data) {
        LOGGER.info("{}", data);

        List<UserMessage> dataList = userMsgService.insert(data);
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

        UserMessage data = userMsgService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 直接将'未读'消息置为'已读'，不需要进入消息
     *
     * @param id 消息主键
     * @return 消息对象
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        UserMessage data = userMsgService.update(new UserMessage.Builder().id(id).status("2").build());
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
            UserMessage data = new UserMessage.Builder().id(id).toUserId(userId).build();
            BodyStatus body = userMsgService.delete(data);
            responseEntity = ResponseEntity.ok(body);
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 获取当前用户消息列表
     *
     * @param page 当前页
     * @param size 每页大小
     * @return ResponseEntity
     */
    @GetMapping(path = "/bangbang")
    public ResponseEntity selectListForBangbang(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                        size,
                                                HttpServletRequest request) {
        LOGGER.info("{},{}", page, size);

        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = (String) request.getAttribute(Constant.USER_ID);

        if (!StringUtils.isEmpty(userId)) {
            List<UserMessageForBangbang> dataList = userMsgService.selectListForBangbang(userId, page, size);

            PageInfo<UserMessageForBangbang> pageInfo = new PageInfo<>(dataList);
            responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查询用户会话消息列表
     *
     * @param fromUserId 发送者/接收者
     * @param toUserId   接受者/发送者
     * @param type       类型
     * @param page       页码
     * @param size       每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/conversation")
    public ResponseEntity selectConversationList(@RequestParam String fromUserId,
                                                 @RequestParam String toUserId,
                                                 @RequestParam(required = false) String type,
                                                 @RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                         page,
                                                 @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                         size
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("fromUserId", fromUserId);
        map.put("toUserId", toUserId);
        map.put("status", type);
        LOGGER.info("{}", map.toString());

        List<UserMessageAdmin> dataList = userMsgService.selectConversationList(map, page, size);
        PageInfo<UserMessageAdmin> pageInfo = new PageInfo<>(dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
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
        userMsgService.batchUpdateToRead(bo);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
