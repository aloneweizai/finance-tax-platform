package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.EventSponsor;
import com.abc12366.cms.model.bo.EventSponsorBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EventSponsorMapper数据库操作接口类
 **/

public interface EventSponsorRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    EventSponsor selectByPrimaryKey(@Param("sponsorId") String sponsorId);

    /**
     * 查询（根据主键ID查询）
     **/
    List<EventSponsorBo> selectList();

}