package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ChnlGroupView;
import org.apache.ibatis.annotations.Param;

/**
 * ChnlGroupViewMapper数据库操作接口类
 **/

public interface ChnlGroupViewMapper {


    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("channelId") String channelId);

    /**
     * 添加
     **/
    int insert(ChnlGroupView record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ChnlGroupView record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ChnlGroupView record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ChnlGroupView record);

}