package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.CheatsCollect;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * CheatsCollectMapper数据库操作接口类
 * 
 **/

public interface CheatsCollectMapper{

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
	int insert(CheatsCollect record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CheatsCollect record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CheatsCollect record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CheatsCollect record);

    void delete(Map map);

}