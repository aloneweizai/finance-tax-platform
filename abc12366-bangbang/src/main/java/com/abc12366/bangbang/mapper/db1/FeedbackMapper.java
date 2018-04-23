package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.Feedback;
import com.abc12366.bangbang.model.bo.FeedbackParamBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * FeedbackMapper数据库操作接口类
 * 
 **/

public interface FeedbackMapper {


	/**
	 *
	 * 查询（根据主键ID查询）
	 *
	 **/
	List<Feedback> selectList(FeedbackParamBO param);

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Feedback  selectByPrimaryKey(@Param("id") String id);

	/**
	 *
	 * 查询（未回复）
	 *
	 **/
	Long  selectCntUnanswered();

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") String id);

	/**
	 *
	 * 删除（根据主键ID批量删除）
	 *
	 **/
	int deleteByPrimaryKeys(List<String> ids);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(Feedback record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(Feedback record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(Feedback record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(Feedback record);

}