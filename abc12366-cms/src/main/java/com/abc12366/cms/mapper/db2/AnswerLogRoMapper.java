package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.bo.AnswerLogRolltjBo;
import com.abc12366.cms.model.bo.AnswerLogtjListBo;
import com.abc12366.cms.model.questionnaire.AnswerLog;
import com.abc12366.cms.model.questionnaire.bo.AnswerLogBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AnswerLogMapper数据库操作接口类
 **/

public interface AnswerLogRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    AnswerLog selectByPrimaryKey(@Param("id") String id);

    List<AnswerLogBO> selectList(AnswerLogBO answerLogBO);

    AnswerLogBO selectOne(String id);

    AnswerLogBO selectAvgTime(AnswerLog answerLog);

    /**
     * 查询（浏览统计）
     **/
    List<AnswerLogRolltjBo> selectlltj(Map<String, Object> map);

    /**
     * 查询（浏览统计总数按时间）
     **/
    Integer selectlltjsbysj(Map<String, Object> map);

    /**
     * 查询（浏览统计总数）
     **/
    Integer selectlltjs(Map<String, Object> map);

    /**
     * 查询（用户答题次数）
     **/
    Integer selectldtcnt(Map<String, Object> map);

    AnswerLogtjListBo selectAnswerLogRolltjBo(Map<String, Object> map);
}