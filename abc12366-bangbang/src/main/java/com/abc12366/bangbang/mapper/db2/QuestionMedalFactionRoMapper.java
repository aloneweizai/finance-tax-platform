package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionMedalFaction;
import com.abc12366.bangbang.model.question.bo.QuestionMedalFactionBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionMedalUserMapper数据库操作接口类
 * 
 **/

public interface QuestionMedalFactionRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionMedalFaction selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionMedalFactionBo> selectListByFactionId(Map map);


}