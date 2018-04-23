package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.Option;
import org.apache.ibatis.annotations.Param;

/**
 * OptionMapper数据库操作接口类
 **/

public interface OptionMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(Option record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(Option record);

    /**
     * 根据SubjectId删除
     *
     * @param subjectId
     * @return
     */
    int deleteBySubjectsId(String subjectId);
}