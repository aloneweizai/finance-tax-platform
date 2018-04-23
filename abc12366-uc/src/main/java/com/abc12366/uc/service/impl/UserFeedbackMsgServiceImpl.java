package com.abc12366.uc.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.RemindConstant;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.UcUserLoginLogRoMapper;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.model.TodoTaskFront;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.UcUserLoginLog;
import com.abc12366.uc.model.bo.UserExtendBO;
import com.abc12366.uc.service.IMsgSendService;
import com.abc12366.uc.service.IMsgSendV2service;
import com.abc12366.uc.service.TodoTaskService;
import com.abc12366.uc.service.UserExtendService;
import com.abc12366.uc.service.UserFeedbackMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-10
 * Time: 15:11
 */
@Service
public class UserFeedbackMsgServiceImpl implements UserFeedbackMsgService {

    @Autowired
    private IMsgSendService msgSendService;

    @Autowired
    private UserExtendService userExtendService;

    @Autowired
    private UcUserLoginLogRoMapper loginLogRoMapper;

    @Autowired
    private TodoTaskService todoTaskService;

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
	private IMsgSendV2service msgSendV2Service;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFeedbackMsgServiceImpl.class);

    @Override
    public void updatePasswordSuccessNotice(String userId) {
        //发信息
        //1.系统消息
        String sysMsg = RemindConstant.UPDATE_PWD_SUCCESS_SYS.replace("{#DATA.DATE}", DateUtils.dateToStr(new Date()));
        //2.微信消息
        Map<String, String> dataList = new HashMap<>();
        dataList.put("first", RemindConstant.UPDATE_PWD_SUCCESS_WX_1);
        dataList.put("keyword1", userMapper.selectOne(userId).getUsername());
        dataList.put("keyword2", DateUtils.dateToStr(new Date()));
        dataList.put("remark", RemindConstant.UPDATE_PWD_SUCCESS_WX_4);
        //3.短信消息
        /*MessageSendBo sendBo = new MessageSendBo();
        sendBo.setUserId(userId);
        sendBo.setWebMsg(sysMsg);
        sendBo.setTemplateid("AYi8h8g7_bKN8Yr9wVDh4ZQ_CIOwsoIzX1A6tx1E5WE");
        sendBo.setDataList(dataList);
        sendBo.setPhoneMsg(RemindConstant.UPDATE_PWD_SUCCESS_DX.replace("{#DATA.DATE}", DateUtils.dateToStr(new Date())));
        msgSendService.sendXtxx(sendBo);*/
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_PWD);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg(sysMsg);
        messageSendBo.setPhoneMsg(RemindConstant.UPDATE_PWD_SUCCESS_DX.replace("{#DATA.DATE}", DateUtils.dateToStr(new Date())));
        messageSendBo.setTemplateid("AYi8h8g7_bKN8Yr9wVDh4ZQ_CIOwsoIzX1A6tx1E5WE");
        messageSendBo.setDataList(dataList);
        messageSendBo.setWxNoChargeVip(true);
        messageSendBo.setMoblieNoChargeVip(true);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    @Override
    public void unrealname(String userId) {
        if (!isFirstLoginToday(userId)) {
            return;
        }
        UserExtendBO userExtendBO = getUserExtend(userId);
        if (userExtendBO != null && !StringUtils.isEmpty(userExtendBO.getValidStatus()) &&
                (userExtendBO.getValidStatus().equals(TaskConstant.USER_REALNAME_VALIDATED)||
                        userExtendBO.getValidStatus().equals(TaskConstant.USER_REALNAME_TO_VALIDATE))) {
            return;
        }

        //发信息
        //1.系统消息
        String sysMsg = RemindConstant.UNREALNAME_SYS;
        String skipUrl = "<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/userinfo/userinfolist.html#1_1'>马上去实名认证</a>";
        //2.微信消息.后改为不做
        //3.短信消息.后改为不做

        /*MessageSendBo sendBo = new MessageSendBo();
        sendBo.setUserId(userId);
        sendBo.setWebMsg(sysMsg);
        sendBo.setSkipUrl(skipUrl);
        msgSendService.sendXtxx(sendBo);*/
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_REALNAME);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg(sysMsg);
        messageSendBo.setSkipUrl(skipUrl);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    @Override
    public void undotask(String userId) {
        if (!isFirstLoginToday(userId)) {
            return;
        }
        int undoTaskCount = 0;
        List<TodoTaskFront> taskList = new ArrayList<>();
        taskList.addAll(todoTaskService.selectNormalTaskList(userId));
        taskList.addAll(todoTaskService.selectOnetimeTaskList(userId));
        taskList.addAll(todoTaskService.selectSpecialTaskList(userId));
        taskList.addAll(todoTaskService.selectBangbangTaskList(userId));
        for (TodoTaskFront taskFront : taskList) {
            if (taskFront != null && taskFront.getStatus() != null && !"1".equals(taskFront.getStatus())) {
                undoTaskCount++;
            }
        }

        //发信息
        //1.系统消息
        String sysMsg = RemindConstant.UNDO_TASK_SYS.replace("{#DATA}", "" + undoTaskCount);
        String skipUrl = "<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/userinfo/task.php'>马上做任务</a>";
        //2.微信消息,不做
        //3.短信消息，不做
       /* MessageSendBo sendBo = new MessageSendBo();
        sendBo.setUserId(userId);
        sendBo.setWebMsg(sysMsg);
        sendBo.setSkipUrl(skipUrl);
        msgSendService.sendXtxx(sendBo);*/
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_USERINFO);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg(sysMsg);
        messageSendBo.setSkipUrl(skipUrl);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    @Override
    public void check(String userId) {
        if (!isFirstLoginToday(userId)) {
            return;
        }

        //发信息
        //1.系统消息
        String sysMsg = RemindConstant.UNDO_CHECK_SYS;
        String skipUrl = "<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/member/checkIn.php'>马上签到</a>";
        //2.微信消息,不做
        //3.短信消息，不做
//        MessageSendBo sendBo = new MessageSendBo();
//        sendBo.setUserId(userId);
//        sendBo.setWebMsg(sysMsg);
//        sendBo.setSkipUrl(skipUrl);
//        msgSendService.sendXtxx(sendBo);
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_USERINFO);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg(sysMsg);
        messageSendBo.setSkipUrl(skipUrl);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    @Override
    public void expLevelUp(String userId) {
        //发信息
        //1.系统消息
        String sysMsg = RemindConstant.EXP_LEVEL_UP_SYS;
        String skipUrl = "<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/userinfo/expLog.php'>查看详情</a>";
        //2.微信消息,不做
        //3.短信消息，不做
       /* MessageSendBo sendBo = new MessageSendBo();
        sendBo.setUserId(userId);
        sendBo.setWebMsg(sysMsg);
        sendBo.setSkipUrl(skipUrl);
        msgSendService.sendXtxx(sendBo);*/
        
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_USERINFO);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg(sysMsg);
        messageSendBo.setSkipUrl(skipUrl);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    @Override
    public void realNameValidate(String userId, String status) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(status)) {
            LOGGER.info("给用户发送实名认证情况消息通知参数异常：userId:{},status:{}", userId, status);
            return;
        }
        User user = userMapper.selectOne(userId);
        if (user == null) {
            LOGGER.info("给用户发送实名认证情况消息通知失败，因为用户不存在");
        }
        //发信息
        //1.系统消息
        String sysMsg = RemindConstant.REALNAME_VALIDATE_SYS.replace("{#DATA.RESULT}", (status.trim().equals(TaskConstant.USER_REALNAME_VALIDATED) ? "已通过" : "未通过"))
                .replace("{#DATA.DATE}", DateUtils.dateToStr(new Date()));
        //2.微信消息
        Map<String, String> dataList = new HashMap<>();
        dataList.put("first", RemindConstant.REALNAME_VALIDATE_WX_1);
        dataList.put("keyword1", (status.trim().equals(TaskConstant.USER_REALNAME_VALIDATED) ? "已通过" : "未通过"));
        dataList.put("keyword2", DateUtils.dateToStr(new Date()));
        dataList.put("remark", RemindConstant.REALNAME_VALIDATE_WX_4);
        //3.短信消息
        String dxMsg = RemindConstant.REALNAME_VALIDATE_DX.replace("{#DATA.RESULT}", (status.trim().equals(TaskConstant.USER_REALNAME_VALIDATED) ? "已通过" : "未通过"))
                .replace("{#DATA.DATE}", DateUtils.dateToStr(new Date()));

//        MessageSendBo sendBo = new MessageSendBo();
//        sendBo.setUserId(userId);
//        sendBo.setWebMsg(sysMsg);
//        sendBo.setPhoneMsg(dxMsg);
//        sendBo.setDataList(dataList);
//        sendBo.setTemplateid("JQUa0hyi-oKyG-hhuboC_4IKAeBTRn26w2ippsLUS-U");

        //msgSendService.sendXtxx(sendBo);
        
        //2018-03-08
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.USER_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_REALNAME);
        messageSendBo.setBusinessId(userId);
        messageSendBo.setWebMsg(sysMsg);
        messageSendBo.setPhoneMsg(dxMsg);
        messageSendBo.setTemplateid("JQUa0hyi-oKyG-hhuboC_4IKAeBTRn26w2ippsLUS-U");
        messageSendBo.setDataList(dataList);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(userId);
        messageSendBo.setUserIds(userIds);
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    private UserExtendBO getUserExtend(String userId) {
        return userExtendService.selectOne(userId);
    }

    private boolean isFirstLoginToday(String userId) {
        Map<String, Object> map = new HashMap<>();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MINUTE, 0);

        calendar2.add(Calendar.DAY_OF_MONTH, 1);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MINUTE, 0);
        map.put("userId", userId);
        map.put("startTime", calendar1.getTime());
        map.put("endTime", calendar2.getTime());
        List<UcUserLoginLog> logList = loginLogRoMapper.selectLoginLogList(map);
        boolean isFirstLoginToday = false;
        if (logList != null && logList.size() == 1) {
            isFirstLoginToday = true;
        }
        return isFirstLoginToday;
    }

}
