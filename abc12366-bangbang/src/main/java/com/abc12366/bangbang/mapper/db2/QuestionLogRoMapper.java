package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionLog;
import com.abc12366.bangbang.model.question.bo.QuestionLogBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionLikeMapper数据库操作接口类
 * 
 **/

public interface QuestionLogRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionLog selectByPrimaryKey(@Param("id") String id);

    List<QuestionLogBo> selectList(String userId);


}