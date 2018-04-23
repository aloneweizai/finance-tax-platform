package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionInform;
import com.abc12366.bangbang.model.question.bo.QuestionFactionInformBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionFactionInformMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionInformRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionInform selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionInformBo> selectList(Map map);


}