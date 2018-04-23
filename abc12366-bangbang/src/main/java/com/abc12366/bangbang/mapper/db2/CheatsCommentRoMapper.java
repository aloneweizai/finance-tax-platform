package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.bo.CheatsCommentBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CheatsCommentMapper数据库操作接口类
 * 
 **/

public interface CheatsCommentRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
    CheatsCommentBo selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CheatsCommentBo> selectList(Map<String, Object> map);

    /**
     * 查询我的评论
     **/
    List<CheatsCommentBo> selectMyCommentList(Map<String, Object> map);

    /**
     *
     * 查询评论数
     *
     **/
    int  selectCommentCnt(@Param("id") String id);

}