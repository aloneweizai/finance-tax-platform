package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumChapter;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumChapterMapper数据库操作接口类
 * 
 **/

public interface CurriculumChapterMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("chapterId") String chapterId);

    /**
     *
     * 删除（根据课程删除）
     *
     **/
    int deleteByCurriculumId(@Param("curriculumId") String curriculumId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumChapter record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumChapter record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumChapter record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumChapter record);

}