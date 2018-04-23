package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumLecturerGx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CurriculumLecturerMapper数据库操作接口类
 * 
 **/

public interface CurriculumLecturerGxRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
    List<CurriculumLecturerGx> selectByPrimaryKey(@Param("lecturerId") String lecturerId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumLecturerGx> selectList(@Param("curriculumId") String curriculumId);

    /**
     *
     * 查询该讲师是否有课程
     *
     **/
    int  selectLecturerCnt(@Param("lecturerId") String lecturerId);


}