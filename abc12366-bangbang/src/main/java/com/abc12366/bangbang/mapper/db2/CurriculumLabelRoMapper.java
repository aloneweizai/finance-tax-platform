package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumLabel;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumLabelBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CurriculumLabelMapper数据库操作接口类
 * 
 **/

public interface CurriculumLabelRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumLabel  selectByPrimaryKey(@Param("curriculumId") String curriculumId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumLabel> selectList(@Param("curriculumId") String curriculumId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumLabelBo> selectLabelList();


}