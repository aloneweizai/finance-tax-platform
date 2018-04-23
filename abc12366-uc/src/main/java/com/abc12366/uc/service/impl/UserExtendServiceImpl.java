package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.UserExtendMapper;
import com.abc12366.uc.model.UserExtend;
import com.abc12366.uc.model.bo.UserExtendBO;
import com.abc12366.uc.model.bo.UserExtendUpdateBO;
import com.abc12366.uc.service.TodoTaskService;
import com.abc12366.uc.service.UserBindService;
import com.abc12366.uc.service.UserExtendService;
import com.abc12366.uc.service.UserFeedbackMsgService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuguiyao
 * @create 2017-05-05 10:08 AM
 * @since 1.0.0
 */
@Service
public class UserExtendServiceImpl implements UserExtendService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserExtendService.class);

    @Autowired
    private UserExtendMapper userExtendMapper;

    @Autowired
    private UserBindService userBindService;

    @Autowired
    private TodoTaskService todoTaskService;

    @Autowired
    private UserFeedbackMsgService userFeedbackMsgService;

    @Override
    public UserExtendBO selectOne(String userId) {
        LOGGER.info("{}", userId);
        UserExtend userExtend = userExtendMapper.selectOne(userId);

        if (userExtend == null) {
            return null;
        }
        UserExtendBO userExtendBO = new UserExtendBO();
        BeanUtils.copyProperties(userExtend, userExtendBO);
        LOGGER.info("{}", userExtendBO);
        return userExtendBO;
    }

    @Transactional("db1TxManager")
    @Override
    public UserExtendBO insert(UserExtendBO userExtendBO,
                               HttpServletRequest request) {
        if (userExtendBO == null) {
            LOGGER.warn("新增失败，参数：{}" + null);
            throw new ServiceException(4101);
        }
        LOGGER.info("{}", userExtendBO);
        if (userExtendBO.getUserId() != null
                && !"".equals(userExtendBO.getUserId())) {
            UserExtend userExtend = userExtendMapper.selectOneForAdmin(userExtendBO.getUserId());
            if (userExtend != null) {
                UserExtendBO userExtendOld = new UserExtendBO();
                BeanUtils.copyProperties(userExtend, userExtendOld);
                return userExtendOld;
            }
            userExtend = new UserExtend();
            BeanUtils.copyProperties(userExtendBO, userExtend);
            userExtend.setCreateTime(new Date());
            userExtend.setLastUpdate(new Date());
            if (userExtendBO.getValidStatus() == null
                    || userExtendBO.getValidStatus().trim().equals("")) {
                userExtend.setValidStatus("0");
            }
            // 调用电子税局实名认证查询接口查询用户实名认证情况，如果已实名，则财税专家直接将该用户置为已实名
            if (userBindService.isRealNameValidatedDzsj(
                    userExtendBO.getIdcard(), userExtendBO.getRealName(), request)) {
                userExtend.setValidStatus("2");
                userExtend.setValidTime(new Date());
                userExtend.setValidType("0");

                userExtend.setStartTime(new Date());
                try {
                    userExtend.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2099-12-30 23:59:59"));
                } catch (ParseException e1) {
                    LOGGER.warn("新增失败，参数：{}" + userExtend.toString());
                    throw new ServiceException(4112);
                }
                // 首次实名认证任务埋点
                todoTaskService.doTask(userExtendBO.getUserId(),
                        TaskConstant.SYS_TASK_FIRST_REALNAME_VALIDATE_CODE);
                userFeedbackMsgService.realNameValidate(userExtendBO.getUserId(), "2");

                // 实名认证 跟新生日和性别
                String idCard = userExtendBO.getIdcard();
                if (idCard.length() == 15) {
                    String id17 = idCard.substring(14, 15);
                    if (Integer.parseInt(id17) % 2 != 0) {
                        userExtend.setSex("1");
                    } else {
                        userExtend.setSex("0");
                    }
                    String birthday = idCard.substring(6, 12);
                    Date birthdate = null;
                    try {
                        birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    userExtend.setBirthday(birthdate);
                } else if (idCard.length() == 18) {
                    String id17 = idCard.substring(16, 17);
                    if (Integer.parseInt(id17) % 2 != 0) {
                        userExtend.setSex("1");
                    } else {
                        userExtend.setSex("0");
                    }
                    String birthday = idCard.substring(6, 14);
                    Date birthdate = null;
                    try {
                        birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    userExtend.setBirthday(birthdate);
                }

            }
            int result = userExtendMapper.insert(userExtend);
            if (result != 1) {
                LOGGER.warn("新增失败，参数：{}" + userExtend.toString());
                throw new ServiceException(4112);
            }


            UserExtendBO userExtendBO1 = new UserExtendBO();
            BeanUtils.copyProperties(userExtend, userExtendBO1);
            LOGGER.info("{}", userExtendBO1);
            return userExtendBO1;
        }
        return null;
    }

    @Override
    public UserExtendBO delete(String userId) {
        LOGGER.info("{}", userId);
        UserExtend userExtend = userExtendMapper.selectOne(userId);
        if (userExtend == null) {
            LOGGER.warn("删除失败，参数：{}" + userId);
            throw new ServiceException(4103);
        }
        int result = userExtendMapper.delete(userId);
        if (result != 1) {
            LOGGER.warn("删除失败，参数：{}" + userId);
            throw new ServiceException(4103);
        }


        UserExtendBO userExtendBO = new UserExtendBO();
        BeanUtils.copyProperties(userExtend, userExtendBO);
        LOGGER.info("{}", userExtendBO);
        return userExtendBO;
    }

    @Override
    public UserExtendBO update(UserExtendUpdateBO userExtendUpdateBO,
                               HttpServletRequest request) throws IOException {
        LOGGER.info("{}", userExtendUpdateBO);
        if (userExtendUpdateBO == null) {
            LOGGER.warn("修改失败，参数：{}" + null);
            throw new ServiceException(4102);
        }
        // 不允许修改非当前登录用户数据
        if (!userExtendUpdateBO.getUserId().trim()
                .equals(Utils.getUserId(request).trim())) {
            throw new ServiceException(4190);
        }
        if (!userExtendUpdateBO.getUserId().equals("")) {
            UserExtend userExtend = userExtendMapper
                    .selectOneForAdmin(userExtendUpdateBO.getUserId());
            if (userExtend == null) {
                UserExtendBO userExtendBO = new UserExtendBO();
                BeanUtils.copyProperties(userExtendUpdateBO, userExtendBO);
                return insert(userExtendBO, request);
            }
            UserExtend userExtendSecond = new UserExtend();
            BeanUtils.copyProperties(userExtendUpdateBO, userExtendSecond);
            Date date = new Date();
            userExtendSecond.setLastUpdate(date);
            // 调用电子税局实名认证查询接口查询用户实名认证情况，如果已实名，则财税专家直接将该用户置为已实名
            if (userBindService.isRealNameValidatedDzsj(
                    userExtendUpdateBO.getIdcard(),
                    userExtendUpdateBO.getRealName(), request)) {
                userExtendSecond.setValidStatus("2");
                userExtendSecond.setValidTime(date);
                userExtendSecond.setValidType("0");

                userExtendSecond.setStartTime(date);
                try {
                    userExtendSecond.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2099-12-30 " +
							"23:59:59"));
                } catch (ParseException e1) {
                    LOGGER.warn("新增失败，参数：{}" + userExtend.toString());
                    throw new ServiceException(4112);
                }

                // 首次实名认证任务埋点
                todoTaskService.doTask(userExtendUpdateBO.getUserId(),
                        TaskConstant.SYS_TASK_FIRST_REALNAME_VALIDATE_CODE);
                userFeedbackMsgService.realNameValidate(
                        userExtendUpdateBO.getUserId(), "2");

                // 实名认证 跟新生日和性别
                String idCard = userExtendUpdateBO.getIdcard();
                if (idCard.length() == 15) {
                    String id17 = idCard.substring(14, 15);
                    if (Integer.parseInt(id17) % 2 != 0) {
                        userExtendSecond.setSex("1");
                    } else {
                        userExtendSecond.setSex("0");
                    }
                    String birthday = idCard.substring(6, 12);
                    Date birthdate = null;
                    try {
                        birthdate = new SimpleDateFormat("yyMMdd")
                                .parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    userExtendSecond.setBirthday(birthdate);
                } else if (idCard.length() == 18) {
                    String id17 = idCard.substring(16, 17);
                    if (Integer.parseInt(id17) % 2 != 0) {
                        userExtendSecond.setSex("1");
                    } else {
                        userExtendSecond.setSex("0");
                    }
                    String birthday = idCard.substring(6, 14);
                    Date birthdate = null;
                    try {
                        birthdate = new SimpleDateFormat("yyyyMMdd")
                                .parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    userExtendSecond.setBirthday(birthdate);
                }

            }
            int result = userExtendMapper.update(userExtendSecond);
            UserExtendBO userExtendBO = new UserExtendBO();

            if (result < 1) {
                LOGGER.warn("修改失败，参数：{}" + userExtendUpdateBO);
                throw new ServiceException(4102);
            }

            UserExtend userExtend2 = userExtendMapper
                    .selectOne(userExtendSecond.getUserId());
            BeanUtils.copyProperties(userExtend2, userExtendBO);
            LOGGER.info("{}", userExtendBO);
            return userExtendBO;
        }
        return null;
    }

    @Override
    public boolean updateUserAddress(String userId, String provinceId, String cityId) {
        UserExtend ue = userExtendMapper.selectOneForAdmin(userId);
        if (ue != null) {
            if (StringUtils.isEmpty(ue.getProvince())) {
                ue.setProvince(provinceId);
            }
            if (StringUtils.isEmpty(ue.getCity())) {
                ue.setCity(cityId);
            }
            ue.setLastUpdate(new Date());
            return 1 == userExtendMapper.update(ue);
        }
        return false;
    }

}
