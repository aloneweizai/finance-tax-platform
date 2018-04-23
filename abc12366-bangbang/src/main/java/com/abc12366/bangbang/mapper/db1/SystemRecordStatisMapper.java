package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.SystemRecordStatis;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * SystemRecordStatisMapper数据库操作接口类
 * 
 **/

public interface SystemRecordStatisMapper{


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
	int insert(SystemRecordStatis record);


	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(SystemRecordStatis record);

}