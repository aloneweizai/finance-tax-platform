package com.abc12366.gateway.mapper.db2;

import com.abc12366.gateway.model.AdminLog;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-27 2:33 PM
 * @since 1.0.0
 */
public interface AdminLogRoMapper {
    List<AdminLog> selectList(AdminLog adminLog);
}
