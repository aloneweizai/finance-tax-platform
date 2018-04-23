package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.questionnaire.Prize;
import org.apache.ibatis.annotations.Param;

/**
 * PrizeMapper数据库操作接口类
 **/

public interface PrizeRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Prize selectByPrimaryKey(@Param("id") String id);


}