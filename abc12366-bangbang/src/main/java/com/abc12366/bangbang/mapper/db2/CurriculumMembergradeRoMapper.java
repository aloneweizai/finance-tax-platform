package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumMembergrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CurriculumMembergradeMapper数据库操作接口类
 * 
 **/

public interface CurriculumMembergradeRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumMembergrade  selectByPrimaryKey(@Param("curriculumId") String curriculumId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumMembergrade> selectList(@Param("curriculumId") String curriculumId);


}