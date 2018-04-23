package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.DzsbHngs;
import com.abc12366.bangbang.model.SystemRecordCompany;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * SystemRecordCompanyMapper数据库操作接口类
 * 
 **/

public interface SystemRecordCompanyRoMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SystemRecordCompany  selectByPrimaryKey(@Param("id") String id);

	List<SystemRecordCompany> selectRecordCompanyList(Map<String, Object> map);

	int selectByDateCount(@Param("createTime")Date createTime);

	List<SystemRecordCompany> statisList(Map<String, Object> map);

	List<DzsbHngs> statisRecordCompanyList(Map<String, Object> map);
}