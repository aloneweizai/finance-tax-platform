package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionHonor;
import com.abc12366.bangbang.model.question.bo.QuestionFactionPhBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionFactionHonorMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionHonorRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionHonor selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询邦派排行
     **/
    List<QuestionFactionPhBo> selectList(String honorTime);

    /**
     * 查询邦派累计排行
     **/
    List<QuestionFactionPhBo> selectljList();


}