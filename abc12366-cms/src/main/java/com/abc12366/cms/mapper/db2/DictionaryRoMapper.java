package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Dictionary;
import org.apache.ibatis.annotations.Param;

/**
 * DictionaryMapper数据库操作接口类
 **/

public interface DictionaryRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Dictionary selectByPrimaryKey(@Param("id") Long id);


}