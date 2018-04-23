package com.abc12366.message.service;

import com.abc12366.message.model.bo.MobileMsgBO;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 9:59
 */
public interface SendMobileMsgService {

    /**
     * 发短信接口
     *
     * @param mobileMsgBO 发送短信对象
     */
    void sendMsg(MobileMsgBO mobileMsgBO);

    /**
     * 发送又拍云业务短信
     *
     * @param mobileMsgBO 消息对象
     */
    void sendMsgByUppyun(MobileMsgBO mobileMsgBO);
}
