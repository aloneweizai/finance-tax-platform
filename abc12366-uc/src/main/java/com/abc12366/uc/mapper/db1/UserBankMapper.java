package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.UserBank;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * UserBankMapper数据库操作接口类
 * 
 **/

public interface UserBankMapper{



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
	int insert(UserBank record);


	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(UserBank record);

}