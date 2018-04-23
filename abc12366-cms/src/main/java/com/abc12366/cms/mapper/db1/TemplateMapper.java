package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Template;
import org.apache.ibatis.annotations.Param;

/**
 * TemplateMapper数据库操作接口类
 **/

public interface TemplateMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("templateId") String templateId);

    /**
     * 添加
     **/
    int insert(Template record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Template record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Template record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Template record);

}