package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumCourseware;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumCoursewareMapper数据库操作接口类
 * 
 **/

public interface CurriculumCoursewareMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("coursewareId") String coursewareId);

    /**
     *
     * 删除（根据章节删除）
     *
     **/
    int deleteByChapterId(@Param("chapterId") String chapterId);

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
	int insert(CurriculumCourseware record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumCourseware record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumCourseware record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumCourseware record);

}