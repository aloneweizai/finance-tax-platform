package com.abc12366.uc.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.TokenMapper;
import com.abc12366.uc.mapper.db1.UcUserLoginLogMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.TokenRoMapper;
import com.abc12366.uc.mapper.db2.UcUserLoginLogRoMapper;
import com.abc12366.uc.model.*;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.*;
import com.abc12366.uc.service.admin.OperateMessageService;
import com.abc12366.uc.util.RandomNumber;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2017-03-27 4:07 PM
 * @since 1.0.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenRoMapper tokenRoMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserExtendService userExtendService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private UcUserLoginLogMapper loginLogMapper;

    @Autowired
    private UcUserLoginLogRoMapper loginLogRoMapper;

    @Autowired
    private ExperienceLogService experienceLogService;

    @Autowired
    private RSAService rsaService;

    @Autowired
    private TodoTaskService todoTaskService;

    @Autowired
    private ExperienceRuleService experienceRuleService;

    @Autowired
    private IpService ipService;

    @Autowired
    private UserFeedbackMsgService userFeedbackMsgService;

    @Autowired
    private OperateMessageService operateMessageService;

    /**
     * 2、新平台采用手机号码+登录密码+短信验证码注册，平台自动产生用户ID、用户名（字母UC+时间戳毫秒数）和用户昵称（财税+6位数字），同时自动绑定手机号码。
     * 3、用户ID作为平台内部字段永久有效且不可更改，平台自动产生的用户名可以允许修改一次且平台内唯一，用户名不能为中文，只能为字母+数字。
     *
     * @param registerBO 注册BO
     * @return 简单用户信息
     */
    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public UserReturnBO register(RegisterBO registerBO, HttpServletRequest request) {
        LOGGER.info("{}", registerBO);
        if (registerBO == null) {
            LOGGER.warn("新增失败，参数:{}" + null);
            throw new ServiceException(4101);
        }

        LoginBO loginBO = new LoginBO();
        if (!StringUtils.isEmpty(registerBO.getPhone())) {
            loginBO.setUsernameOrPhone(registerBO.getPhone().trim().toLowerCase());
        }
        User user = userMapper.selectByUsernameOrPhone(loginBO);
        if (user != null) {
            LOGGER.warn("新增失败，参数:{}" + registerBO.toString());
            throw new ServiceException(4117);
        }
        String password;
        String encodePassword;
        String salt;
        try {
            //密码生产规则：前台传密码md5之后的值，后台用该值加上salt再md5 ，salt是随机生成的六位整数
//            password = Utils.md5(registerBO.getPassword());
//            salt = Utils.salt();
//            encodePassword = Utils.md5(password + salt);

            //新的密码规则
            password = rsaService.decode(registerBO.getPassword());
            salt = Utils.salt();
            encodePassword = Utils.md5(password + salt);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + e);
            throw new ServiceException(4106);
        }
        user = new User();
        BeanUtils.copyProperties(registerBO, user);
        String userId = Utils.uuid();
        user.setId(userId);
        //用户名（字母UC+时间戳毫秒数）
        user.setUsername("kp" + System.currentTimeMillis());
        //自动生成用户昵称:（财税+6位数字）
        user.setNickname("客品" + RandomNumber.getRandomNumber(6));
        user.setSalt(salt);
        user.setPassword(encodePassword);
        user.setPoints(0);
        user.setExp(0);
        user.setVipLevel(Constant.USER_ORIGINAL_LEVEL);
        user.setUsernameModifiedTimes(0);
        if (!StringUtils.isEmpty(registerBO.getRegMail())) {
            user.setRegMail(registerBO.getRegMail());
        }
        if (!StringUtils.isEmpty(registerBO.getUserPicturePath())) {
            user.setUserPicturePath(registerBO.getUserPicturePath());
        }
        if (!StringUtils.isEmpty(registerBO.getRegIP())) {
            user.setRegIP(registerBO.getRegIP());
        }
        if (!StringUtils.isEmpty(registerBO.getSalt())) {
            user.setSalt(registerBO.getSalt());
        }

        user.setStatus(true);
        user.setCreateTime(new Date());
        user.setLastUpdate(new Date());

        int result = userMapper.insert(user);
        if (result != 1) {
            LOGGER.warn("新增失败，参数:{}" + registerBO.toString());
            throw new ServiceException(4020);
        }

        //注册用户增加地址：province、city到uc_userExtend表
        UserExtendBO userExtend = new UserExtendBO();
        userExtend.setUserId(user.getId());
        userExtend.setProvince(registerBO.getProvince());
        userExtend.setCity(registerBO.getCity());
        userExtendService.insert(userExtend, request);

        UserReturnBO userReturnBO = new UserReturnBO();
        BeanUtils.copyProperties(user, userReturnBO);

        //首次绑定手机任务埋点
        //todoTaskService.doTask(user.getId(), TaskConstant.SYS_TASK_FIRST_PHONE_VALIDATE_CODE);

        LOGGER.info("{}", userReturnBO);
        return userReturnBO;
    }

    /**
     * 用户登陆
     *
     * @param bo      LoginBO
     * @param channel 登陆方式：1-用户名/手机号+密码，2-js用户名/手机号+密码，3-手机号+短信验证码，4-openId登陆
     * @return Map:token,expires_in,用户信息
     */
    @Override
    public Map login(LoginBO bo, String channel) {
        LOGGER.info("loginBO:{}, channel:{}", bo, channel);

        // 根据用户名查看用户是否存在
        bo.setUsernameOrPhone(bo.getUsernameOrPhone().trim().toLowerCase());

        //修改登录，从主库查询
        User user = userMapper.selectByUsernameOrPhone(bo);
        if (user == null) {
            LOGGER.warn("登录失败，参数:{}:{}", bo, channel);
            throw new ServiceException(4018);
        }

        // 无效用户不允许登录
        if (!user.getStatus()) {
            throw new ServiceException(4038);
        }
        // 用户账号是否被锁定
        isUserLocked(user.getId());
        // 登录密码进行处理，与表中的加密密码进行比对
        String password;
        try {
            // 先前的加密版本
//            password = Utils.md5(Utils.md5(bo.getPassword()) + user.getSalt());
            // 现在的加密版本
            if ("1".equals(channel)) {
                password = rsaService.decode(bo.getPassword());
            } else if ("2".equals(channel)) {
                password = rsaService.decodeStringFromJs(bo.getPassword());
            } else {
                password = user.getPassword();
            }
            LOGGER.info("password:{}", password);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + e);
            throw new ServiceException(4106);
        }

        // 密码是否输入正确
        if (!user.getPassword().equals(password)) {
            LOGGER.warn("登录失败，参数:{}", bo);
            // 记录用户连续输错密码次数
            continuePasswordWrong(user.getId());
        }

        user.setLastUpdate(new Date());
        int result = userMapper.update(user);
        if (result != 1) {
            LOGGER.warn("登录失败，参数:{}", bo);
            throw new ServiceException(4102);
        }

        Token queryToken = tokenMapper.selectOne(user.getId(), Utils.getAppId());
        // 假如uc_token表有记录（根据userId和appId），则更新，没有则新增
        String userToken = Utils.uuid();
        int result02;
        if (queryToken != null) {
            // 如果token失效则生成新的token
            if ((queryToken.getLastTokenResetTime().getTime() + Constant.USER_TOKEN_VALID_SECONDS * 1000) >= System
                    .currentTimeMillis()) {
                userToken = queryToken.getToken();
            } else {
                queryToken.setToken(userToken);
            }
            queryToken.setLastTokenResetTime(new Date());
            result02 = tokenMapper.update(queryToken);
        } else {
            Token token = new Token();
            token.setId(Utils.uuid());
            token.setAppId(Utils.getAppId());
            token.setUserId(user.getId());
            token.setToken(userToken);
            token.setLastTokenResetTime(new Date());
            result02 = tokenMapper.insert(token);
        }
        if (result02 != 1) {
            LOGGER.warn("登录失败，参数:{}", bo);
            throw new ServiceException(4021);
        }

        // 重置用户连续输错密码记录
        resetContinuePasswordWrong(user.getId());

        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(user, userBO);
        userBO.setPassword(null);
        //用户重要信息模糊化处理:电话号码
        String phone = userBO.getPhone();
        if (!StringUtils.isEmpty(phone) && phone.length() >= 8) {
            userBO.setPhone(new StringBuilder(phone).replace(3, phone.length() - 4, "****").toString());
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("token", userToken);
        map.put("expires_in", Constant.USER_TOKEN_VALID_SECONDS);
        map.put("user", userBO);

        // 登录之后的任务需要
        map.put(Constant.USER_ID, userBO.getId());
        map.put("user_phone", userBO.getPhone());

        // 在request中设置userId，记录日志使用
        Utils.setUserId(userBO.getId());

        // 用户信息写入redis
        valueOperations.set(userToken, JSON.toJSONString(userBO), Constant.USER_TOKEN_VALID_SECONDS / 2,
                TimeUnit.SECONDS);
        return map;
    }

    /**
     * 用户登陆,不做RSA加密
     *
     * @param bo      LoginBO
     * @param channel 登陆方式：1-用户名/手机号+密码，2-js用户名/手机号+密码，3-手机号+短信验证码，4-openId登陆
     * @return Map:token,expires_in,用户信息
     */
    @Override
    public Map testLogin(LoginBO bo, String channel) {
        LOGGER.info("loginBO:{}, channel:{}", bo, channel);

        // 根据用户名查看用户是否存在
        bo.setUsernameOrPhone(bo.getUsernameOrPhone().trim().toLowerCase());

        //修改登录，从主库查询
        User user = userMapper.selectByUsernameOrPhone(bo);
        if (user == null) {
            LOGGER.warn("登录失败，参数:{}:{}", bo, channel);
            throw new ServiceException(4018);
        }

        // 无效用户不允许登录
        if (!user.getStatus()) {
            throw new ServiceException(4038);
        }
        // 用户账号是否被锁定
        isUserLocked(user.getId());
        // 登录密码进行处理，与表中的加密密码进行比对
        String password;
        try {
            // 先前的加密版本
            password = Utils.md5(Utils.md5(bo.getPassword()) + user.getSalt());
            LOGGER.info("password:{}", password);
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + e);
            throw new ServiceException(4106);
        }

        // 密码是否输入正确
        if (!user.getPassword().equals(password)) {
            LOGGER.warn("登录失败，参数:{}", bo);
            // 记录用户连续输错密码次数
            continuePasswordWrong(user.getId());
        }

        user.setLastUpdate(new Date());
        int result = userMapper.update(user);
        if (result != 1) {
            LOGGER.warn("登录失败，参数:{}", bo);
            throw new ServiceException(4102);
        }

        Token queryToken = tokenMapper.selectOne(user.getId(), Utils.getAppId());
        // 假如uc_token表有记录（根据userId和appId），则更新，没有则新增
        String userToken = Utils.uuid();
        int result02;
        if (queryToken != null) {
            // 如果token失效则生成新的token
            if ((queryToken.getLastTokenResetTime().getTime() + Constant.USER_TOKEN_VALID_SECONDS * 1000) >= System
                    .currentTimeMillis()) {
                userToken = queryToken.getToken();
            } else {
                queryToken.setToken(userToken);
            }
            queryToken.setLastTokenResetTime(new Date());
            result02 = tokenMapper.update(queryToken);
        } else {
            Token token = new Token();
            token.setId(Utils.uuid());
            token.setAppId(Utils.getAppId());
            token.setUserId(user.getId());
            token.setToken(userToken);
            token.setLastTokenResetTime(new Date());
            result02 = tokenMapper.insert(token);
        }
        if (result02 != 1) {
            LOGGER.warn("登录失败，参数:{}", bo);
            throw new ServiceException(4021);
        }

        // 重置用户连续输错密码记录
        resetContinuePasswordWrong(user.getId());

        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(user, userBO);
        userBO.setPassword(null);
        //用户重要信息模糊化处理:电话号码
        String phone = userBO.getPhone();
        if (!StringUtils.isEmpty(phone) && phone.length() >= 8) {
            userBO.setPhone(new StringBuilder(phone).replace(3, phone.length() - 4, "****").toString());
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("token", userToken);
        map.put("expires_in", Constant.USER_TOKEN_VALID_SECONDS);
        map.put("user", userBO);

        // 登录之后的任务需要
        map.put(Constant.USER_ID, userBO.getId());
        map.put("user_phone", userBO.getPhone());

        // 在request中设置userId，记录日志使用
        Utils.setUserId(userBO.getId());

        // 用户信息写入redis
        valueOperations.set(userToken, JSON.toJSONString(userBO), Constant.USER_TOKEN_VALID_SECONDS / 2,
                TimeUnit.SECONDS);
        return map;
    }

    @Override
    public boolean isAuthentication(String userToken, HttpServletRequest request) {
        Token token = tokenMapper.isAuthentication(userToken);
        if (token == null) {
            throw new ServiceException(4016);
        }
        long lastTokenResetTime = token.getLastTokenResetTime().getTime();
        long currentTime = System.currentTimeMillis();
        if (currentTime > (lastTokenResetTime + 1000 * Constant.USER_TOKEN_VALID_SECONDS)) {
            throw new ServiceException(4015);
        }
        //把用户Id设置到request
        if (!StringUtils.isEmpty(request.getAttribute(Constant.USER_ID))) {
            request.setAttribute(Constant.USER_ID, token.getUserId());
        }
        return true;
    }

    @Override
    public boolean verifyCode(VerifyingCodeBO loginVerifyingCodeBO, HttpServletRequest request) {
        //不变参数
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/verify";

        //请求头设置
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(Constant.VERSION_HEAD, request.getHeader(Constant.VERSION_HEAD));
        httpHeaders.add(Constant.APP_TOKEN_HEAD, request.getHeader(Constant.APP_TOKEN_HEAD));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("phone", loginVerifyingCodeBO.getPhone());
        requestBody.put("code", loginVerifyingCodeBO.getCode());
        requestBody.put("type", loginVerifyingCodeBO.getType());

        HttpEntity requestEntity = new HttpEntity(requestBody, httpHeaders);
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }

        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
            BaseObject object = JSON.parseObject(String.valueOf(responseEntity.getBody()), BaseObject.class);
            if ("2000".equals(object.getCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout(String token) {
        LOGGER.info("{}", token);
        int result = tokenMapper.delete(token);
        // 用户信息从redis删除
        redisTemplate.delete(token);
        if (result < 1) {
            throw new ServiceException(4022);
        }
    }

    @Override
    public void loginByVerifyFail(VerifyingCodeBO loginBO) {
        User user = null;
        LoginBO loginBOQery = new LoginBO();
        if (!StringUtils.isEmpty(loginBO.getPhone())) {
            loginBOQery.setUsernameOrPhone(loginBO.getPhone().trim());
            user = userMapper.selectByUsernameOrPhone(loginBOQery);
        }
        if (user != null) {
            continuePasswordWrong(user.getId());
        }
        throw new ServiceException(4201);
    }

    @Override
    public String verifyPhone(String phone) throws Exception {
        LoginBO loginBO = new LoginBO();
        loginBO.setUsernameOrPhone(phone);
        User user = userMapper.selectByUsernameOrPhone(loginBO);
        if (user == null) {
            LOGGER.warn("登录失败，该用户不存在，参数:{}", phone);
            throw new ServiceException(4018);
        }

        //无效用户
        if (!user.getStatus()) {
            throw new ServiceException(4038);
        }

        String userToken = Utils.token(Utils.uuid());
        String appId = Utils.getAppId();
        Token queryToken = tokenRoMapper.selectOne(user.getId(), appId);
        int result;
        //加入uc_token表有记录（根据userId和appId），则更新，没有则新增，重置密码时需要带上此token
        if (queryToken != null) {
            //如果token失效则生成新的token
            if ((queryToken.getLastTokenResetTime().getTime() + Constant.USER_TOKEN_VALID_SECONDS * 1000) >= System
                    .currentTimeMillis()) {
                userToken = queryToken.getToken();
            } else {
                queryToken.setToken(userToken);
            }
            queryToken.setLastTokenResetTime(new Date());
            result = tokenMapper.update(queryToken);
        } else {
            Token token = new Token();
            token.setId(Utils.uuid());
            token.setAppId(appId);
            token.setUserId(user.getId());
            token.setToken(userToken);
            token.setLastTokenResetTime(new Date());
            result = tokenMapper.insert(token);
        }
        if (result != 1) {
            LOGGER.warn("设置token失败，参数:{}:{}", phone, appId);
            throw new ServiceException(4101);
        }

        return userToken;
    }

    @Override
    public boolean resetPasswordByPhone(ResetPasswordBO bo) throws Exception {
        // 判断用户是否存在
        LoginBO loginBO = new LoginBO();
        loginBO.setUsernameOrPhone(bo.getPhone());
        User userExist = userMapper.selectByUsernameOrPhone(loginBO);
        if (userExist == null) {
            throw new ServiceException(4018);
        }

        // 验证手机号时存储的token是否与用户传输的相同
        Token queryToken = tokenRoMapper.selectOne(userExist.getId(), Utils.getAppId());
        if (queryToken == null || !bo.getToken().equals(queryToken.getToken())) {
            throw new ServiceException(4023);
        }

        String password = rsaService.decode(bo.getPassword());
        String encodePassword = Utils.md5(password + userExist.getSalt());

        // 修改密码不能为旧密码
        if (encodePassword.equals(userExist.getPassword())) {
            throw new ServiceException(4040);
        }

        // 更新密码
        User user = new User();
        user.setId(userExist.getId());
        user.setPassword(encodePassword);
        user.setLastUpdate(new Date());
        int result = userMapper.updatePassword(user);
        if (result != 1) {
            throw new ServiceException(4023);
        }
        // 删除token
        tokenMapper.delete(bo.getToken());
        return true;
    }

    @Async
    @Override
    public CompletableFuture<BodyStatus> todoAfterLogin(Map map) {
        LOGGER.info("记录用户IP归属");
        String userId = String.valueOf(map.get(Constant.USER_ID));
        if (!StringUtils.isEmpty(map.get(Constant.CLIENT_IP))
                && !StringUtils.isEmpty(userId)) {
            ipService.merge(String.valueOf(map.get(Constant.CLIENT_IP)), userId);
        }

        LOGGER.info("登录删除用户缓存，防止缓存不及时刷新:{}", userId);
        redisTemplate.delete(userId + "_Points");
        redisTemplate.delete(userId + "_MyExperience");

        LOGGER.info("如果用户当天定时任务没有完成，就在登录的时候生成:{}", userId);
        todoTaskService.generateAllTodoTaskList(userId);

        LOGGER.info("登录任务日志:{}", userId);
        boolean loginTask = todoTaskService.doTaskWithouComputeAward(userId, TaskConstant.SYS_TASK_LOGIN_CODE);

        LOGGER.info("用户完成登录任务结果：{}",loginTask);
        if (loginTask) {
            LOGGER.info("计算用户登录经验值变化:{}", userId);
            computeExp(userId);
        }

        LOGGER.info("记用户登录日志:{}", userId);
        insertLoginLog(userId);

        LOGGER.info("首次绑定手机任务埋点:{}", userId);
        if (!StringUtils.isEmpty(map.get("user_phone"))) {
            todoTaskService.doTask(userId, TaskConstant.SYS_TASK_FIRST_PHONE_VALIDATE_CODE);
        }

        try {
            LOGGER.info("发消息:{}", userId);
            userFeedbackMsgService.unrealname(userId);
            userFeedbackMsgService.check(userId);
            //暂时屏蔽未做任务消息提醒
            //userFeedbackMsgService.undotask(userId);
            LOGGER.info("发送运营消息:{}", userId);
            operateMessageService.send(userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户登录后发送消息提醒异常：{}", e);
        }
        return CompletableFuture.completedFuture(Utils.bodyStatus(2000));
    }

    /**
     * 用户输错密码（或者验证码登录的验证码）做记录
     *
     * @param userId 用户ID
     */
    private void continuePasswordWrong(String userId) {
        //记录用户连续输错密码次数
        List<UserLoginPasswordWrongCount> wrongCountList = userMapper.selectContinuePwdWrong(userId);
        UserLoginPasswordWrongCount wrongCount;
        if (wrongCountList == null || wrongCountList.size() != 1) {
            if (wrongCountList != null && wrongCountList.size() > 1) {
                userMapper.deleteContinuePwdWrong(userId);
            }
            wrongCount = new UserLoginPasswordWrongCount(Utils.uuid(), userId, 1, new Date());
            userMapper.insertContinuePwdWrong(wrongCount);
        } else {
            wrongCount = wrongCountList.get(0);
            wrongCount.setCount(wrongCount.getCount() + 1);
            if (wrongCount.getCount() >= Constant.USER_CONTINUE_PASSWORD_WRONG_MAX) {
                Date limitTime = new Date();
                limitTime.setTime(limitTime.getTime() + Constant.LOCK_TIME);
                wrongCount.setCount(0);
                wrongCount.setLimitTime(limitTime);
                userMapper.updateContinuePwdWrong(wrongCount);
                throw new ServiceException(4280);
            }
            userMapper.updateContinuePwdWrong(wrongCount);
        }
        throw new ServiceException("1234", "密码(或验证码)输入错误 " + wrongCount.getCount() + " 次，连续输错5次该账户将被锁定！");
    }

    /**
     * 用户号码是否因为连续输错密码被锁
     *
     * @param userId 用户ID
     */
    private void isUserLocked(String userId) {
        List<UserLoginPasswordWrongCount> wrongCountList = userMapper.selectContinuePwdWrong(userId);
        if (wrongCountList != null && wrongCountList.size() > 0) {
            UserLoginPasswordWrongCount wrongCount = wrongCountList.get(0);
            if (wrongCount.getLimitTime().getTime() >= System.currentTimeMillis()) {
                throw new ServiceException(4280);
            }
        }
    }

    /**
     * 重置用户连续输错密码(或者验证码登录的验证码)记录
     *
     * @param userId 用户ID
     */
    private void resetContinuePasswordWrong(String userId) {
        List<UserLoginPasswordWrongCount> wrongCountList = userMapper.selectContinuePwdWrong(userId);
        UserLoginPasswordWrongCount wrongCount;
        if (wrongCountList == null || wrongCountList.size() != 1) {
            if (wrongCountList != null && wrongCountList.size() > 1) {
                userMapper.deleteContinuePwdWrong(userId);
            }
            wrongCount = new UserLoginPasswordWrongCount(Utils.uuid(), userId, 0, new Date());
            userMapper.insertContinuePwdWrong(wrongCount);
        } else {
            wrongCount = wrongCountList.get(0);
            wrongCount.setCount(0);
            userMapper.updateContinuePwdWrong(wrongCount);
        }
    }

    public void computeExp(String userId) {
        //今日第一次登录才能获取经验值
//        if (!isContinueLogin(userId, 0)) {
            //判断用户连续登录情况
            int exp = 3;
            if (isContinueLogin(userId, 1)) {
                exp = 5;
                if (isContinueLogin(userId, 2)) {
                    exp = 8;
                    if (isContinueLogin(userId, 3)) {
                        exp = 10;
                    }
                }
            }

            //查询系统任务
            //新的查询系统任务方法：根据编码查询
            ExperienceRuleBO experienceRuleBO = experienceRuleService.selectValidOneByCode(TaskConstant
                    .EXP_RULE_LOGIN_CODE);
            if (experienceRuleBO == null) {
                return;
            }

            ExperienceLogBO logBO = new ExperienceLogBO();
            logBO.setId(Utils.uuid());
            logBO.setIncome(exp);
            logBO.setUserId(userId);
            logBO.setRuleId(experienceRuleBO.getId());
            logBO.setOutgo(0);
            logBO.setCreateTime(new Date());
            experienceLogService.insert(logBO);
//        }
    }

    private boolean isContinueLogin(String userId, int i) {
        Map<String, Object> map = new HashMap<>(16);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -i);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MINUTE, 0);

        calendar2.add(Calendar.DATE, -(i - 1));
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MINUTE, 0);

        map.put("userId", userId);
        map.put("startTime", calendar1.getTime());
        map.put("endTime", calendar2.getTime());
        List<UcUserLoginLog> logList = loginLogRoMapper.selectLoginLogList(map);
        return logList != null && logList.size() > 0;
    }

    public void insertLoginLog(String userId) {
        UcUserLoginLog loginLog = new UcUserLoginLog();
        loginLog.setId(Utils.uuid());
        loginLog.setUserId(userId);
        loginLog.setCreateTime(new Date());
        loginLogMapper.insert(loginLog);
    }
}