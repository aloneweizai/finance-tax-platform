package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.questionnaire.Images;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImagesRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Images selectByPrimaryKey(@Param("id") String id);

    List<Images> selectList(Images images);
}