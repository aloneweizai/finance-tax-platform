package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumOrderMapper数据库操作接口类
 * 
 **/

public interface CurriculumOrderMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("orderId") String orderId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumOrder record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumOrder record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumOrder record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumOrder record);

}