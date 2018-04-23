package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.questionnaire.QuestionnaireParam;
import org.apache.ibatis.annotations.Param;

/**
 * QuestionnaireParamMapper数据库操作接口类
 **/

public interface QuestionnaireParamRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    QuestionnaireParam selectByPrimaryKey(@Param("id") String id);


}