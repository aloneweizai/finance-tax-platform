package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionCollect;
import com.abc12366.bangbang.model.question.bo.QuestionBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionCollectMapper数据库操作接口类
 * 
 **/

public interface QuestionCollectRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionCollect  selectByPrimaryKey(@Param("collectId") String collectId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionBo> selectList(@Param("userId") String userId);

    int selectExist(Map map);

    int selectCollectCnt(String questionId);


}