package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionLike;
import com.abc12366.bangbang.model.question.bo.QuestionBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionLikeMapper数据库操作接口类
 * 
 **/

public interface QuestionLikeRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionLike  selectByPrimaryKey(@Param("id") String id);

    List<QuestionBo> selectList(String userId);

    int selectExist(Map map);

    int selectLikeCnt(String id);

    int selectTrampleCnt(String id);


}