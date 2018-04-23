package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ChnlGroupView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ChnlGroupViewMapper数据库操作接口类
 **/

public interface ChnlGroupViewRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    ChnlGroupView selectByPrimaryKey(@Param("channelId") String channelId);

    /**
     * 查询(根据主键ID查询)
     **/
    List<ChnlGroupView> selectList(@Param("channelId") String channelId);


}