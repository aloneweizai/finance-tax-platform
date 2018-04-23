package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumCourseware;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumCoursewareBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumCoursewareMapper数据库操作接口类
 * 
 **/

public interface CurriculumCoursewareRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumCourseware  selectByPrimaryKey(@Param("coursewareId") String coursewareId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumCoursewareBo> selectList(Map<String, Object> map);

    /**
     *
     * 查询课程是否免费
     *
     **/
    int  selectCurriculumisFree(@Param("coursewareId") String coursewareId);

    /**
     *
     * 查询课件是否免费
     *
     **/
    int  selectCoursewareisFree(@Param("coursewareId") String coursewareId);

    /**
     * 查询用户会员等级是否可观看
     **/
    int selectGradeWatch(Map<String, Object> map);

    /**
     * 查询用户是否已购买课程
     **/
    int selectIsBuy(Map<String, Object> map);

    /**
     * 查询课件标题或者序号是否已存在
     **/
    int selectCoursewareCnt(Map<String, Object> map);


    List<CurriculumCoursewareBo> selectCoursewareList(Map<String, Object> dataMap);
}