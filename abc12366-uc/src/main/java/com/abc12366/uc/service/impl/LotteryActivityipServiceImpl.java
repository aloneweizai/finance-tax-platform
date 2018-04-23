package com.abc12366.uc.service.impl;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-11-11
 */


import com.abc12366.uc.mapper.db1.LotteryActivityipMapper;
import com.abc12366.uc.mapper.db2.LotteryActivityipRoMapper;
import com.abc12366.uc.model.LotteryActivityip;
import com.abc12366.uc.model.bo.LotteryActivityipBO;
import com.abc12366.uc.service.LotteryActivityipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LotteryActivityipServiceImpl implements LotteryActivityipService{
private static final Logger LOGGER = LoggerFactory.getLogger(LotteryActivityipServiceImpl.class);
@Autowired
private LotteryActivityipMapper lotteryActivityipMapper;
@Autowired
private LotteryActivityipRoMapper lotteryActivityipRoMapper;
@Override
public LotteryActivityipBO update(LotteryActivityipBO lotteryActivityipBO, String id) {
LotteryActivityip obj = new LotteryActivityip();
BeanUtils.copyProperties(lotteryActivityipBO, obj);
obj.setId(id);
obj.setCreateTime(null);
int result = lotteryActivityipMapper.update(obj);
if (result != 1) {
LOGGER.warn("修改失败，参数：" + obj);
throw new RuntimeException("seviceErr:修改失败");
}
LotteryActivityipBO returnObj = new LotteryActivityipBO();
BeanUtils.copyProperties(obj, returnObj);
return returnObj;
}
@Override
public boolean delete(String id) {
Integer result = lotteryActivityipMapper.delete(id);
return (result == 1);
}
@Override
public LotteryActivityipBO insert(LotteryActivityipBO lotteryActivityipBO) {
LotteryActivityip obj = new LotteryActivityip();
BeanUtils.copyProperties(lotteryActivityipBO, obj);
Date date = new Date();
obj.setId(java.util.UUID.randomUUID().toString());
obj.setCreateTime(date);
int result = lotteryActivityipMapper.insert(obj);
if (result != 1) {
LOGGER.warn("新增失败，参数：" + obj);
throw new RuntimeException("seviceErr:新增失败");
}
LotteryActivityipBO returnObj = new LotteryActivityipBO();
BeanUtils.copyProperties(obj, returnObj);
return returnObj;
}
@Override
public LotteryActivityipBO selectOne(String id) {
return lotteryActivityipRoMapper.selectOne(id);
}
@Override
public List<LotteryActivityipBO> selectList(Map map) {
return lotteryActivityipRoMapper.selectList(map);
}
}
