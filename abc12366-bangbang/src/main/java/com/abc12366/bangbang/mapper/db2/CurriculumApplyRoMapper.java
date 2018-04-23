package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumApply;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumApplyBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumApplyMapper数据库操作接口类
 * 
 **/

public interface CurriculumApplyRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumApply  selectByPrimaryKey(@Param("applyId") String applyId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumApplyBo> selectList(Map<String, Object> map);

    /**
     * 根据课程ID，用户ID查询报名签到信息
     **/
    CurriculumApplyBo selectCurrApply(Map<String, Object> map);

    /**
     * 查询报名人数
     **/
    int selectApplyCnt(Map<String, Object> map);


}