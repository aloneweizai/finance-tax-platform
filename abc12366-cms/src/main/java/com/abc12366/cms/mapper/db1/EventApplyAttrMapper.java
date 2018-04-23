package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.EventApplyAttr;
import org.apache.ibatis.annotations.Param;

/**
 * EventApplyAttrMapper数据库操作接口类
 **/

public interface EventApplyAttrMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("applyId") String applyId);

    /**
     * 删除(根据主键ID批量删除)
     **/
    int deleteList(@Param("applyIds") String[] applyIds);

    /**
     * 添加
     **/
    int insert(EventApplyAttr record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(EventApplyAttr record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(EventApplyAttr record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(EventApplyAttr record);

}