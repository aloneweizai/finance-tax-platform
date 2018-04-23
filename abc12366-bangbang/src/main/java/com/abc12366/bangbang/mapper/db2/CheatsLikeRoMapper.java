package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.CheatsLike;
import com.abc12366.bangbang.model.question.bo.CheatsBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CheatsLikeMapper数据库操作接口类
 * 
 **/

public interface CheatsLikeRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CheatsLike selectByPrimaryKey(@Param("likeId") String likeId);

    List<CheatsBo> selectList(String userId);

    int selectExist(Map map);

    int selectLikeCnt(String id);

    int selectTrampleCnt(String id);

}