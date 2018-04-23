package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.Answer;
import org.apache.ibatis.annotations.Param;

/**
 * AnswerMapper数据库操作接口类
 **/

public interface AnswerMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("answerLogId") String answerLogId);

    /**
     * 添加
     **/
    int insert(Answer record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(Answer record);

}