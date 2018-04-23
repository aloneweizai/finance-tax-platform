package com.abc12366.uc.service.impl;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-13
 */

import com.abc12366.uc.mapper.db1.LotteryTimeMapper;
import com.abc12366.uc.mapper.db2.LotteryTimeRoMapper;
import com.abc12366.uc.model.LotteryTime;
import com.abc12366.uc.model.bo.LotteryTimeBO;
import com.abc12366.uc.service.LotteryTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotteryTimeServiceImpl implements LotteryTimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryTimeServiceImpl.class);
    @Autowired
    private LotteryTimeMapper lotteryTimeMapper;
    @Autowired
    private LotteryTimeRoMapper lotteryTimeRoMapper;

    @Override
    public LotteryTimeBO update(LotteryTimeBO lotteryTimeBO, String id) {
        LotteryTime obj = new LotteryTime();
        BeanUtils.copyProperties(lotteryTimeBO, obj);
        obj.setId(id);
        obj.setCreateTime(null);
        obj.setLastUpdate(new Date());
        int result = lotteryTimeMapper.update(obj);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + obj);
            throw new RuntimeException("seviceErr:修改失败");
        }
        LotteryTimeBO returnObj = new LotteryTimeBO();
        BeanUtils.copyProperties(obj, returnObj);
        return returnObj;
    }

    @Override
    public boolean delete(String id) {
        Integer result = lotteryTimeMapper.delete(id);
        return (result == 1);
    }

    @Override
    public LotteryTimeBO insert(LotteryTimeBO lotteryTimeBO) {
        LotteryTime obj = new LotteryTime();
        BeanUtils.copyProperties(lotteryTimeBO, obj);
        Date date = new Date();
        obj.setId(java.util.UUID.randomUUID().toString());
        obj.setCreateTime(date);
        obj.setLastUpdate(date);
        int result = lotteryTimeMapper.insert(obj);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + obj);
            throw new RuntimeException("seviceErr:新增失败");
        }
        LotteryTimeBO returnObj = new LotteryTimeBO();
        BeanUtils.copyProperties(obj, returnObj);
        return returnObj;
    }

    @Override
    public LotteryTimeBO selectOne(String id) {
        return lotteryTimeRoMapper.selectOne(id);
    }

    @Override
    public List<LotteryTimeBO> selectList(Map map) {
        return lotteryTimeRoMapper.selectList(map);
    }

    @Override
    public LotteryTimeBO findbyTime(String activityId, Date date) {
        //根据时间返回时间段对象
        Map<String, Object> map = new HashMap<>();
        if(activityId != null && !activityId.isEmpty()){
            map.put("activityId",activityId);
        }
         List<LotteryTimeBO> list = this.selectList(map);
        for (LotteryTimeBO obj : list) {
            Date startDate = obj.getStartTime();
            Date endDate = obj.getEndTime();
            if(endDate != null && startDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

               // SimpleDateFormat sdfss = new SimpleDateFormat("ss");

                String start =  sdf.format(startDate);
                String end =  sdf.format(endDate);
                String newD =  sdf.format(date);
                try {
                    Date sDate =  sdf.parse(start);
                    Date eDate =  sdf.parse(end);
                    Date nDate =  sdf.parse(newD);

                    if(nDate.getTime()>= sDate.getTime() && nDate.getTime()<= eDate.getTime()){
                        return obj;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
}
