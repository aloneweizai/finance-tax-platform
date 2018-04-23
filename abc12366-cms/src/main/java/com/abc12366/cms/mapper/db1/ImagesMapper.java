package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.Images;
import org.apache.ibatis.annotations.Param;

public interface ImagesMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(Images record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(Images record);
}