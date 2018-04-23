package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionDisableUserMapper;
import com.abc12366.bangbang.mapper.db2.QuestionDisableUserRoMapper;
import com.abc12366.bangbang.model.Message;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.question.QuestionDisableUser;
import com.abc12366.bangbang.model.question.bo.QuestionDisableUserBo;
import com.abc12366.bangbang.service.MessageSendUtil;
import com.abc12366.bangbang.service.QuestionDisableUserService;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/18 15:50
 */
@Service
public class QuestionDisableUserServiceImpl implements QuestionDisableUserService{

    @Autowired
    private QuestionDisableUserMapper questionDisableUserMapper;

    @Autowired
    private QuestionDisableUserRoMapper questionDisableUserRoMapper;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @Override
    public List<QuestionDisableUserBo> selectList(Map map) {
        return questionDisableUserRoMapper.selectList(map);
    }

    @Transactional("db1TxManager")
    @Override
    public void disable(QuestionDisableUser record, HttpServletRequest request) {
        questionDisableUserMapper.deleteByPrimaryKey(record.getUserId());
        record.setUpdateAdmin(Utils.getAdminId());
        questionDisableUserMapper.insert(record);
        /*发送系统消息*/
        /*Message message = new Message();
        message.setUserId(record.getUserId());
        message.setContent(new StringBuilder("很抱歉！您已被禁言了,原因为：").append(record.getReason()).append("，下次生效时间为！").append(DateUtils.dateToStr(record.getActiveTime())).toString());
        message.setType("2");
        message.setBusinessId(record.getUserId());
        messageSendUtil.sendMessage(message, request);*/
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_BANGBANG);
        messageSendBo.setBusinessId(record.getUserId());
        messageSendBo.setWebMsg(new StringBuilder("很抱歉！您已被禁言了,原因为：").append(record.getReason()).append("，下次生效时间为！").append(DateUtils.dateToStr(record.getActiveTime())).toString());
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(record.getUserId());
        messageSendBo.setUserIds(userIds);
        
        messageSendUtil.sendMsgBySubscriptions(messageSendBo, request);
    }

    @Override
    public void enable(String userId, HttpServletRequest request) {
        questionDisableUserMapper.deleteByPrimaryKey(userId);
        /*发送系统消息*/
        /*Message message = new Message();
        message.setUserId(userId);
        message.setContent("恭喜你！您的禁言已被撤销");
        message.setType("2");
        message.setBusinessId(userId);
        messageSendUtil.sendMessage(message, request);*/
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_BANGBANG);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg("恭喜你！您的禁言已被撤销");
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        
        messageSendUtil.sendMsgBySubscriptions(messageSendBo, request);
    }
}
