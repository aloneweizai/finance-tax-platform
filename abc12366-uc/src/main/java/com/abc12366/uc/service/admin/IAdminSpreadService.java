package com.abc12366.uc.service.admin;

import java.util.List;
import java.util.Map;

public interface IAdminSpreadService {

	/**
	 * 我的客户列表
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> myConstomers(Map<String, Object> map, int pageNum, int pageSize);

}
