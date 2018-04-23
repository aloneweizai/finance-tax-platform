package com.abc12366.uc.service.impl;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */

import com.abc12366.uc.mapper.db1.LotteryActivityprizeMapper;
import com.abc12366.uc.mapper.db2.LotteryActivityprizeRoMapper;
import com.abc12366.uc.model.LotteryActivityprize;
import com.abc12366.uc.model.bo.LotteryActivityprizeBO;
import com.abc12366.uc.service.LotteryActivityprizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LotteryActivityprizeServiceImpl implements LotteryActivityprizeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryActivityprizeServiceImpl.class);
    @Autowired
    private LotteryActivityprizeMapper lotteryActivityprizeMapper;
    @Autowired
    private LotteryActivityprizeRoMapper lotteryActivityprizeRoMapper;

    @Override
    public LotteryActivityprizeBO update(LotteryActivityprizeBO lotteryActivityprizeBO, String id) {
        LotteryActivityprize obj = new LotteryActivityprize();
        BeanUtils.copyProperties(lotteryActivityprizeBO, obj);
        obj.setId(id);
        obj.setCreateTime(null);
        obj.setLastTime(new Date());
        int result = lotteryActivityprizeMapper.update(obj);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + obj);
            throw new RuntimeException("seviceErr:修改失败");
        }
        LotteryActivityprizeBO returnObj = new LotteryActivityprizeBO();
        BeanUtils.copyProperties(obj, returnObj);
        return returnObj;
    }

    @Override
    public boolean delete(String id) {
        Integer result = lotteryActivityprizeMapper.delete(id);
        return (result == 1);
    }

    @Override
    public LotteryActivityprizeBO insert(LotteryActivityprizeBO lotteryActivityprizeBO) {
        LotteryActivityprize obj = new LotteryActivityprize();
        BeanUtils.copyProperties(lotteryActivityprizeBO, obj);
        Date date = new Date();
        obj.setId(java.util.UUID.randomUUID().toString());
        obj.setCreateTime(date);
        obj.setLastTime(date);
        int result = lotteryActivityprizeMapper.insert(obj);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + obj);
            throw new RuntimeException("seviceErr:新增失败");
        }
        LotteryActivityprizeBO returnObj = new LotteryActivityprizeBO();
        BeanUtils.copyProperties(obj, returnObj);
        return returnObj;
    }

    @Override
    public LotteryActivityprizeBO selectOne(String id) {
        return lotteryActivityprizeRoMapper.selectOne(id);
    }

    @Override
    public List<LotteryActivityprizeBO> selectList(Map map) {
        return lotteryActivityprizeRoMapper.selectList(map);
    }
}
