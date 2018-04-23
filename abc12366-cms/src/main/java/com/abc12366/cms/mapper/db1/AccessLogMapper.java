package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.AccessLog;
import org.apache.ibatis.annotations.Param;

/**
 * AccessLogMapper数据库操作接口类
 **/

public interface AccessLogMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(AccessLog record);


    /**
     * 修改 （匹配有值的字段）
     **/
    int update(AccessLog record);


    void deleteByQuestionId(@Param("questionId") String questionId);
}