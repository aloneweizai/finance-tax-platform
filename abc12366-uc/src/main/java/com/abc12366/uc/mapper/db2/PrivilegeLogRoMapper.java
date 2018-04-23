package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.PrivilegeLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-25
 * Time: 14:37
 */
public interface PrivilegeLogRoMapper {
    List<PrivilegeLog> selectListMonth(@Param("logType") String logType, @Param("userId") String userId);

    List<PrivilegeLog> selectListYear(@Param("logType") String logType, @Param("userId") String userId);

    List<PrivilegeLog> selectList(@Param("logType") String logType, @Param("userId") String userId);
}
