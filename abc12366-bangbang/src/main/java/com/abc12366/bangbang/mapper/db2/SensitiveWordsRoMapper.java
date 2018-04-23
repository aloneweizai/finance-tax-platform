package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.SensitiveWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * SensitiveWordsMapper数据库操作接口类
 **/

public interface SensitiveWordsRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    SensitiveWords selectByPrimaryKey(@Param("id") String id);


    List<SensitiveWords> selectList(SensitiveWords sensitiveWords);

    Set<String> selectListKeywords();

    List<String> selectListWords();
}