package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.EventApply;
import org.apache.ibatis.annotations.Param;

/**
 * EventApplyMapper数据库操作接口类
 **/

public interface EventApplyMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("applyId") String applyId);

    /**
     * 删除(根据主键ID批量删除)
     **/
    int deleteList(@Param("applyIds") String[] applyIds);

    /**
     * 批量审批
     **/
    int updateStatusList(@Param("applyIds") String[] applyIds);

    int updateStatusNoList(@Param("applyIds") String[] applyIds,@Param("text") String text);

    /**
     * 添加
     **/
    int insert(EventApply record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(EventApply record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(EventApply record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(EventApply record);

}