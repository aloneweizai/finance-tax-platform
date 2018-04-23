package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.uc.mapper.db1.UserExtendMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.UserExtendRoMapper;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.UserExtend;
import com.abc12366.uc.model.bo.UserExtendBO;
import com.abc12366.uc.model.bo.UserExtendListBO;
import com.abc12366.uc.model.bo.UserExtendUpdateBO;
import com.abc12366.uc.model.bo.UserListBO;
import com.abc12366.uc.service.RealNameValidationService;
import com.abc12366.uc.service.TodoTaskService;
import com.abc12366.uc.service.UserFeedbackMsgService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-04
 * Time: 11:07
 */
@Service
public class RealNameValidationServiceImpl implements RealNameValidationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RealNameValidationServiceImpl.class);

    @Autowired
    private UserExtendRoMapper userExtendRoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtendMapper userExtendMapper;

    @Autowired
    private TodoTaskService todoTaskService;

    @Autowired
    private UserFeedbackMsgService userFeedbackMsgService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<UserExtendListBO> selectList(Map<String, Object> map, int page, int size) {
        List<UserExtendListBO> dataList = new ArrayList<>();
        if (!StringUtils.isEmpty(map.get("username"))
                || !StringUtils.isEmpty(map.get("status"))
                || !StringUtils.isEmpty(map.get("phone"))) {
            PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
            List<UserListBO> userList = userMapper.selectList(map);
            if (userList.size() > 0) {
                for (UserListBO bo : userList) {
                    map.put("userId", bo.getId());
                    dataList.addAll(userExtendMapper.selectList(map));
                }
            }
        } else {
            PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
            dataList = userExtendMapper.selectList(map);
        }

        for (UserExtendListBO bo : dataList) {
            User user = userMapper.selectUserById(new User(bo.getUserId()));
            if (user != null) {
                bo.setUsername(user.getUsername());
                bo.setPhone(user.getPhone());
                bo.setStatus(user.getStatus());
                bo.setNickname(user.getNickname());
            }
        }

        return dataList;
    }

    @Override
    public UserExtendBO validate(String userId, String validStatus, UserExtendUpdateBO userExtendUpdateBO) throws
            ParseException {
        LOGGER.info("{}:{}:{}", userId, validStatus, userExtendUpdateBO);
        UserExtend userExtend = userExtendMapper.selectOneForAdmin(userId);
        if (userExtend == null) {
            throw new ServiceException(4701);
        }
        UserExtend userExtendUpdate = new UserExtend();
        Date startTime = new Date();

        if (userExtendUpdateBO != null) {
            BeanUtils.copyProperties(userExtendUpdateBO, userExtendUpdate);
        }
        userExtendUpdate.setUserId(userId);
        userExtendUpdate.setLastUpdate(startTime);
        userExtendUpdate.setStartTime(startTime);
        userExtendUpdate.setEndTime(getSpecifiedDate("2099-12-30 23:59:59"));
        userExtendUpdate.setValidStatus(validStatus);
        userExtendUpdate.setValidTime(new Date());
        userExtendUpdate.setValidType("1");
        if (validStatus.equals(TaskConstant.USER_REALNAME_VALIDATED)) {
            userExtendUpdate.setValidTime(new Date());

            //实名认证 跟新生日和性别
            String idCard = userExtend.getIdcard();
            if (idCard.length() == 15) {
                String id17 = idCard.substring(14, 15);
                if (Integer.parseInt(id17) % 2 != 0) {
                    userExtendUpdate.setSex("1");
                } else {
                    userExtendUpdate.setSex("0");
                }
                String birthday = idCard.substring(6, 12);
                Date birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
                userExtendUpdate.setBirthday(birthdate);
            } else if (idCard.length() == 18) {
                String id17 = idCard.substring(16, 17);
                if (Integer.parseInt(id17) % 2 != 0) {
                    userExtendUpdate.setSex("1");
                } else {
                    userExtendUpdate.setSex("0");
                }
                String birthday = idCard.substring(6, 14);
                Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
                userExtendUpdate.setBirthday(birthdate);
            }

        }
        int result = userExtendMapper.update(userExtendUpdate);
        if (result < 1) {
            throw new ServiceException();
        }

        //首次实名认证任务埋点
        if (validStatus.equals(TaskConstant.USER_REALNAME_VALIDATED)) {
            todoTaskService.doTask(userId, TaskConstant.SYS_TASK_FIRST_REALNAME_VALIDATE_CODE);
        }

        UserExtendBO userExtendBO = new UserExtendBO();
        UserExtend userExtend1 = userExtendMapper.selectOne(userExtendUpdate.getUserId());
        BeanUtils.copyProperties(userExtend1, userExtendBO);

        userFeedbackMsgService.realNameValidate(userId, validStatus);
        return userExtendBO;
    }

    @Override
    public Integer selectTodoListCount() {
        return userExtendMapper.selectTodoListCount();
    }

    public Date getSpecifiedDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(date);
    }
}
