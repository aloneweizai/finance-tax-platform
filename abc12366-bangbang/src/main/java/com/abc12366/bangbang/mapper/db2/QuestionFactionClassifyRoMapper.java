package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionClassify;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionFactionClassifyMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionClassifyRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionClassify selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionClassify> selectList(@Param("factionId") String factionId);

    /**
     *
     * 查询该用户创建的分类是否已存在
     *
     **/
    int selectClassifyCnt(Map<String, Object> map);


}