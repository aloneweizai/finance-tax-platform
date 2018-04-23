package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionMemberHonor;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionMemberHonorMapper数据库操作接口类
 * 
 **/

public interface QuestionMemberHonorRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionMemberHonor selectByPrimaryKey(@Param("id") String id);


}