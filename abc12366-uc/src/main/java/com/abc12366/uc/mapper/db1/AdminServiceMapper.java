package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.AdminService;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * AdminServiceMapper数据库操作接口类
 * 
 **/

public interface AdminServiceMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(AdminService record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(AdminService record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(AdminService record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(AdminService record);

}