package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.UserPointsApply;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * UserPointsApplyMapper数据库操作接口类
 * 
 **/

public interface UserPointsApplyMapper{


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
	int insert(UserPointsApply record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(UserPointsApply record);

}