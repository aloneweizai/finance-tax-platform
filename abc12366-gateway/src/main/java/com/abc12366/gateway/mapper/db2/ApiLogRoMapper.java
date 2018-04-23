package com.abc12366.gateway.mapper.db2;

import com.abc12366.gateway.model.ApiLog;

import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-28 3:47 PM
 * @since 1.0.0
 */
public interface ApiLogRoMapper {
    List<ApiLog> selectList();

    List<ApiLog> selectListPage(ApiLog apiLog);

    Integer selectApiLogCount(ApiLog apiLog);


    int selectByUri(ApiLog apiLog);

    List<ApiLog> selectApiList(Map<String, Object> map);

    List<ApiLog> selectApiListByAppId(Map<String, Object> map);

    List<ApiLog> selectApiListByApiId(Map<String, Object> map);
}
