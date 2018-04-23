package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.UCUserBO;
import com.abc12366.bangbang.model.question.QuestionAccepted;
import com.abc12366.bangbang.model.question.bo.QuestionAcceptedBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionAcceptedMapper数据库操作接口类
 * 
 **/

public interface QuestionAcceptedRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionAccepted selectByPrimaryKey(@Param("id") String id);

	List<QuestionAccepted> selectList(QuestionAcceptedBO returnVisitBO);

	List<QuestionAcceptedBO> selectStatisList(QuestionAcceptedBO param);

	UCUserBO selectUCUser(String userId);
}