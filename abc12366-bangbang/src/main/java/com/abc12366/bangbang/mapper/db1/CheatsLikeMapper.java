package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.CheatsLike;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CheatsLikeMapper数据库操作接口类
 * 
 **/

public interface CheatsLikeMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("likeId") String likeId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CheatsLike record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CheatsLike record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CheatsLike record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CheatsLike record);

}