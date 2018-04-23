package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.questionnaire.Option;
import org.apache.ibatis.annotations.Param;

/**
 * OptionMapper数据库操作接口类
 **/

public interface OptionRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Option selectByPrimaryKey(@Param("id") String id);


}