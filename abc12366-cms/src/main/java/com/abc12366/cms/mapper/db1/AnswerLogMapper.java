package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.AnswerLog;
import org.apache.ibatis.annotations.Param;

/**
 * AnswerLogMapper数据库操作接口类
 **/

public interface AnswerLogMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(AnswerLog record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(AnswerLog record);

    int delete(AnswerLog answerLog);
}