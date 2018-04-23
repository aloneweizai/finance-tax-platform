package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.Curriculum;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumMapper数据库操作接口类
 * 
 **/

public interface CurriculumMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("curriculumId") String curriculumId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(Curriculum record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(Curriculum record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(Curriculum record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(Curriculum record);

    /**
     *
     * 更新状态
     *
     **/
    int updateStatus(Curriculum record);

    /**
     *
     * 更新浏览量
     *
     **/
    int updateBrowsesDay(@Param("curriculumId") String curriculumId);

    /**
     *
     * 更新观看量
     *
     **/
    int updateWatchsDay(@Param("curriculumId") String curriculumId);

}