package com.abc12366.gateway.service;

import com.abc12366.gateway.model.AdminLog;
import com.abc12366.gateway.model.bo.AdminLogBO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 管理员操作日志
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-27 2:18 PM
 * @since 1.0.0
 */
public interface AdminLogService {

    // 新增操作员日志
    CompletableFuture<AdminLog> insert(AdminLogBO bo);

    // 查询
    List<AdminLog> selectList(int page, int size, AdminLog adminLog);
}
