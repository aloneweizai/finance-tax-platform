package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionFactionTagMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionTagRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionTag selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionTag> selectList(@Param("factionId") String factionId);


}