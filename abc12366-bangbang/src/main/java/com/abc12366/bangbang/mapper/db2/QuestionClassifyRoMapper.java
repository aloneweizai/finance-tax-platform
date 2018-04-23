package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionClassify;
import com.abc12366.bangbang.model.question.QuestionClassifyStatistics;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionClassifyMapper数据库操作接口类
 * 
 **/

public interface QuestionClassifyRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionClassify  selectByPrimaryKey(@Param("classifyCode") String classifyCode);

    /**
     * 查询(根据查询条件查询)
     **/
    List<QuestionClassifyBo> selectList(Map<String, Object> map);

    /**
     *
     * 查询分类Code是否已存在
     *
     **/
    int selectClassifyCnt(@Param("classifyCode") String classifyCode);


    /*话题统计分析*/
    List<QuestionClassifyStatistics> selectClassifyStatistics(Map<String, Object> map);

}