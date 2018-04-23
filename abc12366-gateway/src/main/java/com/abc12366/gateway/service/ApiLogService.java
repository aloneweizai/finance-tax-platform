package com.abc12366.gateway.service;


import com.abc12366.gateway.model.ApiLog;

import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 11:48 AM
 * @since 1.0.0
 */
public interface ApiLogService {

    /**
     * 新增接口日志
     *
     * @param log 日志对象
     */
    void insert(ApiLog log);

    /**
     * 查询接口访问次数
     *
     * @param apiLog 日志对象
     * @return int
     */
    int selectApiLogCount(ApiLog apiLog);

    /**
     * 根据日期查询日志列表
     *
     * @param map createTime:查询日期
     * @return List<ApiLog>
     */
    List<ApiLog> selectApiList(Map<String, Object> map);

    /**
     * 根据日期、appId查询日志列表
     *
     * @param map createTime:查询日期，appId:appId
     * @return List<ApiLog>
     */
    List<ApiLog> selectApiListByAppId(Map<String, Object> map);

    /**
     * 接口日志-根据uri,version,method列表查询
     * @param map
     * @return
     */
    List<ApiLog> selectApiListByApiId(Map<String, Object> map);
}
