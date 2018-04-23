package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.bo.AnswerdttjBo;
import com.abc12366.cms.model.questionnaire.Answer;
import com.abc12366.cms.model.questionnaire.bo.AnswertjBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AnswerMapper数据库操作接口类
 **/

public interface AnswerRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Answer selectByPrimaryKey(@Param("id") String id);

    Answer selectByLogId(Answer answer);

    List<Answer> selectList(Answer answer);

    List<AnswertjBO> selectListBysubjectsId(String subjectsId);

    List<AnswerdttjBo> selectdttj(Map<String, Object> map);

    Integer selectdttjs(Map<String, Object> map);
}