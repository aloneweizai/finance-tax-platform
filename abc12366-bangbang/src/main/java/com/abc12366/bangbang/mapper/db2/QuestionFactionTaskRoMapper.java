package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionTask;
import com.abc12366.bangbang.model.question.bo.QuestionFactionTaskBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionFactionHonorMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionTaskRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
    QuestionFactionTask selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询邦派任务动态
     **/
    List<QuestionFactionTaskBo> selectListdt();

    /**
     * 查询邦派任务情况
     **/
    List<QuestionFactionTaskBo> selectTaskList(Map map);


}