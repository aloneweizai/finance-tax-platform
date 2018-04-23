package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.SystemRecordCompany;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * SystemRecordCompanyMapper数据库操作接口类
 * 
 **/

public interface SystemRecordCompanyMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SystemRecordCompany  selectByPrimaryKey(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int delete(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(SystemRecordCompany record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(SystemRecordCompany record);

}