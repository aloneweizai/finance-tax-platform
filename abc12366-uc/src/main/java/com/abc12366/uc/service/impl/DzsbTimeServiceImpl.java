package com.abc12366.uc.service.impl;

import com.abc12366.uc.mapper.db1.DzsbTimeMapper;
import com.abc12366.uc.mapper.db2.DzsbTimeRoMapper;
import com.abc12366.uc.model.job.DzsbTime;
import com.abc12366.uc.service.IDzsbTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dzsbTimeService")
public class DzsbTimeServiceImpl implements IDzsbTimeService{
	
	@Autowired
	private DzsbTimeMapper timeMapper;
	
	@Autowired
	private DzsbTimeRoMapper timeRoMapper;

	@Override
	@Transactional("db1TxManager")
	public void insert(DzsbTime dzsbTime) {
		timeMapper.insert(dzsbTime);
	}

	@Transactional("db1TxManager")
	public void update(DzsbTime dzsbTime) {
		timeMapper.update(dzsbTime);
	}

	@Override
	public DzsbTime select(String ywlx) {
		return timeRoMapper.select(ywlx);
	}

}
