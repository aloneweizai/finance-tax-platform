package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionFactionMember;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * QuestionFactionMemberMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionMemberMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("memberId") String memberId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionFactionMember record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionFactionMember record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionFactionMember record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionFactionMember record);

    /**
     *
     * 设置职位
     *
     **/
    int updateDuty(Map map);

}