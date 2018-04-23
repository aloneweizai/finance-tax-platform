package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.questionnaire.WhiteList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * WhiteListMapper数据库操作接口类
 **/

public interface WhiteListRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    WhiteList selectByPrimaryKey(@Param("id") String id);


    List<WhiteList> selectList(WhiteList whiteList);
}