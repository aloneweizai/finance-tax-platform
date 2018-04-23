package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.SensitiveWords;
import org.apache.ibatis.annotations.Param;

/**
 * SensitiveWordsMapper数据库操作接口类
 **/

public interface SensitiveWordsMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(SensitiveWords record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(SensitiveWords record);

}