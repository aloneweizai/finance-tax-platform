package com.abc12366.uc.service.impl;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-18
 */

import com.abc12366.uc.mapper.db1.LotteryLogMapper;
import com.abc12366.uc.mapper.db2.LotteryLogRoMapper;
import com.abc12366.uc.model.LotteryLog;
import com.abc12366.uc.model.bo.LotteryLogBO;
import com.abc12366.uc.service.LotteryLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LotteryLogServiceImpl implements LotteryLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryLogServiceImpl.class);
    @Autowired
    private LotteryLogMapper lotteryLogMapper;
    @Autowired
    private LotteryLogRoMapper lotteryLogRoMapper;

    @Override
    public LotteryLogBO update(LotteryLogBO lotteryLogBO, String id) {
        LotteryLog obj = new LotteryLog();
        BeanUtils.copyProperties(lotteryLogBO, obj);
        obj.setId(id);
        obj.setCreateTime(null);
        int result = lotteryLogMapper.update(obj);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + obj);
            throw new RuntimeException("seviceErr:修改失败");
        }
        LotteryLogBO returnObj = new LotteryLogBO();
        BeanUtils.copyProperties(obj, returnObj);
        return returnObj;
    }

    @Override
    public boolean delete(String id) {
        Integer result = lotteryLogMapper.delete(id);
        return (result == 1);
    }

    @Override
    public LotteryLogBO insert(LotteryLogBO lotteryLogBO) {
        LotteryLog obj = new LotteryLog();
        BeanUtils.copyProperties(lotteryLogBO, obj);
        Date date = new Date();
        obj.setId(java.util.UUID.randomUUID().toString());
        obj.setCreateTime(date);

        int result = lotteryLogMapper.insert(obj);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + obj);
            throw new RuntimeException("seviceErr:新增失败");
        }
        LotteryLogBO returnObj = new LotteryLogBO();
        BeanUtils.copyProperties(obj, returnObj);
        return returnObj;
    }

    @Override
    public LotteryLogBO selectOne(String id) {
        return lotteryLogRoMapper.selectOne(id);
    }

    @Override
    public    Integer selectUserDayLuck(String userId){
        return lotteryLogRoMapper.selectUserDayLuck(userId);
    }
    @Override
    public    Integer selectUserDay(String userId){
        return lotteryLogRoMapper.selectUserDay(userId);
    }
    @Override
    public List<LotteryLogBO> selectList(Map map) {
        return lotteryLogRoMapper.selectList(map);
    }
}
