package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.bo.MobileMsgBO;
import com.abc12366.message.service.SendMobileMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 发送手机业务短信控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 9:57
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SendMobileMsgController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMobileMsgController.class);

    @Autowired
    private SendMobileMsgService sendMobileMsgService;

    /**
     * 发短信接口
     *
     * @param mobileMsgBO 发送短信对象
     * @return ResponseEntity
     */
    @PostMapping(path = "/mobile/msg")
    public ResponseEntity sendMsg(@Valid @RequestBody MobileMsgBO mobileMsgBO) {
        LOGGER.info("发送短信参数：{}", mobileMsgBO);
        sendMobileMsgService.sendMsg(mobileMsgBO);
        return ResponseEntity.ok(Utils.kv());
    }

}
