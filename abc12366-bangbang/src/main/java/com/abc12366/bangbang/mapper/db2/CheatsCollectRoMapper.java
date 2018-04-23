package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.CheatsCollect;
import com.abc12366.bangbang.model.question.bo.CheatsBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CheatsCollectMapper数据库操作接口类
 * 
 **/

public interface CheatsCollectRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CheatsCollect selectByPrimaryKey(@Param("collectId") String collectId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<CheatsBo> selectList(@Param("userId") String userId);

    int selectExist(Map map);

    int selectCollectCnt(String questionId);

}