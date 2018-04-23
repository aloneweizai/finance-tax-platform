package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionFaction;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionFactionMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("factionId") String factionId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionFaction record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionFaction record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionFaction record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionFaction record);

	/**
	 *
	 * 修改奖励积分
	 *
	 **/
	int updateAwardPoint(@Param("factionId") String factionId, @Param("awardPoint")Integer awardPoint);

	int decreaseAwardPoint(@Param("factionId") String factionId, @Param("awardPoint")Integer awardPoint);
}