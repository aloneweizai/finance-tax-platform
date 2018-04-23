package com.abc12366.cms.service.impl;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-08-25
 */

import com.abc12366.cms.mapper.db1.AdpageLogMapper;
import com.abc12366.cms.mapper.db2.AdpageLogRoMapper;
import com.abc12366.cms.model.AdpageLog;
import com.abc12366.cms.model.bo.AdpageLogBO;
import com.abc12366.cms.service.AdpageLogService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
 

@Service
public class AdpageLogServiceImpl implements AdpageLogService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdpageLogServiceImpl.class);
	@Autowired
	private AdpageLogMapper adpageLogMapper;
	@Autowired
	private AdpageLogRoMapper adpageLogRoMapper;

	@Override
	public AdpageLogBO update(AdpageLogBO adpageLogBO, String id) {
		AdpageLog obj = new AdpageLog();
		BeanUtils.copyProperties(adpageLogBO, obj);
		obj.setId(id);
		obj.setCreateTime(null);
		int result = adpageLogMapper.update(obj);
		if (result != 1) {
			LOGGER.warn("修改失败，参数：" + obj);
			throw new RuntimeException("seviceErr:修改失败");
		}
		AdpageLogBO returnObj = new AdpageLogBO();
		BeanUtils.copyProperties(obj, returnObj);
		return returnObj;
	}

	@Override
	public boolean delete(String id) {
		Integer result = adpageLogMapper.delete(id);
		return (result == 1);
	}

	@Override
	public AdpageLogBO insert(AdpageLogBO adpageLogBO) {

		AdpageLog obj = new AdpageLog();
		BeanUtils.copyProperties(adpageLogBO, obj);
		if(obj.getAdPageId() == null || obj.getAdPageId().isEmpty()){
			LOGGER.info("{新增记录失败}", adpageLogBO);
			throw new ServiceException(4407);
		}

		Date date = new Date();
		obj.setId(java.util.UUID.randomUUID().toString());
		obj.setCreateTime(date);
		int result = adpageLogMapper.insert(obj);
		if (result != 1) {
			LOGGER.warn("新增失败，参数：" + obj);
			throw new RuntimeException("seviceErr:新增失败");
		}
		AdpageLogBO returnObj = new AdpageLogBO();
		BeanUtils.copyProperties(obj, returnObj);
		return returnObj;
	}

	@Override
	public AdpageLogBO selectOne(String id) {
		return adpageLogRoMapper.selectOne(id);
	}

	@Override
	public List<AdpageLogBO> selectList(Map map) {
		return adpageLogRoMapper.selectList(map);
	}
}
