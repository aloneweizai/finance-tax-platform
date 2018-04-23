package com.abc12366.uc.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.*;
import com.abc12366.uc.job.reportdate.ReportDateJob;
import com.abc12366.uc.mapper.db1.TokenMapper;
import com.abc12366.uc.mapper.db1.UamountLogMapper;
import com.abc12366.uc.mapper.db1.UserExtendMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.ExperienceLevelRoMapper;
import com.abc12366.uc.mapper.db2.TokenRoMapper;
import com.abc12366.uc.mapper.db2.UserRoMapper;
import com.abc12366.uc.mapper.db2.VipPrivilegeLevelRoMapper;
import com.abc12366.uc.model.*;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.model.gift.UamountLog;
import com.abc12366.uc.service.*;
import com.abc12366.uc.service.admin.AdminOperationService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-22 10:17 AM
 * @since 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoMapper userRoMapper;

	@Autowired
	private UserExtendMapper userExtendMapper;

	@Autowired
	private TokenMapper tokenMapper;

	@Autowired
	private TokenRoMapper tokenRoMapper;

	@Autowired
	private RSAService rsaService;

	@Autowired
	private TodoTaskService todoTaskService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AuthService authService;

	/**
	 * 会员日志服务
	 */
	@Autowired
	private VipLogService vipLogService;

	@Autowired
	private UserFeedbackMsgService userFeedbackMsgService;

	@Autowired
	private ExperienceLevelService experienceLevelService;

	@Autowired
	private VipLevelService vipLevelService;

	@Autowired
	private ExperienceLogService experienceLogService;

	@Autowired
	private ExperienceLevelRoMapper experienceLevelRoMapper;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private AdminOperationService adminOperationService;

    @Autowired
    private VipPrivilegeLevelRoMapper vipPrivilegeLevelRoMapper;

    @Autowired
    private UamountLogMapper uamountLogMapper;

    @Override
	public List<UserListBO> selectList(Map<String, Object> map, int page,
			int size) {

		// 解析多标签名称参数
		String tagId = "tagId";
		List tagIdList = new ArrayList<>();
		if (!StringUtils.isEmpty(map.get(tagId))) {
			tagIdList = analysisTagId((String) map.get(tagId), ",");
		}
		map.put(tagId, tagIdList);
		map.put("tagIdCount", (tagIdList == null) ? 0 : tagIdList.size());

		PageHelper.startPage(page, size, true).pageSizeZero(true)
				.reasonable(true);
		List<UserListBO> userList = userMapper.selectList(map);

		// 补充真实姓名、用户等级信息
		for (UserListBO user : userList) {
			UserExtend ue = userExtendMapper.selectOneForAdmin(user.getId());
			if (ue != null) {
				user.setRealName(ue.getRealName());
			}
			if (user.getExp() != null
					&& !"".equals(String.valueOf(user.getExp()))) {
				ExperienceLevelBO el = experienceLevelService.selectOne(user
						.getExp());
				if (el != null) {
					user.setMedal(el.getMedal());
					user.setLevelName(el.getName());
					user.setMedalIcon(el.getMedalIcon());
				}
			}
		}
		return userList;
	}

	/**
	 * 逗号分隔的标签ID转为List
	 *
	 * @param tagId
	 *            带逗号分隔的标签ID
	 * @param split
	 *            分隔符
	 * @return ID列表
	 */
	private List analysisTagId(String tagId, String split) {
		String[] tags = tagId.trim().split(split);
		List list = Arrays.asList(tags);
		// 去除空的元素
		for (int i = 0; i < list.size(); i++) {
			if (StringUtils.isEmpty(list.get(i))) {
				list.remove(i);
			}
		}
		return list;
	}

	@Override
	public User selectUser(String userId) {
		// 新增优先查询redis
		User user = userMapper.selectOne(userId);

		return user;
	}

	@Override
	public Map selectOne(String userId) {
		// 新增优先查询redis
		LOGGER.info("{}", userId);
		User user = userMapper.selectOne(userId);
		UserExtend userExtend = userExtendMapper.selectOne(userId);
     	if (user != null) {

			// 用户重要信息模糊化处理:电话号码
			if (!StringUtils.isEmpty(user.getPhone())
					&& user.getPhone().length() >= 8) {
				String phone = user.getPhone();
				StringBuilder phoneFuffer = new StringBuilder(phone);
				user.setPhone(phoneFuffer
						.replace(3, phone.length() - 4, "****").toString());
			}
			user.setPassword(null);
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			map.put("user_extend", userExtend);
			LOGGER.info("{}", map);
			return map;
		}
		return null;
	}

	@Override
	public void enableOrDisable(String id, String status) {
		LOGGER.info("{}:{}", id, status);
		if ((!status.equals("true")) && (!status.equals("false"))) {
			throw new ServiceException(4614);
		}
		boolean modifyStatus = status.equals("true");
		User obj = new User();
		obj.setId(id);
		obj.setStatus(modifyStatus);
		obj.setLastUpdate(new Date());
		int result = userMapper.update(obj);
		if (result < 1) {
			if (modifyStatus) {
				throw new ServiceException(4623);
			}
			throw new ServiceException(4624);
		}
	}

	@Override
	public UserBO update(UserUpdateBO userUpdateBO) {
		LOGGER.info("{}", userUpdateBO);
		// 用户名转成小写
		if (!StringUtils.isEmpty(userUpdateBO.getUsername())) {
			userUpdateBO.setUsername(userUpdateBO.getUsername().trim()
					.toLowerCase());
		}

		User user = selectUser(userUpdateBO.getId());
		if (user == null) {
			LOGGER.warn("修改失败");
			throw new ServiceException(4018);
		}

		// 普通用户只允许修改一次用户名
		if (!StringUtils.isEmpty(userUpdateBO.getUsername())
				&& !userUpdateBO.getUsername().trim()
						.equals(user.getUsername())
				&& !StringUtils.isEmpty(user.getUsernameModifiedTimes())
				&& user.getUsernameModifiedTimes() >= 1) {
			throw new ServiceException(4037);
		}

		// 进行用户名唯一性确认
		if (userUpdateBO.getUsername() != null) {
			LoginBO loginBO = new LoginBO();
			loginBO.setUsernameOrPhone(userUpdateBO.getUsername());
			User userOnly = userMapper.selectByUsernameOrPhone(loginBO);
			if (userOnly != null
					&& !userOnly.getId().equals(userUpdateBO.getId())) {
				throw new ServiceException(4182);
			}
		}
		if (userUpdateBO.getUsername() != null
				&& !user.getUsername().equals(userUpdateBO.getUsername())) {
			if (!StringUtils.isEmpty(user.getUsernameModifiedTimes())) {
				user.setUsernameModifiedTimes(user.getUsernameModifiedTimes() + 1);
			} else {
				user.setUsernameModifiedTimes(1);
			}
		}
		BeanUtils.copyProperties(userUpdateBO, user);
		user.setLastUpdate(new Date());
		int result = userMapper.update(user);
		if (result != 1) {
			LOGGER.warn("修改失败");
			throw new ServiceException(4102);
		}

		if (user.getUserPicturePath() != null
				&& !user.getUserPicturePath().trim().equals("")) {
			// 首次上传用户头像任务埋点
			todoTaskService.doTask(user.getId(),
					TaskConstant.SYS_TASK_FIRST_UPLOAD_PICTURE_CODE);
		}

		UserBO userDTO = new UserBO();
		BeanUtils.copyProperties(user, userDTO);
		userDTO.setPassword(null);

		// 用户重要信息模糊化处理:电话号码
		if (!StringUtils.isEmpty(userDTO.getPhone())
				&& userDTO.getPhone().length() >= 8) {
			String phone = userDTO.getPhone();
			StringBuilder phoneFuffer = new StringBuilder(phone);
			userDTO.setPhone(phoneFuffer.replace(3, phone.length() - 4, "****")
					.toString());
		}
		LOGGER.info("{}", userDTO);


		return userDTO;
	}

	@Override
	public UserBO selectByUsernameOrPhone(String usernameOrPhone) {
		LOGGER.info("{}", usernameOrPhone);
		LoginBO loginBO = new LoginBO();
		loginBO.setUsernameOrPhone(usernameOrPhone);
		User user = userMapper.selectByUsernameOrPhone(loginBO);
		if (user != null) {
			UserBO userDTO = new UserBO();
			BeanUtils.copyProperties(user, userDTO);
			// 用户重要信息模糊化处理:电话号码
			if (!StringUtils.isEmpty(userDTO.getPhone())
					&& userDTO.getPhone().length() >= 8) {
				String phone = userDTO.getPhone();
				StringBuilder phoneFuffer = new StringBuilder(phone);
				user.setPhone(phoneFuffer
						.replace(3, phone.length() - 4, "****").toString());
			}
			userDTO.setPassword(null);
			LOGGER.info("{}", userDTO);
			return userDTO;
		}
		return null;
	}

	@Transactional("db1TxManager")
	@Override
	public UserBO delete(String userId) {
		LOGGER.info("{}", userId);
		User user = selectUser(userId);
		if (user != null) {
			int result = userMapper.delete(userId);
			if (result > 0) {
				UserBO userBO = new UserBO();
				BeanUtils.copyProperties(user, userBO);
				LOGGER.info("{}", userBO);

				return userBO;
			}
		}
		return null;
	}

	@Override
	public UserBO authAndRefreshToken(String token) {
		LOGGER.info("{}", token);
		UserBO user = userMapper.selectOneByToken(token);
		if (user != null) {
			tokenMapper.updateLastTokenResetTime(token);
			// 用户重要信息模糊化处理:电话号码
			if (!StringUtils.isEmpty(user.getPhone())
					&& user.getPhone().length() >= 8) {
				String phone = user.getPhone();
				StringBuilder phoneFuffer = new StringBuilder(phone);
				user.setPhone(phoneFuffer
						.replace(3, phone.length() - 4, "****").toString());
			}
			user.setPassword(null);
		}
		return user;
	}

	@Transactional(value = "db1TxManager", rollbackFor = ServiceException.class)
	@Override
	public Boolean updatePassword(PasswordUpdateBO passwordUpdateBO,
			HttpServletRequest request) {
		LOGGER.info("{}", passwordUpdateBO);
		String userId = Utils.getUserId();
		// 判断用户是否存在
		User userExist = selectUser(userId);
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
		String encodePassword = rsaService.decode(passwordUpdateBO
				.getPassword());

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
			todoTaskService.doTask(userExist.getId(),
					TaskConstant.SYS_TASK_FIRST_UPDATE_PASSWROD_CODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void updateUserVipInfo(String userId, String vipLevel, boolean isGive) {
		if (StringUtils.isEmpty(vipLevel)) {
			LOGGER.info("更新会员失败，因为传入的用户等级编码不在约定之中：{}", vipLevel);
			return;
		}
		if (!vipLevel.trim().equals(Constant.USER_VIP_LEVEL_1)
				&& !vipLevel.trim().equals(Constant.USER_VIP_LEVEL_2)
				&& !vipLevel.trim().equals(Constant.USER_VIP_LEVEL_3)
				&& !vipLevel.trim().equals(Constant.USER_VIP_LEVEL_4)) {
			LOGGER.info("更新会员失败，因为传入的用户等级编码不在约定之中：{}", vipLevel);
			return;
		}
		// 会员到期日为明年的今天
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1); // 年份加1
		calendar.add(Calendar.MONTH, 0);// 月份不变
		calendar.add(Calendar.DATE, 0);// 日期不变
		// 时分秒设为：23:59:59
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		User userTmp = selectUser(userId);
		if (userTmp == null) {
			throw new ServiceException(4018);
		}

		// 用户会员等级发生变化，则会员有效时间直接覆盖原有的，否则延长一年
		if (vipLevel.trim().toUpperCase().equals(userTmp.getVipLevel())) {
			calendar.setTime(userTmp.getVipExpireDate());
			calendar.add(Calendar.YEAR, 1); // 年份加1
		}

		//查询会员礼包业务
		User temp = userMapper.selectOne(userId);
		double usable = 0;
		if (temp.getAmount() != null) {
			usable = temp.getAmount();
		}
		VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
		obj.setLevelId(vipLevel.trim().toUpperCase());
		obj.setPrivilegeId(MessageConstant.HYLB_CODE);
		//查看会员礼包是否启用
		VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);
		if (findObj != null && findObj.getStatus() && isGive) {
			UamountLog uamountLog = new UamountLog();
			uamountLog.setId(Utils.uuid());
			uamountLog.setBusinessId(MessageConstant.HYLB_CODE);
			uamountLog.setUserId(userId);
			uamountLog.setCreateTime(new Date());
			uamountLog.setRemark("充值会员，获得礼包金额");
			//赠送金额
			double income = Double.parseDouble(findObj.getVal1());
			double amount = 0;
			if (temp.getAmount() != null) {
				amount = temp.getAmount();
			}
            usable = amount + income;
			uamountLog.setIncome(income);
			uamountLog.setUsable(usable);
			//插入礼包金额记录
			uamountLogMapper.insert(uamountLog);
			//修改礼包金额
			/*User temp = new User();
			temp.setId(user.getId());
			temp.setAmount(usable);
			userMapper.update(temp);*/
		}

		User user = new User();
		user.setId(userId);
		user.setVipLevel(vipLevel.trim().toUpperCase());
		user.setVipExpireDate(calendar.getTime());
		user.setLastUpdate(new Date());
        //会员礼包金额
        user.setAmount(usable);
		userMapper.update(user);

	}

	@Override
	public UserBO selectByopenid(String openid) {
		return userMapper.selectByopenid(openid);
	}

	@Override
	public void automaticUserCancel() {
		List<User> userList = userMapper.selectUserVipList(new Date());
		LOGGER.info("VIP到期，自动取消", userList);
		for (User user : userList) {
			LOGGER.info("VIP到期，取消用户", user);
			// 更新会员状态
			user.setVipLevel(Constant.USER_ORIGINAL_LEVEL);
			user.setLastUpdate(new Date());
			userMapper.update(user);

			// 插入会员日志
			VipLogBO bo = new VipLogBO();
			bo.setLevelId(user.getVipLevel());
			bo.setSource("系统管理员");
			bo.setUserId(user.getId());
			vipLogService.insert(bo,2);

		}
	}

	@Override
	public UserSimpleInfoBO selectSimple(String userId) {
		return userMapper.selectSimple(userId);
	}

	@Override
	public void bindPhone(BindPhoneBO bindPhoneBO) {
		LOGGER.info("绑定手机输入信息：{}", bindPhoneBO.toString());
		User user = selectUser(bindPhoneBO.getUserId());
		// 判断用户是否存在
		if (user == null) {
			throw new ServiceException(4018);
		}

		// 新的绑定手机是否已被绑定过
		LoginBO loginBO = new LoginBO();
		loginBO.setUsernameOrPhone(bindPhoneBO.getNewPhone());
		User userPhoneExist = userMapper.selectByUsernameOrPhone(loginBO);
		// 该手机号码已被绑定
		if (userPhoneExist != null
				&& !bindPhoneBO.getUserId().equals(userPhoneExist.getId())) {
			throw new ServiceException(4858);
		}

		// 如果用户已绑定电话，则对传入的旧电话号码进行校验
		if (user.getPhone() != null && !user.getPhone().trim().equals("")) {
			// 旧的手机号码不能为空
			if (StringUtils.isEmpty(bindPhoneBO.getOldPhone())) {
				throw new ServiceException(4856);
			}
			// 旧手机号码需和已绑定手机一致
			if (!bindPhoneBO.getOldPhone().equals(user.getPhone())) {
				throw new ServiceException(4857);
			}
			// 新的绑定手机号码和旧的是同一个号码
			if (bindPhoneBO.getOldPhone().equals(bindPhoneBO.getNewPhone())) {
				throw new ServiceException(4859);
			}
		}

		// 验证码校验
		VerifyingCodeBO verifyingCodeBO = new VerifyingCodeBO();
		verifyingCodeBO.setPhone(bindPhoneBO.getNewPhone());
		verifyingCodeBO.setType(bindPhoneBO.getType());
		verifyingCodeBO.setCode(bindPhoneBO.getCode());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (!authService.verifyCode(verifyingCodeBO, request)) {
			throw new ServiceException(4201);
		}

		User userPhone = new User();
		userPhone.setId(user.getId());
		userPhone.setPhone(bindPhoneBO.getNewPhone());
		LOGGER.info("用户绑定手机号：{}", userPhone.toString());
		int result = userMapper.updatePhone(userPhone);
		if (result != 1) {
			LOGGER.warn("修改失败");
			throw new ServiceException(4102);
		}


	}

	@Override
	public void loginedSendCode(LoginedSendCodeBO sendCodeBO) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		User user = selectUser(sendCodeBO.getUserId());
		if (user == null) {
			throw new ServiceException(4018);
		}
		if (user.getPhone() == null || user.getPhone().trim().equals("")) {
			throw new ServiceException(4184);
		}

		// 不变参数
		String url = SpringCtxHolder.getProperty("abc12366.message.url")
				+ "/getcode";

		// 请求头设置
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(Constant.VERSION_HEAD,
				request.getHeader(Constant.VERSION_HEAD));
		httpHeaders.add(Constant.APP_TOKEN_HEAD,
				request.getHeader(Constant.APP_TOKEN_HEAD));

		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("phone", user.getPhone());
		requestBody.put("type", sendCodeBO.getType());

		HttpEntity requestEntity = new HttpEntity(requestBody, httpHeaders);
		ResponseEntity responseEntity;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.POST,
					requestEntity, String.class);
		} catch (Exception e) {
			throw new ServiceException(4821);
		}

		if (responseEntity != null
				&& responseEntity.getStatusCode().is2xxSuccessful()
				&& responseEntity.hasBody()) {
			BaseObject object = JSON.parseObject(
					String.valueOf(responseEntity.getBody()), BaseObject.class);
			if (object.getCode().equals("2000")) {
				// return true;
			}
		}
	}

	@Override
	public UserBO updatePhone(UserPhoneBO bo) {
		User user = selectUser(bo.getId());
		if (user == null) {
			LOGGER.warn("修改失败");
			throw new ServiceException(4018);
		}
		String oldPhone = user.getPhone();
		String oldUsername = user.getUsername();

		if (!StringUtils.isEmpty(bo.getPhone())) {
			LoginBO loginBO = new LoginBO();
			loginBO.setUsernameOrPhone(bo.getPhone());
			User userTmp = userMapper.selectByUsernameOrPhone(loginBO);
			if (null != userTmp && !userTmp.getId().equals(bo.getId())) {
				throw new ServiceException(4183);
			}
		}
		if (!StringUtils.isEmpty(bo.getUsername())) {
			LoginBO loginBO = new LoginBO();
			loginBO.setUsernameOrPhone(bo.getUsername());
			User userTmp = userMapper.selectByUsernameOrPhone(loginBO);
			if (null != userTmp && !userTmp.getId().equals(bo.getId())) {
				throw new ServiceException(4182);
			}
		}

		user.setLastUpdate(new Date());
		user.setPhone(bo.getPhone());
		user.setUsername(StringUtils.isEmpty(bo.getUsername())?null:bo.getUsername());
		int result = userMapper.updatePhone(user);
		if (result != 1) {
			LOGGER.warn("修改失败");
			throw new ServiceException(4102);
		}
		//管理员修改用户手机记日志
		try{
			adminOperationService.insert(new AdminModifyUserPhoneLogBO(bo.getId(), Utils.getAdminId(), oldPhone,bo.getPhone(), bo.getReason(),oldUsername,bo.getUsername()));
		} catch (Exception e){
			e.printStackTrace();
		}

		UserBO userDTO = new UserBO();
		BeanUtils.copyProperties(user, userDTO);
		userDTO.setPassword(null);
		return userDTO;
	}

	@Override
	public void phoneLoginSendCode(SendPhoneCodeParam sendCodeBO) {
		LOGGER.info("用户手机+验证码登录获取手机验证码参数：{}", sendCodeBO.toString());
		LoginBO loginBO = new LoginBO();
		loginBO.setUsernameOrPhone(sendCodeBO.getPhone());
		User user = userMapper.selectByUsernameOrPhone(loginBO);
		// 判断手机号码是否注册
		if (user == null) {
			LOGGER.warn("此号码未注册，不允许通过验证码登录：{}", loginBO.toString());
			throw new ServiceException(4823);
		}
		// 判断用户是否激活状态
		if (!user.getStatus()) {
			throw new ServiceException(4038);
		}
		// 判断用户的会员身份
		if (user.getVipLevel() == null
				|| user.getVipLevel().equals(Constant.USER_ORIGINAL_LEVEL)) {
			throw new ServiceException(4824);
		}
		// 判断用户会员是否有效
		if (user.getVipExpireDate()==null||user.getVipExpireDate().getTime() < System.currentTimeMillis()) {
			throw new ServiceException(4825);
		}

		// 发送短信
		sendPhoneCode(sendCodeBO.getPhone(), sendCodeBO.getType());
	}

	@Override
	public void loginedVerifyCode(LoginedVerifyCodeBO verifyCodeBO) {
		LOGGER.info("用户通过用户ID校验手机验证码，参数：{}", verifyCodeBO.toString());
		User user = selectUser(verifyCodeBO.getUserId());
		if (user == null) {
			throw new ServiceException(4018);
		}
		if (StringUtils.isEmpty(user.getPhone())) {
			throw new ServiceException(4184);
		}

		VerifyingCodeBO codeBO = new VerifyingCodeBO();
		codeBO.setCode(verifyCodeBO.getCode());
		codeBO.setType(verifyCodeBO.getType());
		codeBO.setPhone(user.getPhone());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		boolean result = false;
		try {
			result = authService.verifyCode(codeBO, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!result) {
			throw new ServiceException(4201);
		}
	}

	@Override
	public void verifyOldPhone(oldPhoneBO oldPhone) {
		String userId = Utils.getUserId();
		User userNow = userMapper.selectOne(userId);

		LoginBO loginBO = new LoginBO();
		loginBO.setUsernameOrPhone(oldPhone.getOldPhone());
		User user = userMapper.selectByUsernameOrPhone(loginBO);
		if (user == null || !userNow.getPhone().equals(oldPhone.getOldPhone())) {
			LOGGER.warn("旧手机校验不通过：", oldPhone);
			throw new ServiceException(4826);
		}
	}

	@Override
	public IsRealNameBO isRealName() {
		IsRealNameBO isRealName = new IsRealNameBO();
		String userId = Utils.getUserId();
		UserExtend userExtend = userExtendMapper.isRealName(userId);
		if (userExtend != null
				&& userExtend.getValidStatus() != null
				&& userExtend.getValidStatus().equals(
						TaskConstant.USER_REALNAME_VALIDATED)) {
			isRealName.setIsRealName(true);
		} else {
			isRealName.setIsRealName(false);
		}
		return isRealName;
	}

	@Override
	public Map selectOneForAdmin(String userId) {
		LOGGER.info("{}", userId);
		User userTemp = selectUser(userId);
		UserExtend userExtend = userExtendMapper.selectOneForAdmin(userId);
		if (userTemp != null) {
			userTemp.setPassword(null);
			Map<String, Object> map = new HashMap<>();
			map.put("user", userTemp);
			map.put("user_extend", userExtend);
			LOGGER.info("{}：{}", userTemp, userExtend);
			return map;
		}
		return null;
	}

	// 获取总用户数
	@Override
	public int getAllNomalCont() {
		return userMapper.getAllNomalCont();
	}

	@Override
	public List<UserBO> getNomalList(Map<String, Object> map) {
		return userMapper.getNomalList(map);
	}

	@Override
	@Transactional("db1TxManager")
	public int changeWxBdxx(UserUpdateBO userUpdateDTO) {
		User users = new User();
		users.setId(userUpdateDTO.getId());
		users.setWxopenid(userUpdateDTO.getWxopenid());

		User user = userMapper.selectByWxUserId(users);
		if (user != null) {
			LOGGER.info("微信已绑定此账号");
			return 1;
		} else {
			LOGGER.info("微信绑定账号与此账号不符合，更新绑定关系");

			users.setWxheadimg(userUpdateDTO.getWxheadimg());
			users.setWxnickname(userUpdateDTO.getWxnickname());
			userMapper.qxwxbd(userUpdateDTO.getWxopenid());
			int n = userMapper.update(users);
			if (n >= 1) {
				LOGGER.info("用户关注公众号，做任务，USERID:" + userUpdateDTO.getId());
				todoTaskService.doTask(userUpdateDTO.getId(),
						TaskConstant.SYS_TASK_GZCSZJGZH_CODE);
				return 2;
			} else {
				throw new ServiceException(4624);
			}
		}

	}

	@Override
	public List<User> findByDzsbNsrsbh(String nsrsbh) {
		return userRoMapper.findByDzsbNsrsbh(nsrsbh);
	}

	@Override
	public UserBO selectOneByPhone(String phone) {
		return userMapper.selectOneByPhone(phone);
	}

	@Override
	public User selectUserById(User user) {
		return userMapper.selectUserById(user);
	}

	/**
	 * 调用message接口发送短信
	 *
	 * @param phone
	 *            手机号
	 * @param type
	 *            短信类型
	 */
	private void sendPhoneCode(String phone, String type) {
		// 不变参数
		String url = SpringCtxHolder.getProperty("abc12366.message.url")
				+ "/getcode";

		// 请求头设置
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(Constant.VERSION_HEAD,
				request.getHeader(Constant.VERSION_HEAD));
		httpHeaders.add(Constant.APP_TOKEN_HEAD,
				request.getHeader(Constant.APP_TOKEN_HEAD));

		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("phone", phone);
		requestBody.put("type", type);

		HttpEntity requestEntity = new HttpEntity(requestBody, httpHeaders);
		ResponseEntity responseEntity;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.POST,
					requestEntity, String.class);
		} catch (Exception e) {
			throw new ServiceException(4821);
		}

		if (responseEntity != null
				&& responseEntity.getStatusCode().is2xxSuccessful()
				&& responseEntity.hasBody()) {
			BaseObject object = JSON.parseObject(
					String.valueOf(responseEntity.getBody()), BaseObject.class);
			if (!object.getCode().equals("2000")) {
				throw new ServiceException(4204);
			}
		}
	}

	@Override
	public List<UserStatisBO> statisUserByMonth(Map<String, Object> map) {
		int day = 0;
		if (map.get("startTime") != null && map.get("endTime") != null) {
			day = DateUtils.differentDaysByMillisecond(
					(Date) map.get("startTime"), (Date) map.get("endTime"));
		}
		List<UserStatisBO> statisBOs = new ArrayList<>();
		List<UserStatisBO> userStatisBOList = new ArrayList<>();
		// 未超过30天则按天显示统计数，否则按月显示统计数
		if (day <= 31) {
			map.put("dateFormat", "%Y-%m-%d");
			statisBOs = userRoMapper.statisUserByDay(map);
			List<Date> datelist = DateUtils.findDates(
					(Date) map.get("startTime"), (Date) map.get("endTime"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Date date : datelist) {
				UserStatisBO bo = new UserStatisBO();
				bo.setCount(0);
				bo.setDays(sdf.format(date));
				for (UserStatisBO statisBO : statisBOs) {
					if (sdf.format(date).equals(statisBO.getDays())) {
						BeanUtils.copyProperties(statisBO, bo);
					}
				}
				userStatisBOList.add(bo);
			}
		} else {
			map.put("dateFormat", "%Y-%m");
			statisBOs = userRoMapper.statisUserByDay(map);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date startTime = (Date) map.get("startTime");
			Date endTime = (Date) map.get("endTime");
			List<Date> datelist = DateUtils.getMonthBetween(
					sdf.format(startTime), sdf.format(endTime));
			for (Date date : datelist) {
				UserStatisBO bo = new UserStatisBO();
				bo.setCount(0);
				bo.setDays(sdf.format(date));
				for (UserStatisBO statisBO : statisBOs) {
					if (sdf.format(date).equals(statisBO.getDays())) {
						BeanUtils.copyProperties(statisBO, bo);
					}
				}
				userStatisBOList.add(bo);
			}
		}
		return userStatisBOList;
	}

	@Override
	public UserLossRateBO statisUserLossRate(Map<String, Object> map) {
		UserLossRateBO userCount = userRoMapper.statisUserCount(map);
		UserLossRateBO lossUserCount = userRoMapper
				.statisUserLossRateCount(map);
		UserLossRateBO data = new UserLossRateBO();
		if (userCount != null && userCount.getUserCount() != null
				&& lossUserCount != null
				&& lossUserCount.getLossUserCount() != null) {
			int notUserCount = userCount.getUserCount()
					- lossUserCount.getLossUserCount();
			NumberFormat numberFormat = NumberFormat.getInstance();
			// 设置精确到小数点后2位
			numberFormat.setMaximumFractionDigits(2);
			String rate = numberFormat.format((float) notUserCount
					/ (float) userCount.getUserCount() * 100);
			data.setRate(rate);
			data.setUserCount(userCount.getUserCount());
			data.setLossUserCount(notUserCount);
		}
		return data;
	}

	@Override
	public UserLivenessSurveyBO userLivenessSurvey() {
		UserLivenessSurveyBO userLivenessSurveyBO = userRoMapper
				.userLivenessSurvey();
		if (userLivenessSurveyBO.getLastweek() != 0) {
			userLivenessSurveyBO
					.setLastweekDevidedbyLastweek(new DecimalFormat("#.##")
							.format(userLivenessSurveyBO.getYesterday()
									/ userLivenessSurveyBO.getLastweek() * 100)
							+ "%");
		} else {
			userLivenessSurveyBO.setLastweekDevidedbyLastweek("/");
		}
		if (userLivenessSurveyBO.getLast30Days() != 0) {
			userLivenessSurveyBO
					.setLast30DaysDevidedbyYesterday(new DecimalFormat("#.##")
							.format(userLivenessSurveyBO.getYesterday()
									/ userLivenessSurveyBO.getLast30Days()
									* 100)
							+ "%");
		} else {
			userLivenessSurveyBO.setLast30DaysDevidedbyYesterday("/");
		}
		return userLivenessSurveyBO;
	}

	@Override
	public Object userLivenessDetail(String type, String startStr, String endStr) {
		if (StringUtils.isEmpty(type) || StringUtils.isEmpty(startStr)
				|| StringUtils.isEmpty(endStr)) {
			return null;
		}
		if (!type.equals("year") && !type.trim().equals("month")
				&& !type.trim().equals("day")) {
			return null;
		}
		List<UserLivenessDetailBO> list = new ArrayList<>();

		if (type.trim().equals("year")) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(DateUtils.strToDate(startStr, "yyyy"));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(DateUtils.strToDate(endStr, "yyyy"));
			c2.add(Calendar.YEAR,1);
			Calendar c3 = Calendar.getInstance();
			c3.setTime(DateUtils.strToDate(startStr, "yyyy"));
			c3.add(Calendar.YEAR, 1);
			int minusYear = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
			for (int i = 0; i < minusYear; i++) {
				UserLivenessDetailBO userLivenessDetailBO = userRoMapper
						.userLivenessDetail(c1.getTime(), c3.getTime());
				userLivenessDetailBO.setDate(DateUtils.dateToString(
						c1.getTime(), "yyyy")
						+ "～" + DateUtils.dateToString(c3.getTime(), "yyyy"));
				if (userLivenessDetailBO.getAllRegister() > 0) {
					userLivenessDetailBO.setLiveUserPercent(new DecimalFormat(
							"#.##").format(userLivenessDetailBO.getLiveUsers()
							/ userLivenessDetailBO.getAllRegister() * 100)
							+ "%");
				} else {
					userLivenessDetailBO.setLiveUserPercent(0 + "");
				}
				list.add(userLivenessDetailBO);
				c1.add(Calendar.YEAR, 1);
				c3.add(Calendar.YEAR, 1);
			}
		}
		if (type.trim().equals("month")) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(DateUtils.strToDate(startStr, "yyyy-MM"));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(DateUtils.strToDate(endStr, "yyyy-MM"));
			c2.add(Calendar.MONTH,1);
			int minusYear = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
			int minus = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + 12
					* minusYear;
			Calendar c3 = Calendar.getInstance();
			c3.setTime(DateUtils.strToDate(startStr, "yyyy-MM"));
			c3.add(Calendar.MONTH, 1);
			for (int i = 0; i < minus; i++) {
				UserLivenessDetailBO userLivenessDetailBO = userRoMapper
						.userLivenessDetail(c1.getTime(), c3.getTime());
				userLivenessDetailBO
						.setDate(DateUtils.dateToString(c1.getTime(), "yyyy-MM")
								+ "～"
								+ DateUtils.dateToString(c3.getTime(),
										"yyyy-MM"));
				if (userLivenessDetailBO.getAllRegister() > 0) {
					userLivenessDetailBO.setLiveUserPercent(new DecimalFormat(
							"#.##").format(userLivenessDetailBO.getLiveUsers()
							/ userLivenessDetailBO.getAllRegister() * 100)
							+ "%");
				} else {
					userLivenessDetailBO.setLiveUserPercent(0 + "");
				}
				list.add(userLivenessDetailBO);
				c1.add(Calendar.MONTH, 1);
				c3.add(Calendar.MONTH, 1);
			}
		}

		if (type.trim().equals("day")) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(DateUtils.strToDate(startStr, "yyyy-MM-dd"));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(DateUtils.strToDate(endStr, "yyyy-MM-dd"));
			c2.add(Calendar.DAY_OF_YEAR,1);
			Calendar c3 = Calendar.getInstance();
			c3.setTime(DateUtils.strToDate(startStr, "yyyy-MM-dd"));
			c3.add(Calendar.DAY_OF_YEAR, 1);
			long minus = (c2.getTime().getTime() - c1.getTime().getTime())
					/ (24 * 60 * 60 * 1000);
			for (int i = 0; i < minus; i++) {
				UserLivenessDetailBO userLivenessDetailBO = userRoMapper
						.userLivenessDetail(c1.getTime(), c3.getTime());
				userLivenessDetailBO.setDate(DateUtils.dateToString(
						c1.getTime(), "yyyy-MM-dd")
						+ "～"
						+ DateUtils.dateToString(c3.getTime(), "yyyy-MM-dd"));
				if (userLivenessDetailBO.getAllRegister() > 0) {
					userLivenessDetailBO.setLiveUserPercent(new DecimalFormat(
							"#.##").format(userLivenessDetailBO.getLiveUsers()
							/ userLivenessDetailBO.getAllRegister() * 100)
							+ "%");
				} else {
					userLivenessDetailBO.setLiveUserPercent(0 + "");
				}
				list.add(userLivenessDetailBO);
				c1.add(Calendar.DAY_OF_YEAR, 1);
				c3.add(Calendar.DAY_OF_YEAR, 1);
			}
		}
		return list;
	}

    @Override
    public List<ExpLevelStatistic> userExpLevel(String year, int page, int size) {
        Date start = DateUtils.strToDate(year, "yyyy");
        Date end = DateUtils.strToDate(Integer.parseInt(year) + 1 + "", "yyyy");
        Date last = DateUtils.strToDate(Integer.parseInt(year) - 1 + "", "yyyy");
        Map<String, Object> map = new HashMap<>();
        map.put("last", last);
        map.put("start", start);
        map.put("end", end);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ExpLevelStatistic> experienceLevelBOList = experienceLevelRoMapper
                .selectLevelList(map);
        PageInfo<ExpLevelStatistic> pageInfo = new PageInfo<>(experienceLevelBOList);

        List<ExpLevelStatistic> expLevelStatisticList = new ArrayList<>();

        int threadNum = (int) pageInfo.getTotal();
        List<Future<ExpLevelStatistic>> futureList = new ArrayList<>();

        LOGGER.info("开始创建线程池.......");
        long time = System.currentTimeMillis();

        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(threadNum), new ThreadFactory() {

            @Override
            public Thread newThread(Runnable runnable) {
                long ll = System.currentTimeMillis();
                Thread t = new Thread(runnable, "ReportDateThread_" + ll);
//                LOGGER.info("启用线程数{}"+"ReportDateThread_"+ll);
                return t;
            }
        });

        for (ExpLevelStatistic experienceLevelBO : pageInfo.getList()) {
            Future<ExpLevelStatistic> future = executorService.submit(new UserLevelThread(year,experienceLevelBO));
            futureList.add(future);
        }

        // 记录线程结果
        for (int i = 0; i < futureList.size(); i++) {
            try {
                Future<ExpLevelStatistic> future = futureList.get(i);
                ExpLevelStatistic result = future.get();
                if(result != null){
                    expLevelStatisticList.add(result);
                }
            } catch (Exception e) {
                LOGGER.error("线程结果记录异常:", e);
            }
        }
        long time2 = System.currentTimeMillis();
        LOGGER.info("线程池处理完毕：" +  ",耗时：" + (time2 - time));

        // 关闭线程池
        executorService.shutdown();

        return expLevelStatisticList;
    }


    public class UserLevelThread implements Callable<ExpLevelStatistic> {
        private String year;
        private ExpLevelStatistic experienceLevelBO;

        public UserLevelThread(String year,ExpLevelStatistic experienceLevelBO) {
            this.year = year;
            this.experienceLevelBO = experienceLevelBO;
        }

        @Override
        public ExpLevelStatistic call() {

            Date start = DateUtils.strToDate(year, "yyyy");
            Date end = DateUtils.strToDate(Integer.parseInt(year) + 1 + "", "yyyy");
            Date last = DateUtils.strToDate(Integer.parseInt(year) - 1 + "", "yyyy");
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("start", start);
            hashMap.put("end", end);
            hashMap.put("min", experienceLevelBO.getMinValue());
            hashMap.put("max", experienceLevelBO.getMaxValue());
            LOGGER.info("执行SQL时间.......");
            long time = System.currentTimeMillis();
            float thisYearIncrease = experienceLogService.selectCount(hashMap);
            long time3 = System.currentTimeMillis();
            LOGGER.info("执行SQL时间：" + ",耗时：" + (time3 - time)+"; min = "+experienceLevelBO.getMinValue()+"; max = "+experienceLevelBO.getMaxValue());

            Map<String, Object> lastYeaMap = new HashMap<>();
            lastYeaMap.put("start", last);
            lastYeaMap.put("end", start);
            lastYeaMap.put("min",experienceLevelBO.getMinValue());
            lastYeaMap.put("max", experienceLevelBO.getMaxValue());
            float lastYearAll = experienceLogService.selectCount(lastYeaMap);

            //float all = userRoMapper.selectExpCount(map);

            ExpLevelStatistic expLevelStatistic = new ExpLevelStatistic();
            expLevelStatistic.setAll(experienceLevelBO.getAll());
            expLevelStatistic.setThisYearIncrease(thisYearIncrease);
            expLevelStatistic.setLastYearAll(lastYearAll);
            expLevelStatistic.setLevelCode(experienceLevelBO.getLevelCode());
            expLevelStatistic.setLevelName(experienceLevelBO.getLevelName());
            expLevelStatistic.setIncreasePercent(lastYearAll == 0 ? "/"
                    : new DecimalFormat("#.##").format(thisYearIncrease
                    / lastYearAll * 100)
                    + "%");
//            expLevelStatisticList.add(expLevelStatistic);
            long time2 = System.currentTimeMillis();
            LOGGER.info("单个处理时间：" +  ",耗时：" + (time2 - time)+"; min = "+experienceLevelBO.getMinValue()+"; max = "+experienceLevelBO.getMaxValue());
            return expLevelStatistic;
        }
    }



	@Override
	public List<VipLevelStatistic> userVip(String year) {
		List<VipLevelBO> vipLevelBOList = vipLevelService
				.selectList(new HashMap<>());
		Date start = DateUtils.strToDate(year, "yyyy");
		Date end = DateUtils.strToDate(Integer.parseInt(year) + 1 + "", "yyyy");
		Date lastStart = DateUtils.strToDate(Integer.parseInt(year) - 1 + "",
				"yyyy");
		List<VipLevelStatistic> vipLevelStatistics = new ArrayList<>();
		for (int i = 0; i < vipLevelBOList.size(); i++) {
			VipLevelBO vipLevelBO = vipLevelBOList.get(i);
			if (vipLevelBO.getLevelCode().equals("VIP0")) {
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("levelCode", vipLevelBO.getLevelCode());
			map.put("start", start);
			map.put("end", end);
			map.put("lastStart", lastStart);
			map.put("lastEnd", start);
			VipLevelStatisticTemp vipLevelStatistic = vipLogService
					.selectCountByCode(map);

			// int all =
			// vipLogService.selectCountAll(vipLevelBO.getLevelCode());
			VipLevelStatistic levelStatistic = new VipLevelStatistic();
			levelStatistic.setLevelCode(vipLevelBO.getLevelCode());
			levelStatistic.setLevelName(vipLevelBO.getLevel());
			levelStatistic.setAll(vipLevelStatistic.getAllCount());
			levelStatistic.setIncrease((int) vipLevelStatistic.getIncrease());
			levelStatistic.setLastYearAll((int) vipLevelStatistic
					.getLastYearAll());
			if (vipLevelStatistic.getLastYearAll() == 0) {
				levelStatistic.setIncreasePercent("/");
			} else {
				levelStatistic.setIncreasePercent(new DecimalFormat("#.##")
						.format(vipLevelStatistic.getIncrease()
								/ vipLevelStatistic.getLastYearAll() * 100F)
						+ "%");
			}
			vipLevelStatistics.add(levelStatistic);
		}
		return vipLevelStatistics;
	}

	@Override
	public List<UserRetainedRateListBO> statisUserRetainedRate(
			Map<String, Object> map) {
		String number = "0,1,2,3,4,6,12,";
		map.put("number", number);
		// 获取起止时间的月份数组
		List<Date> dates = DateUtils.getMonthBetween(
				(String) map.get("startTime"), (String) map.get("endTime"));
		if (dates != null && dates.size() > 12) {
			LOGGER.info("起止时间不能超过12个月:" + dates);
			throw new ServiceException(4926, "起止时间不能超过12个月");
		}
		Map<String, Object> inMap = new HashMap<>();
		List<UserRetainedRateBO> bos;
		List<UserRetainedRateListBO> listBOs = new ArrayList<>();
		for (Date date : dates) {
			UserRetainedRateListBO userRetainedRateListBO = new UserRetainedRateListBO();
			bos = new ArrayList<>();
			inMap.put("startTime", date);
			inMap.put("number", number);
			bos = userMapper.statisUserRetainedRate(inMap);
			userRetainedRateListBO.setDate(date);
			userRetainedRateListBO.setUserRetainedRateBOList(bos);
			listBOs.add(userRetainedRateListBO);
		}
		return listBOs;
	}

	@Override
	public List<UserExprotInfoBO> statisUserConsumeLevel(Map<String, Object> map) {
		return userRoMapper.statisUserConsumeLevel(map);
	}

	@Override
	public UserRFMBO statisUserRFM(Map<String, Object> map) {
		return userRoMapper.statisUserRFM(map);
	}

	@Override
	public List<UserListBO> userLivenessDetailUinfo(String timeInterval,
			int page, int size) {
		String[] str = timeInterval.split("～");
		if (str.length != 2 || str[0].length() != str[1].length()) {
			throw new ServiceException(4806);
		}
		Date start = null;
		Date end = null;
		switch (str[0].length()) {
		case 4:
			start = DateUtils.strToDate(str[0], "yyyy");
			end = DateUtils.strToDate(str[1], "yyyy");
			break;
		case 7:
			start = DateUtils.strToDate(str[0], "yyyy-MM");
			end = DateUtils.strToDate(str[1], "yyyy-MM");
			break;
		case 10:
			start = DateUtils.strToDate(str[0], "yyyy-MM-dd");
			end = DateUtils.strToDate(str[1], "yyyy-MM-dd");
			break;
		}

		Map<String, Date> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return userRoMapper.userLivenessDetailUinfo(map);
	}

	@Override
	public List<UserAgeBO> statisUserAge(Map<String, Object> map) {
		// 0-25 、26-30 、31-35 、36-40 、41-45 、46-50 、51-55 、56-60、61-70、70以上 、未知
		int[][] ages = { { 0, 25 }, { 26, 30 }, { 31, 35 }, { 36, 40 },
				{ 41, 45 }, { 46, 50 }, { 51, 55 }, { 56, 60 }, { 61, 70 } };

		List<UserAgeBO> list = new ArrayList<>();
		UserAgeBO userAgeBO;
		int count = 0;
		int total = 0;
		for (int[] row : ages) {
			userAgeBO = new UserAgeBO();
			LOGGER.info("时间段：{},{}", row[0], row[1]);
			map.put("startAge", row[0]);
			map.put("endAge", row[1]);
			userAgeBO.setAgeGroup(row[0] + "-" + row[1]);
			count = userRoMapper.statisUserAge(map);
			userAgeBO.setCount(count);
			list.add(userAgeBO);
			total = total + count;
		}
		map.put("startAge", 70);
		map.put("endAge", null);
		userAgeBO = new UserAgeBO();
		userAgeBO.setAgeGroup("70以上");
		userAgeBO.setCount(userRoMapper.statisUserAge(map));
		list.add(userAgeBO);

		// 查询用户显示
		userAgeBO = new UserAgeBO();
		userAgeBO.setAgeGroup("未知");
		userAgeBO.setCount(userRoMapper.statisUserUnknownAge(map));
		list.add(userAgeBO);
		return list;
	}

	@Override
	public List<UserBO> statisUserAgeList(Map<String, Object> map,
			Integer startAge, Integer endAge) {
		if (startAge != null) {
			map.put("startAge", startAge);
		}
		if (endAge != null) {
			map.put("endAge", endAge);
		}
		// startNum和endNum为null 查询未知年龄用户
		if (startAge == null && endAge == null) {
			return userRoMapper.statisUserUnknownAgeList(map);
		} else {
			return userRoMapper.statisUserAgeList(map);
		}
	}

	@Override
	public List<UserBO> statisUserSexList(Map<String, Object> map) {
		return userRoMapper.statisUserSexList(map);
	}

	@Override
	public List<UserSexBO> statisUserSex(Map<String, Object> map) {
		return userRoMapper.statisUserSex(map);
	}

	@Override
	public UserBindBO statisUserBind(Map<String, Object> map) {
		return userRoMapper.statisUserBind(map);
	}

	@Override
	public List<UserBO> statisUserBindList(Map<String, Object> map) {
		return userRoMapper.statisUserBindList(map);
	}
	@Override
	public void resetPassword(UserResetPwdBO resetPwdBO) {
		User u = userMapper.selectOne(resetPwdBO.getUserId());
		if (u == null) {
			throw new ServiceException(4018);
		}

		if(StringUtils.isEmpty(u.getSalt())){
			throw new ServiceException(4205);
		}

		User newUser = new User();
		newUser.setId(resetPwdBO.getUserId());
		newUser.setLastUpdate(new Date());
		String defaultPwd;
		try {
			defaultPwd = Utils.md5(Utils.md5(Constant.USER_DEFAULT_PASSWORD) + u.getSalt());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(4106);
		}
		newUser.setPassword(defaultPwd);
		int result = userMapper.updatePassword(newUser);
		if (result < 1) {
			throw new ServiceException(4103);
		}
		userMapper.deleteContinuePwdWrong(resetPwdBO.getUserId());
		try {
			adminOperationService.insert(new AdminModifyUserPhoneLogBO(resetPwdBO.getUserId(), Utils.getAdminId(), null, null, resetPwdBO.getReason(), null, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserJyxx selectByUnameOrPhone(String usernameOrPhone) {
		return userMapper.selectByUnameOrPhone(usernameOrPhone);
	}

	@Override
	public List<UserSubscriptionInfo> selectUserSubscriptionList(Map<String, Object> param) {
		return userMapper.selectUserSubscriptionList(param);
	}
}
