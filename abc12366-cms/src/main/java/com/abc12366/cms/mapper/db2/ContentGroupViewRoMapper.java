package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ContentGroupView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ContentGroupViewMapper数据库操作接口类
 **/

public interface ContentGroupViewRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    ContentGroupView selectByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 查询(根据contentId查询)
     **/
    List<ContentGroupView> selectList(@Param("contentId") String contentId);


}