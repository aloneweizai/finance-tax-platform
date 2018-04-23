package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionInvite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionInviteMapper数据库操作接口类
 * 
 **/

public interface QuestionInviteRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionInvite selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionInvite> selectList(@Param("questionId") String questionId);

}