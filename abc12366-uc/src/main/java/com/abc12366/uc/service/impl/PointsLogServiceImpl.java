package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.PointsLogMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.PointsLogRoMapper;
import com.abc12366.uc.mapper.db2.UserRoMapper;
import com.abc12366.uc.model.PointsLog;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsLogUcBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.service.PointsLogService;
import com.abc12366.uc.service.VipPrivilegeLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:22
 */
@Service
public class PointsLogServiceImpl implements PointsLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PointsLogServiceImpl.class);

    @Autowired
    private PointsLogMapper pointsLogMapper;

    @Autowired
    private PointsLogRoMapper pointsLogRoMapper;

    @Autowired
    private UserRoMapper userRoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VipPrivilegeLevelService vipPrivilegeLevelService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<PointsLogBO> selectList(Map map) {
        return pointsLogRoMapper.selectList(map);
    }

    //@Transactional("db1TxManager")
    @Override
    public PointsLogBO insert(PointsLogBO pointsLogBO) {
    	
    	//2018-02-28
    	//redis经验值删除
        redisTemplate.delete(pointsLogBO.getUserId() + "_Points");

        LOGGER.info("计算用户积分：{}",pointsLogBO);
        
        User user = userRoMapper.selectPointsAndExp(pointsLogBO.getUserId());
        if (user == null) {
            throw new ServiceException(4018);
        }
        LOGGER.info("当前用户信息：{}",user);
        //可用积分=上一次的可用积分+|-本次收入|支出
        int priPoints = 0;
        if (user.getPoints() != null) {
            priPoints = user.getPoints();
        }
        LOGGER.info("用户当前可用积分：{}",priPoints);
        int usablePoints = priPoints + pointsLogBO.getIncome() - pointsLogBO.getOutgo();
        if (usablePoints < 0) {
            throw new ServiceException(4635);
        }
        LOGGER.info("用户积分计算后可用积分：{}",usablePoints);
        //uc_user的points字段和uc_point_log的usablePoints字段都要更新
        user.setPoints(usablePoints);
        user.setLastUpdate(new Date());
        int userUpdateResult = userMapper.updatePoints(user);
        if (userUpdateResult != 1) {
            LOGGER.warn("新增失败,更新用户表积分失败,参数为：userId=" + pointsLogBO.getUserId());
            throw new ServiceException(4101);
        }

        PointsLog pointsLog = new PointsLog();
        BeanUtils.copyProperties(pointsLogBO, pointsLog);
        pointsLog.setId(Utils.uuid());
        pointsLog.setCreateTime(new Date());
        pointsLog.setUsablePoints(usablePoints);
        int result = pointsLogMapper.insert(pointsLog);
        if (result < 1) {
            LOGGER.warn("新增失败，参数：{}", pointsLog.toString());
            throw new ServiceException(4101);
        }

        PointsLogBO pointsLogBOReturn = new PointsLogBO();
        BeanUtils.copyProperties(pointsLog, pointsLogBOReturn);
        return pointsLogBOReturn;
        //执行存储过程做计算
//        PointsLogBO pointsLogBO1 = pointsLogMapper.updatePointAndLog(pointsLogBO.getUserId(),pointsLogBO.getIncome()-pointsLogBO.getOutgo(),pointsLogBO.getRuleId());
//        LOGGER.info("计算用户积分结果：{}",pointsLogBO1);
//        return pointsLogBO1;
    }

    @Override
    public List<PointsLogUcBO> selectListByUser(Map<String, Object> map) {
        return pointsLogRoMapper.selectListByUser(map);
    }

    @Transactional("db1TxManager")
    @Override
    public PointsLogBO insertNoVip(PointsLogBO pointsLogBO) {
        if (pointsLogBO == null) {
            LOGGER.warn("新增失败，参数：{}" + null);
            throw new ServiceException(4101);
        }
        User user = userMapper.selectOne(pointsLogBO.getUserId());
        if (user == null) {
            throw new ServiceException(4018);
        }

        //可用积分=上一次的可用积分+|-本次收入|支出
        int priPoints = 0;
        if (user.getPoints() != null) {
            priPoints = user.getPoints();
        }
        int usablePoints = priPoints + pointsLogBO.getIncome() - pointsLogBO.getOutgo();
        if (usablePoints < 0) {
            throw new ServiceException(4635);
        }

        //uc_user的points字段和uc_point_log的usablePoints字段都要更新
        user.setPoints(usablePoints);
        user.setLastUpdate(new Date());
        int userUpdateResult = userMapper.updatePoints(user);
        if (userUpdateResult != 1) {
            LOGGER.warn("新增失败,更新用户表积分失败,参数为：userId=" + pointsLogBO.getUserId());
            throw new ServiceException(4101);
        }

        PointsLog pointsLog = new PointsLog();
        BeanUtils.copyProperties(pointsLogBO, pointsLog);
        pointsLog.setId(Utils.uuid());
        pointsLog.setCreateTime(new Date());
        pointsLog.setUsablePoints(usablePoints);
        int result = pointsLogMapper.insert(pointsLog);
        if (result < 1) {
            LOGGER.warn("新增失败，参数：{}", pointsLog.toString());
            throw new ServiceException(4101);
        }

        PointsLogBO pointsLogBOReturn = new PointsLogBO();
        BeanUtils.copyProperties(pointsLog, pointsLogBOReturn);
        return pointsLogBOReturn;
    }

    @Override
    public PointsLogBO insertByConsume(PointsLogBO pointsLogBO,String vipLevel) {
        if (pointsLogBO == null || StringUtils.isEmpty(pointsLogBO.getUserId())) {
            LOGGER.error("积分日志参数不正确：{}",pointsLogBO);
            return null;
        }
        /*User user = userMapper.selectOne(pointsLogBO.getUserId());
        if (user == null || StringUtils.isEmpty(user.getVipLevel())) {
            LOGGER.error("不存在这个用户：{}", pointsLogBO.getUserId());
            return null;
        }*/
        //会员权限埋点（产品消费获得积分加成）
        if (!StringUtils.isEmpty(vipLevel)) {
            VipPrivilegeLevelBO vipPrivilegeLevelBOPar = new VipPrivilegeLevelBO();
            vipPrivilegeLevelBOPar.setLevelId(vipLevel);
            vipPrivilegeLevelBOPar.setPrivilegeId("A_XFJFJC");
            VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelService.selectLevelIdPrivilegeId(vipPrivilegeLevelBOPar);
            if (vipPrivilegeLevelBO != null && vipPrivilegeLevelBO.getStatus()) {
                if (!StringUtils.isEmpty(vipPrivilegeLevelBO.getVal1())) {
                    LOGGER.info("会员产品消费获得积分加成：{}", vipPrivilegeLevelBO.getVal1()+"倍");
                    pointsLogBO.setIncome((int) ((pointsLogBO.getIncome()) * Float.parseFloat(vipPrivilegeLevelBO.getVal1())));
                    pointsLogBO.setOutgo((int) ((pointsLogBO.getOutgo()) * Float.parseFloat(vipPrivilegeLevelBO.getVal1())));
                }
            }
        }
        insert(pointsLogBO);
        return pointsLogBO;
    }

	@Override
	public int selecttimes(Map map) {
		return pointsLogRoMapper.selecttimes(map);
	}
}
