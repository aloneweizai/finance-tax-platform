package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.CheatsTag;
import com.abc12366.bangbang.model.question.bo.CheatsTagBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CheatsTagMapper数据库操作接口类
 * 
 **/

public interface CheatsTagRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CheatsTag selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<CheatsTag> selectList(@Param("cheatsId") String cheatsId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<CheatsTagBo> selectTagList();

}