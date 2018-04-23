package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.Prize;
import org.apache.ibatis.annotations.Param;

/**
 * PrizeMapper数据库操作接口类
 **/

public interface PrizeMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(Prize record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(Prize record);

}