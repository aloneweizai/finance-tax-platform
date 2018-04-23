package com.abc12366.uc.service;

import com.abc12366.uc.model.PrivilegeLog;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-25
 * Time: 11:39
 */
public interface PrivilegeLogService {
    /**
     * 插入用户权益的日志
     * @param privilegeLog
     * @return
     */
    PrivilegeLog insert(PrivilegeLog privilegeLog);

    /**
     * 查询用户当月的权益日志
     * @param logType
     * @param userId
     * @return
     */
    List<PrivilegeLog> selectListMonth(String logType, String userId);

    /**
     * 查询用户当年的权益日志
     * @param logType
     * @param userId
     * @return
     */
    List<PrivilegeLog> selectListYear(String logType, String userId);

    /**
     * 查询用户权益日志（忽略时间）
     * @param logType
     * @param userId
     * @return
     */
    List<PrivilegeLog> selectList(String logType, String userId);
}
