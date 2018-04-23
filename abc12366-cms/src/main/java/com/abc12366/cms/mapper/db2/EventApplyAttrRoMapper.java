package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.EventApplyAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EventApplyAttrMapper数据库操作接口类
 **/

public interface EventApplyAttrRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    List<EventApplyAttr> selectByPrimaryKey(@Param("applyId") String applyId);

}