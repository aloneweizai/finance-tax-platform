package com.abc12366.message.service;

import com.abc12366.message.model.bo.VerifyParam;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;

import java.io.IOException;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-20
 * Time: 0:09
 */
public interface MobileVerifyCodeService {

    /**
     * 获取验证码接口
     *
     * @param type  验证码类型
     * @param phone 手机号
     * @throws IOException
     */
    void getCode(String type, String phone) throws IOException;

    /**
     * 验证码校验接口
     *
     * @param verifyParam 验证码校验参数
     */
    void verify(VerifyParam verifyParam);

    /**
     * 发送阿里云短信
     *
     * @param phone   手机
     * @param type    验证码类型
     * @param msg     消息内容
     * @param temCode 模版ID
     * @return 是否发送成功
     */
    boolean sendAliYunMsg(String phone, String type, String msg, String temCode);

    /**
     * 获取注册验证码接口（校验号码是否已被注册）
     *
     * @param type  验证码类型
     * @param phone 手机号
     */
    void getRegisCode(String type, String phone);

    /**
     * 调用阿里短信发送情况查询
     * @param phone 发送电话
     * @param bizId 发送流水号,从调用发送接口返回值中获取
     * @param logId 短信发送日志id
     * @return QuerySendDetailsResponse.SmsSendDetailDTO
     */
    QuerySendDetailsResponse.SmsSendDetailDTO querySendDetails(String phone,String bizId, String logId, String sendDate);

    /**
     * 阿里云短信发送查询
     * @param channel
     * @param phone
     * @param sendDate
     * @param page
     * @param size
     * @return
     */
    QuerySendDetailsResponse msgQueryAli(String channel, String phone, String sendDate,int page,int  size);

    
    JSONObject msgQueryYp(String channel, String phone, String sendDate, int page, int size);
}
