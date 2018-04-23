package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionClassifyTag;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyTagBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionClassifyTagMapper数据库操作接口类
 * 
 **/

public interface QuestionClassifyTagRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionClassifyTag  selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionClassifyTag> selectList(@Param("classifyId") String classifyId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionClassifyTagBo> selectClassifyTagList(Map map);


}