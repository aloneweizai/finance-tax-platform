package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.MessageSendLog;
import com.abc12366.message.service.SendMsgLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 发送短信日志接口控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 15:58
 */
@RestController
@RequestMapping(path = "/mobile/msg/log", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SendMsgLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgLogController.class);

    @Autowired
    private SendMsgLogService sendMsgLogService;

    /**
     * 查询短信日志列表接口
     *
     * @param phone    手机号
     * @param status   发送状态
     * @param channel  发送通道
     * @param start    开始时间
     * @param end      结束时间
     * @param sendinfo 短信内容
     * @param page     当前页
     * @param size     每页大小
     * @return 短信日志列表
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(required = false) String phone,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false) String channel,
                                     @RequestParam(required = false) String start,
                                     @RequestParam(required = false) String end,
                                     @RequestParam(required = false) String sendinfo,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> map = new HashMap<>();
        if (phone != null && "".equals(phone.trim())) {
            phone = null;
        }
        if (status != null && "".equals(status.trim())) {
            status = null;
        }
        if (channel != null && "".equals(channel.trim())) {
            channel = null;
        }
        if (start != null && "".equals(start.trim())) {
            start = null;
        }
        if (end != null && "".equals(end.trim())) {
            end = null;
        }
        if (sendinfo != null && "".equals(sendinfo.trim())) {
            sendinfo = null;
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

        map.put("phone", phone);
        map.put("status", status);
        map.put("channel", channel);
        map.put("start", startDate);
        map.put("end", endDate);
        map.put("sendinfo", sendinfo);
        List<MessageSendLog> logList = sendMsgLogService.selectList(map);
        LOGGER.info("查询到的短信日志有：{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }
}
