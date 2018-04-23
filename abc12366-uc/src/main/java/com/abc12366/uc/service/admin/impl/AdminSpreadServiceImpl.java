package com.abc12366.uc.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc12366.uc.mapper.db2.AdminSpreadRoMapper;
import com.abc12366.uc.service.admin.IAdminSpreadService;
import com.github.pagehelper.PageHelper;

@Service
public class AdminSpreadServiceImpl implements IAdminSpreadService {
	
	@Autowired
	private AdminSpreadRoMapper adminSpreadRoMapper;

	@Override
	public List<Map<String, Object>> myConstomers(Map<String, Object> map, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
		return adminSpreadRoMapper.myConstomers(map);
	}

}
