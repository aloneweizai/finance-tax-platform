package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumChapter;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumChapterBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumChapterMapper数据库操作接口类
 * 
 **/

public interface CurriculumChapterRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumChapter  selectByPrimaryKey(@Param("chapterId") String chapterId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumChapterBo> selectList(Map<String, Object> map);


}