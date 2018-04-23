package com.abc12366.message.service.impl;

import com.abc12366.gateway.util.Utils;
import com.abc12366.message.mapper.db1.HndsBindMapper;
import com.abc12366.message.mapper.db2.HndsBindRoMapper;
import com.abc12366.message.service.IHndsBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
/**
 *  湖南地税登录成功绑定实现类
 * @author zhushuai 2017-11-7
 *
 */
@Service
public class HndsBindServiceImpl implements IHndsBindService{

	@Autowired
    private HndsBindMapper hndsBindMapper;
	@Autowired
	private HndsBindRoMapper hndsBindRoMapper;
	
	@Transactional("db1TxManager")
	public int bindHnds(HashMap<String,Object> map) {
		
		long num=hndsBindRoMapper.findHndsBind(map);
		if(num>0){
			return -1;
		}else{
		    Date now=new Date();
			map.put("id", Utils.uuid());
			map.put("status", true);
			map.put("createTime", now);
			map.put("lastUpdate", now);
			return hndsBindMapper.insert(map);
		}
	}

}
