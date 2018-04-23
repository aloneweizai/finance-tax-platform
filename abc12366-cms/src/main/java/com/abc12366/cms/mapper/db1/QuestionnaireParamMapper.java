package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.QuestionnaireParam;
import org.apache.ibatis.annotations.Param;

/**
 * QuestionnaireParamMapper数据库操作接口类
 **/

public interface QuestionnaireParamMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("questionId") String questionId);

    /**
     * 添加
     **/
    int insert(QuestionnaireParam record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(QuestionnaireParam record);

}