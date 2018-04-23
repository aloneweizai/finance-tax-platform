package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Template;
import com.abc12366.cms.model.bo.TemplateBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TemplateMapper数据库操作接口类
 **/

public interface TemplateRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Template selectByPrimaryKey(@Param("templateId") String templateId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<TemplateBo> selectList(Map<String, Object> map);

}