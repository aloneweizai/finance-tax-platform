package com.abc12366.uc.service.impl;

import com.abc12366.uc.mapper.db1.DzfpMapper;
import com.abc12366.uc.mapper.db2.DzfpRoMapper;
import com.abc12366.uc.model.dzfp.Einvocie;
import com.abc12366.uc.service.IDzfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dzfpService")
public class DzfqServiceImpl implements IDzfpService{
	
	@Autowired
	private DzfpRoMapper dzfpRoMapper;
	@Autowired
	private DzfpMapper dzfpMapper;

	@Override
	public Einvocie selectOne(String FPQQLSH) {
		
		return dzfpRoMapper.selectOne(FPQQLSH);
	}

	@Override
	public List<Einvocie> selectList(Einvocie einvocie) {
		
		return dzfpRoMapper.selectList(einvocie);
	}

	@Override
	public void insert(Einvocie einvocie) {
		
		dzfpMapper.insert(einvocie);
	}

	@Override
	public int update(Einvocie einvocie) {
		
		return dzfpMapper.update(einvocie);
	}

}
