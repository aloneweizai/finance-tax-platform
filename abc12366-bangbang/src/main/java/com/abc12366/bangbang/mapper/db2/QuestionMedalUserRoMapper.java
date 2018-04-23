package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionMedalUser;
import com.abc12366.bangbang.model.question.bo.QuestionMedalUserBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionMedalUserMapper数据库操作接口类
 * 
 **/

public interface QuestionMedalUserRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionMedalUser selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionMedalUserBo> selectListByUserId(Map map);


}