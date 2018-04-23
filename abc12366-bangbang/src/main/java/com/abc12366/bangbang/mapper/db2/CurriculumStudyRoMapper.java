package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumStudy;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumStudyBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumStudyMapper数据库操作接口类
 * 
 **/

public interface CurriculumStudyRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumStudy  selectByPrimaryKey(@Param("studyId") String studyId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumStudyBo> selectList(Map<String, Object> map);

    /**
    * 查询学习次数
    **/
    int selectStudyCnt(Map<String, Object> map);

}