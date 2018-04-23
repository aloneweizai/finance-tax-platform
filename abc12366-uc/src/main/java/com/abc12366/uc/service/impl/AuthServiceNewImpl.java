package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.TokenMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.TokenRoMapper;
import com.abc12366.uc.model.Token;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.UserLoginPasswordWrongCount;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.*;
import com.abc12366.uc.util.RandomNumber;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceNewImpl implements AuthServiceNew {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceNewImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserExtendService userExtendService;

	@Autowired
	private TodoTaskService todoTaskService;

	@Autowired
	private TokenMapper tokenMapper;

	@Autowired
	private TokenRoMapper tokenRoMapper;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOperations;

	@Autowired
	private UserFeedbackMsgService userFeedbackMsgService;

	@Autowired
	private RSAService rsaService;

	@Override
	@Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
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
			// 密码生产规则：前台传密码md5之后的值，后台用该值加上salt再md5 ，salt是随机生成的六位整数
			// password = Utils.md5(registerBO.getPassword());
			// salt = Utils.salt();
			// encodePassword = Utils.md5(password + salt);

			// 新的密码规则
			// password = rsaService.decode(registerBO.getPassword());

			password = rsaService.decodenNew(registerBO.getPassword());
			salt = Utils.salt();
			encodePassword = Utils.md5(password + salt);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + e);
			throw new ServiceException(4106);
		}
		user = new User();
		BeanUtils.copyProperties(registerBO, user);

		user.setId(Utils.uuid());
		// 用户名（字母UC+时间戳毫秒数）
		user.setUsername("uc" + System.currentTimeMillis());
		// 自动生成用户昵称:（财税+6位数字）
		user.setNickname("财税" + RandomNumber.getRandomNumber(6));
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

		// 注册用户增加地址：province、city到uc_userExtend表
		UserExtendBO userExtend = new UserExtendBO();
		userExtend.setUserId(user.getId());
		userExtend.setProvince(registerBO.getProvince());
		userExtend.setCity(registerBO.getCity());
		userExtendService.insert(userExtend, request);

		UserReturnBO userReturnBO = new UserReturnBO();
		BeanUtils.copyProperties(user, userReturnBO);

		// 首次绑定手机任务埋点
		todoTaskService.doTask(user.getId(), TaskConstant.SYS_TASK_FIRST_PHONE_VALIDATE_CODE);

		LOGGER.info("{}", userReturnBO);
		return userReturnBO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map login(LoginBO bo, String channel) {
		LOGGER.info("loginBO:{}, channel:{}", bo, channel);

		// 根据用户名查看用户是否存在
		bo.setUsernameOrPhone(bo.getUsernameOrPhone().trim().toLowerCase());

		// 修改登录，从主库查询
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
			// password = Utils.md5(Utils.md5(bo.getPassword()) +
			// user.getSalt());
			// 现在的加密版本
			if ("1".equals(channel)) {
				// 新加密后台MD5
				password = Utils.md5(rsaService.decodenNew(bo.getPassword()) + user.getSalt());
			} else if ("2".equals(channel)) {
				// 新加密后台MD5
				password = Utils.md5(rsaService.decodeStringFromJsNew(bo.getPassword()) + user.getSalt());

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
		// 用户重要信息模糊化处理:电话号码
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
	 * 用户输错密码（或者验证码登录的验证码）做记录
	 *
	 * @param userId
	 *            用户ID
	 */
	private void continuePasswordWrong(String userId) {
		// 记录用户连续输错密码次数
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
	 * @param userId
	 *            用户ID
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
	 * @param userId
	 *            用户ID
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
		Token queryToken = tokenMapper.selectOne(userExist.getId(), Utils.getAppId());
		if (queryToken == null || !bo.getToken().equals(queryToken.getToken())) {
			throw new ServiceException(4023);
		}

		String password = rsaService.decodenNew(bo.getPassword());
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

	@Override
	public Boolean updatePassword(PasswordUpdateBO passwordUpdateBO, HttpServletRequest request) {
		LOGGER.info("{}", passwordUpdateBO);
		String userId = Utils.getUserId();
		// 判断用户是否存在
		User userExist = userMapper.selectOne(userId);
		if (userExist == null) {
			throw new ServiceException(4018);
		}

		// 判断是否有用户token请求头
		String token = request.getHeader(Constant.USER_TOKEN_HEAD);
		if (token == null || "".equals(token)) {
			throw new ServiceException(4199);
		}

		// 判断库表是否存在该token
		Token tokenExist = tokenRoMapper.isAuthentication(token);
		if (tokenExist == null) {
			throw new ServiceException(4179);
		}

		// 判断user-token是否与被修改用户是同一个
		if (!userExist.getId().equals(tokenExist.getUserId())) {
			throw new ServiceException(4191);
		}

		// 密码加密
		// String encodePassword =
		// PasswordUtils.encodePassword(passwordUpdateBO.getPassword(),
		// userExist.getSalt());

		// 新的加密
		String encodePassword = rsaService.decodenNew(passwordUpdateBO.getPassword());

		// 修改密码不能为旧密码
		if (encodePassword.equals(userExist.getPassword())) {
			throw new ServiceException(4040);
		}

		// 改库..
		User user = new User();
		user.setId(userExist.getId());
		user.setPassword(encodePassword);
		user.setLastUpdate(new Date());
		int result = userMapper.updatePassword(user);
		if (result != 1) {
			throw new ServiceException(4023);
		}
		// 删除token
		tokenMapper.delete(token);

		try {
			// 发消息
			userFeedbackMsgService.updatePasswordSuccessNotice(userId);
			// 首次修改密码任务埋点
			todoTaskService.doTask(userExist.getId(), TaskConstant.SYS_TASK_FIRST_UPDATE_PASSWROD_CODE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map dbAppUserLogin(String userToken) {
		Map<String, Object> map = new HashMap<>(16);
		UserBO userBO = userMapper.selectOneByToken(userToken);
		String tokens = Utils.uuid();
		int result02 = 0;
		if (userBO == null) {
			LOGGER.error("登录失败，参数:{}", userToken);
			throw new ServiceException(4018);
		}
		LOGGER.info("获取到用户信息:{}", JSONObject.toJSONString(userBO));
		Token queryToken = tokenMapper.selectOne(userBO.getId(), Utils.getAppId());
		LOGGER.info("获取到用户token信息:{}", JSONObject.toJSONString(queryToken));
		if (queryToken != null){
			if(queryToken.getLastTokenResetTime().getTime() + Constant.USER_TOKEN_VALID_SECONDS * 1000 >= System
					.currentTimeMillis()){// 当B应用有对应的用户登录且在有效期内时，返回原有token
				tokens = queryToken.getToken();
			}else{// 当B应用有对应的用户登录且在不在有效期内时，更新token
				queryToken.setToken(tokens);
			}
			queryToken.setLastTokenResetTime(new Date());
			result02 = tokenMapper.update(queryToken);
		}else {// 当B应用没有对应的用户没有登录或已过期时，将A应用的用户token作为B应用的用户token，并更新有效期
			Token token = new Token();
			token.setId(Utils.uuid());
			token.setAppId(Utils.getAppId());
			token.setUserId(userBO.getId());
			token.setToken(tokens);
			token.setLastTokenResetTime(new Date());
			result02 = tokenMapper.insert(token);	
		}
		if (result02 != 1) {
			LOGGER.warn("登录失败，参数:{}", userToken);
			throw new ServiceException(4021);
		}
		
		// 用户重要信息模糊化处理:电话号码
		String phone = userBO.getPhone();
		if (!StringUtils.isEmpty(phone) && phone.length() >= 8) {
			userBO.setPhone(new StringBuilder(phone).replace(3, phone.length() - 4, "****").toString());
		}
		
		map.put("token", tokens);
		map.put("expires_in", Constant.USER_TOKEN_VALID_SECONDS);
		map.put("user", userBO);
		return map;
	}

	@Override
	public List<Token> getActiveToken() {
		return tokenMapper.getActiveToken();
	}

}
