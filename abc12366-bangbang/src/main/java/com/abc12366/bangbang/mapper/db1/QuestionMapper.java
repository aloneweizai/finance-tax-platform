package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.Question;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionMapper数据库操作接口类
 * 
 **/

public interface QuestionMapper{


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
	int insert(Question record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(Question record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(Question record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(Question record);


    /**
     * 修改浏览量(根据主键ID)
     **/
    int updateBrowseNum(@Param("id") String id);


	/**
	 * 修改被举报数(根据主键ID)
	 **/
	int updateReportNum(@Param("id") String id);

	/**
	 * 话题推荐
	 **/
	void recommend(@Param("id")String id, @Param("isRecommend")Boolean isRecommend);
}