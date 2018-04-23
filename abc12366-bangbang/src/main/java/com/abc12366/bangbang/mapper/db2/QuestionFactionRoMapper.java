package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFaction;
import com.abc12366.bangbang.model.question.QuestionFactionHonor;
import com.abc12366.bangbang.model.question.bo.QuestionAnswerBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionListBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionTjBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionFactionMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFaction selectByPrimaryKey(@Param("factionId") String factionId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectFactionCnt(@Param("userId") String userId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionBo> selectList(Map<String, Object> map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionListBo> selectListTj(Map<String, Object> map);

    /**
     *
     * 查询优秀邦派
     *
     **/
    List<QuestionFactionListBo> selectListExcellent(Map<String, Object> map);

    /**
     *
     * 查询潜力邦派
     *
     **/
    List<QuestionFactionListBo> selectListPotential(Map<String, Object> map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    QuestionFactionTjBo selectFactionTj(@Param("factionId") String factionId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionAnswerBo> selectdtListByFactionId(@Param("factionId") String factionId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionHonor> selectFactionHonorList();

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectSplendidNumByFactionId(@Param("factionId") String factionId);

    /**
     *
     * 查询邦派名称是否存在
     *
     **/
    int selectFactionNameCnt(Map<String, Object> map);


}