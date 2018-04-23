package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionAttention;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * QuestionAttentionMapper数据库操作接口类
 * 
 **/

public interface QuestionAttentionMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("attentionId") String attentionId);

    void delete(Map map);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionAttention record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionAttention record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionAttention record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionAttention record);

    /**
     *
     * 修改为已读(根据主键ID)
     *
     **/
    int updateIsRead(@Param("attentionId") String attentionId);

}