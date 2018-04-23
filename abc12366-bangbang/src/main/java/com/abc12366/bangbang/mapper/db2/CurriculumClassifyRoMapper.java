package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumClassify;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumClassifyBo;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumClassifysBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumChapterMapper数据库操作接口类
 * 
 **/

public interface CurriculumClassifyRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
    CurriculumClassify selectByPrimaryKey(@Param("classifyId") String classifyId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumClassifyBo> selectList(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumClassifysBo> selectClassifyListsy();

    /**
     *
     * 查询分类ID是否已存在
     *
     **/
    int selectClassifyCnt(@Param("classifyId") String classifyId);

    /**
     *
     * 查询分类名称是否已存在
     *
     **/
    int selectClassifyNameCnt(CurriculumClassifyBo recod);


}