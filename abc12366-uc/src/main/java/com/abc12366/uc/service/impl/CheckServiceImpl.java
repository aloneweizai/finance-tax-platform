package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.CheckMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.CheckRoMapper;
import com.abc12366.uc.model.Check;
import com.abc12366.uc.model.CheckRank;
import com.abc12366.uc.model.ReCheck;
import com.abc12366.uc.model.TodoTask;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * User: liuguiyao<435720953@qq.com> Date: 2017-08-21 Time: 14:28
 */
@Service
public class CheckServiceImpl implements CheckService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckServiceImpl.class);

	@Autowired
	private CheckMapper checkMapper;

	@Autowired
	private CheckRoMapper checkRoMapper;

	@Autowired
	private PointsLogService pointsLogService;

	@Autowired
	private TodoTaskService todoTaskService;

	@Autowired
	private PointsRuleService pointsRuleService;

	@Autowired
	private VipPrivilegeLevelService vipPrivilegeLevelService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SysTaskService sysTaskService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Transactional("db1TxManager")
	@Override
	public int check(Check check) {
		User user = null;
		if (!StringUtils.isEmpty(check.getUserId())) {
			user = userMapper.selectOne(check.getUserId());
		}
		if (user == null) {
			return 0;
		}

		// 2018-02-28 签到操作之前判断任务是否已经生成
		SysTaskBO sysTaskBO = sysTaskService.selectValidOneByCode(TaskConstant.SYS_TASK_CHECK_CODE);
		if (sysTaskBO == null) {
			throw new ServiceException(9999, "系统正在生成每日任务，请稍后进行签到操作");
		}
		TodoTask todoTask = todoTaskService.selectOne(user.getId(), sysTaskBO.getId());
		if (todoTask == null) {
			throw new ServiceException(9999, "系统正在生成每日任务，请稍后进行签到操作");
		}

		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		check.setOrderby(String.valueOf(day));
		check.setCheckDate(DateUtils.strToDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));

		// 判断当天是否已经签到过
		if (isExist(check)) {
			throw new ServiceException(4850);
		}

		int points = 5;

		// 是否连续签到
		if (isContinueCheck(check.getUserId(), day - 1)) {
			// 是否两天连续签到，获得10积分
			points = 10;
			// 是否三天连续签到，获得15积分
			if (isContinueCheck(check.getUserId(), day - 2)) {
				points = 15;
				// 是否连续四天或以上签到，获得20积分
				if (isContinueCheck(check.getUserId(), day - 3)) {
					points = 20;
				}
			}
		}
		// 记录签到
		insert(check);

		// 完成任务埋点,如果任务不存在或失效则返回
		if (!todoTaskService.doTaskWithouComputeAward(check.getUserId(), TaskConstant.SYS_TASK_CHECK_CODE)) {
			return 0;
		}

		// 签到积分加成
		if (!StringUtils.isEmpty(user.getVipLevel())) {
			VipPrivilegeLevelBO vipPrivilegeLevelBOPar = new VipPrivilegeLevelBO();
			vipPrivilegeLevelBOPar.setLevelId(user.getVipLevel());
			vipPrivilegeLevelBOPar.setPrivilegeId("A_JDSJF");
			VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelService
					.selectLevelIdPrivilegeId(vipPrivilegeLevelBOPar);
			if (vipPrivilegeLevelBO != null) {
				if (!StringUtils.isEmpty(vipPrivilegeLevelBO.getVal1())) {
					LOGGER.info("签到赠送积分加成：{}", vipPrivilegeLevelBO.getVal1() + "倍");
					points = (int) (points * Float.parseFloat(vipPrivilegeLevelBO.getVal1()));
				}
			}
		}

		// 记日志,如果规则失效则返回
		if (!pointsLog(check.getUserId(), points)) {
			return 0;
		}

		// 删除redis用户信息
		redisTemplate.delete(check.getUserId() + "_Check");

		return points;
	}

	@Transactional("db1TxManager")
	@Override
	public void reCheck(ReCheck recheck) {
		Date date = DateUtils.strToDate(recheck.getDate());
		// 每天只能补签三次
		Check checkTmp = new Check();
		checkTmp.setUserId(recheck.getUserId());
		checkTmp.setCheckDate(date);
		List<Check> checkList = checkRoMapper.selectRecheck(checkTmp);
		if (checkList != null && checkList.size() >= 3) {
			throw new ServiceException(4851);
		}

		if (isRecheckExist(checkTmp)) {
			throw new ServiceException(4852);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Check check = new Check();
		check.setId(Utils.uuid());
		check.setUserId(recheck.getUserId());
		check.setCreateTime(new Date());
		check.setCheckDate(date);
		check.setOrderby(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
		check.setIsReCheck(true);

		recheckInsert(check);
		// 记日志
		recheckPointsLog(recheck.getUserId());

		// 删除redis用户信息
		redisTemplate.delete(check.getUserId() + "_Check");
	}

	@Override
	public List<CheckRank> rank(String yearTemp) {
		Map<String, Object> map = new HashMap<>();
		Date startTime;
		Date endTime;
		if (yearTemp == null || yearTemp.trim().equals("")) {
			startTime = DateUtils.getFirstMonthOfYear();
			endTime = DateUtils.getFirstMonthOfLastYear();
		} else {
			Date date;
			try {
				date = new SimpleDateFormat("yyyy").parse(yearTemp);
			} catch (ParseException e) {
				e.printStackTrace();
				LOGGER.error("时间转换异常：{}", yearTemp);
				throw new ServiceException(4806);
			}
			Calendar end = Calendar.getInstance();
			end.setTime(date);
			end.add(Calendar.YEAR, 1);

			startTime = date;
			endTime = end.getTime();
		}

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return checkRoMapper.selectRankList(map);
	}

	@Override
	public List<CheckListBO> checklist(String yearMonth) {
		String userId = Utils.getUserId();

		String[] timeArray = yearMonth.trim().split("-");
		int year = Integer.parseInt(timeArray[0]);
		int month = Integer.parseInt(timeArray[1]);
		int days = getDaysByYearMonth(year, month);

		Date startDate = DateUtils.strToDate(yearMonth + "-01");
		Date endDate = DateUtils.strToDate(year + "-" + (month + 1) + "-01");
		CheckListParam checkListParam = new CheckListParam();
		checkListParam.setUserId(userId);
		checkListParam.setStartDate(startDate);
		checkListParam.setEndDate(endDate);
		List<Check> checkList = checkRoMapper.selectCheckList(checkListParam);

		List<CheckListBO> checkListBOs = new ArrayList<>();

		// 判断用户该月每天签到情况
		for (int i = 1; i <= days; i++) {
			CheckListBO checkListBO = new CheckListBO();
			String iExtend = String.valueOf(i);
			if (i < 10) {
				iExtend = "0" + i;
			}
			for (int j = 0; j < checkList.size(); j++) {
				Check check = checkList.get(j);
				Date checkDate = DateUtils.strToDate(yearMonth + "-" + iExtend);
				checkListBO.setDate(checkDate);
				if (check.getCheckDate().equals(checkDate)) {
					checkListBO.setIsCheck(true);
				}
			}
			checkListBOs.add(checkListBO);
		}
		return checkListBOs;
	}

	private boolean pointsLog(String userId, int points) {
		PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_CHECK_CODE);
		if (pointsRuleBO == null) {
			return false;
		}

		PointsLogBO pointsLog = new PointsLogBO();
		pointsLog.setId(Utils.uuid());
		pointsLog.setCreateTime(new Date());
		pointsLog.setRuleId(pointsRuleBO.getId());
		pointsLog.setUserId(userId);
		pointsLog.setIncome(points);
		pointsLog.setLogType("CHECK_IN");
		pointsLog.setRemark("用户签到获取积分");
		pointsLogService.insert(pointsLog);
		return true;
	}

	private boolean recheckPointsLog(String userId) {
		PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_RECHECK_CODE);
		LOGGER.info("用户补签积分规则：{}", pointsRuleBO);
		if (pointsRuleBO == null) {
			return false;
		}

		PointsLogBO pointsLog = new PointsLogBO();
		pointsLog.setId(Utils.uuid());
		pointsLog.setCreateTime(new Date());
		pointsLog.setUserId(userId);
		pointsLog.setOutgo(-pointsRuleBO.getPoints());
		pointsLog.setRuleId(pointsRuleBO.getId());
		pointsLog.setLogType("RE_CHECK_IN");
		pointsLog.setRemark("用户补签消耗" + -pointsRuleBO.getPoints() + "积分");
		pointsLogService.insert(pointsLog);
		return true;
	}

	private Check insert(Check c) {
		Check check = new Check();
		check.setUserId(c.getUserId());
		check.setId(Utils.uuid());
		Date date = new Date();
		check.setCreateTime(date);
		check.setCheckDate(date);
		check.setOrderby(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
		check.setIsReCheck(false);
		checkMapper.insert(check);
		return check;
	}

	private Check recheckInsert(Check check) {
		checkMapper.insert(check);
		return check;
	}

	private boolean isContinueCheck(String userId, int order) {
		Check check = new Check();
		check.setUserId(userId);
		check.setOrderby(String.valueOf(order));
		// 时间格式转换，为了数据库时间的比较
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), order);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(date);
		Date checkDate = DateUtils.strToDate(dateStr);
		check.setCheckDate(checkDate);
		return isExist(check);
	}

	public boolean isExist(Check check) {
		List<Check> checkList = checkRoMapper.selectByOrder(check);
		return (checkList != null && checkList.size() >= 1);
	}

	// public void continuingCheck(String userId) {
	// Map<String, String> map = new HashMap<>();
	// String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	// map.put("userId", userId);
	// map.put("year", year);
	// List<CheckRank> checkRankList = checkRoMapper.selectOneRank(map);
	// CheckRank checkRank = new CheckRank();
	// checkRank.setUserId(userId);
	// checkRank.setLastUpdate(new Date());
	// checkRank.setYear(year);
	// if (checkRankList == null || checkRankList.size() < 1) {
	// checkRank.setId(Utils.uuid());
	// checkRank.setCount(1);
	// checkMapper.insertRank(checkRank);
	// return;
	// }
	// CheckRank checkRankTmp = checkRankList.get(0);
	// checkRank.setCount(checkRankTmp.getCount() + 1);
	// checkMapper.updateRank(checkRank);
	// }

	// public void continuingCheck(String userId, Calendar calendar) {
	// Map<String, String> map = new HashMap<>();
	// String year = String.valueOf(calendar.get(Calendar.YEAR));
	// map.put("userId", userId);
	// map.put("year", year);
	// List<CheckRank> checkRankList = checkRoMapper.selectOneRank(map);
	// CheckRank checkRank = new CheckRank();
	// checkRank.setUserId(userId);
	// checkRank.setLastUpdate(new Date());
	// checkRank.setYear(year);
	// if (checkRankList == null || checkRankList.size() < 1) {
	// checkRank.setId(Utils.uuid());
	// checkRank.setCount(1);
	// checkMapper.insertRank(checkRank);
	// return;
	// }
	// CheckRank checkRankTmp = checkRankList.get(0);
	// checkRank.setCount(checkRankTmp.getCount() + 1);
	// checkMapper.updateRank(checkRank);
	// }

	public boolean isRecheckExist(Check check) {
		List<Check> checkList = checkRoMapper.selectIsRecheck(check);
		return !(checkList == null || checkList.size() <= 0);
	}

	public int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		return a.get(Calendar.DATE);
	}

	@Override
	public int checkTotal(String userId) {
		Integer total;
		if (redisTemplate.hasKey(userId + "_Check")) {
			total = Integer.parseInt(redisTemplate.opsForValue().get(userId + "_Check"));
		} else {
			total = checkRoMapper.checkTotal(userId);
			redisTemplate.opsForValue().set(userId + "_Check", String.valueOf(total), RedisConstant.DAY_1,
					TimeUnit.DAYS);
		}
		return total;
	}
}
