package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.questionnaire.Questionnaire;
import com.abc12366.cms.model.questionnaire.bo.QuestionnaireBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * QuestionnaireMapper数据库操作接口类
 **/

public interface QuestionnaireRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Questionnaire selectByPrimaryKey(@Param("id") String id);


    QuestionnaireBO selectOne(String id);

    List<QuestionnaireBO> selectList(Questionnaire questionnaire);

    QuestionnaireBO selectAccessNum(String id);
}