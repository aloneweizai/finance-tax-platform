package com.abc12366.gateway.service;

import com.abc12366.gateway.mapper.db1.ApiLogMapper;
import com.abc12366.gateway.mapper.db2.ApiLogRoMapper;
import com.abc12366.gateway.model.ApiLog;
import com.abc12366.gateway.model.bo.TableBO;
import com.abc12366.gateway.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 11:48 AM
 * @since 1.0.0
 */
@Service
public class ApiLogServiceImpl implements ApiLogService {

    @Autowired
    private ApiLogMapper apiLogMapper;

    @Autowired
    private ApiLogRoMapper apiLogRoMapper;

    @Transactional(value = "gw1TxManager", rollbackFor = SQLException.class)
    @Override
    public void insert(ApiLog log) {
        TableBO tableBO = new TableBO();
        tableBO.setYyyyMMdd(log.getYyyyMMdd());
        apiLogMapper.create(tableBO);

        apiLogMapper.insert(log);
    }

    @Override
    public int selectApiLogCount(ApiLog apiLog) {
        // 查询之前判断表是否存在
        TableBO tableBO = new TableBO();
        tableBO.setYyyyMMdd(DateUtils.getDataString());
        apiLogMapper.create(tableBO);

        return apiLogRoMapper.selectApiLogCount(apiLog);
    }

    @Override
    public List<ApiLog> selectApiList(Map<String, Object> map) {
        return apiLogRoMapper.selectApiList(map);
    }

    @Override
    public List<ApiLog> selectApiListByAppId(Map<String, Object> map) {
        return apiLogRoMapper.selectApiListByAppId(map);
    }

    @Override
    public List<ApiLog> selectApiListByApiId(Map<String, Object> map) {
        return apiLogRoMapper.selectApiListByApiId(map);
    }
}
