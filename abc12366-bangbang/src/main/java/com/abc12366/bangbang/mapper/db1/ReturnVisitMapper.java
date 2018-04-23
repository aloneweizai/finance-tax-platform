package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.ReturnVisit;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * ReturnVisitMapper数据库操作接口类
 * 
 **/

public interface ReturnVisitMapper{



	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int delete(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(ReturnVisit record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int update(ReturnVisit record);

}