package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionLike;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * QuestionLikeMapper数据库操作接口类
 * 
 **/

public interface QuestionLikeMapper{


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
	int insert(QuestionLike record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionLike record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionLike record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionLike record);

    void delete(Map map);

}