package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.Subjects;
import org.apache.ibatis.annotations.Param;

/**
 * SubjectsMapper数据库操作接口类
 **/

public interface SubjectsMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(Subjects record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(Subjects record);

    /**
     * 根据Id和问卷id
     *
     * @param subjects
     * @return
     */
    int delete(Subjects subjects);
}