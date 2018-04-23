package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.gift.UgiftLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UgiftLogMapper数据库操作接口类
 *
 * @author lizhongwei
 * @date 2017-12-18 3:09 PM
 * @since 1.0.0
 **/

public interface UgiftLogRoMapper {


    /**
     * 查询（根据主键ID查询）
     *
     * @param id PK
     **/
    UgiftLog selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询申请单日志
     *
     * @param applyId 申请单ID
     * @return 日志列表
     */
    List<UgiftLog> selectListByApplyId(String applyId);
}