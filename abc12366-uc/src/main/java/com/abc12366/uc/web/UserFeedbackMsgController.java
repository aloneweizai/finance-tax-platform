package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.service.UserFeedbackMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-10
 * Time: 15:07
 */
@RestController
@RequestMapping(path = "/user/fback/msg", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserFeedbackMsgController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFeedbackMsgController.class);

    @Autowired
    private UserFeedbackMsgService userFeedbackMsgService;

    /**
     *  用户修改密码成功调soa发送消息提醒接口
     * @return ResponseEntity
     */
    @PostMapping(path = "/pwd")
    public ResponseEntity updatePasswordSuccessNotice(){
        LOGGER.info("用户修改密码成功回答soa发送消息提醒");
        userFeedbackMsgService.updatePasswordSuccessNotice(Utils.getUserId());
        LOGGER.info("用户修改密码成功回答soa发送消息提醒成功");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     *  用户每日首次登录未实名认证发送消息提醒
     * @return ResponseEntity
     */
    @PostMapping(path = "/unrealname")
    public ResponseEntity unrealname(){
        LOGGER.info("用户每日首次登录未实名认证回调soa发送消息提醒");
        userFeedbackMsgService.unrealname(Utils.getUserId());
        LOGGER.info("用户每日首次登录未实名认证回调soa发送消息提醒成功");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     *  用户每日首次登录有未完成的任务发送消息提醒
     * @return ResponseEntity
     */
    @PostMapping(path = "/undotask")
    public ResponseEntity undotask(){
        LOGGER.info("用户每日首次登录有未完成的任务发送消息提醒");
        userFeedbackMsgService.undotask(Utils.getUserId());
        LOGGER.info("用户每日首次登录有未完成的任务发送消息提醒成功");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     *  用户每日首次登录发送消息提醒用户去签到
     * @return ResponseEntity
     */
    @PostMapping(path = "/check")
    public ResponseEntity check(){
        LOGGER.info("用户每日首次登录有未完成的任务发送消息提醒用户去签到");
        userFeedbackMsgService.check(Utils.getUserId());
        LOGGER.info("用户每日首次登录有未完成的任务发送消息提醒用户去签到成功");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     *  用户经验值等级提升每发送消息提醒用户
     * @return ResponseEntity
     */
    @PostMapping(path = "/explevelup")
    public ResponseEntity expLevelUp(){
        LOGGER.info("用户经验值等级提升每发送消息提醒用户");
        userFeedbackMsgService.expLevelUp(Utils.getUserId());
        LOGGER.info("用户经验值等级提升每发送消息提醒用户成功");
        return ResponseEntity.ok(Utils.kv());
    }
}
