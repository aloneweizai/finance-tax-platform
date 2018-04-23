package com.abc12366.uc.service.impl;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-19
 */


import com.abc12366.uc.mapper.db1.LotteryTemplateMapper;
import com.abc12366.uc.mapper.db2.LotteryTemplateRoMapper;
import com.abc12366.uc.model.LotteryTemplate;
import com.abc12366.uc.model.bo.LotteryTemplateBO;
import com.abc12366.uc.service.LotteryTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LotteryTemplateServiceImpl implements LotteryTemplateService{
private static final Logger LOGGER = LoggerFactory.getLogger(LotteryTemplateServiceImpl.class);
@Autowired
private LotteryTemplateMapper lotteryTemplateMapper;
@Autowired
private LotteryTemplateRoMapper lotteryTemplateRoMapper;
@Override
public LotteryTemplateBO update(LotteryTemplateBO lotteryTemplateBO, String id) {
LotteryTemplate obj = new LotteryTemplate();
BeanUtils.copyProperties(lotteryTemplateBO, obj);
obj.setId(id);
obj.setCreateTime(null);
obj.setLastTime(new Date());
int result = lotteryTemplateMapper.update(obj);
if (result != 1) {
LOGGER.warn("修改失败，参数：" + obj);
throw new RuntimeException("seviceErr:修改失败");
}
LotteryTemplateBO returnObj = new LotteryTemplateBO();
BeanUtils.copyProperties(obj, returnObj);
return returnObj;
}
@Override
public boolean delete(String id) {
Integer result = lotteryTemplateMapper.delete(id);
return (result == 1);
}
@Override
public LotteryTemplateBO insert(LotteryTemplateBO lotteryTemplateBO) {
LotteryTemplate obj = new LotteryTemplate();
BeanUtils.copyProperties(lotteryTemplateBO, obj);
Date date = new Date();
obj.setId(java.util.UUID.randomUUID().toString());
obj.setCreateTime(date);
obj.setLastTime(date);
int result = lotteryTemplateMapper.insert(obj);
if (result != 1) {
LOGGER.warn("新增失败，参数：" + obj);
throw new RuntimeException("seviceErr:新增失败");
}
LotteryTemplateBO returnObj = new LotteryTemplateBO();
BeanUtils.copyProperties(obj, returnObj);
return returnObj;
}
@Override
public LotteryTemplateBO selectOne(String id) {
return lotteryTemplateRoMapper.selectOne(id);
}
@Override
public List<LotteryTemplateBO> selectList(Map map) {
return lotteryTemplateRoMapper.selectList(map);
}
}
