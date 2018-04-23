package com.abc12366.gateway.service;

import com.abc12366.gateway.model.Api;
import com.abc12366.gateway.model.bo.ApiBO;

import java.util.List;

/**
 * 接口列表服务接口
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-27 10:59 AM
 * @since 1.0.0
 */
public interface ApiService {

    /**
     * 查询接口列表
     *
     * @param api 接口对象
     * @return List
     */
    List<ApiBO> selectList(Api api);

    /**
     * 新增服务接口
     *
     * @param apiBO 接口对象
     * @return Api
     */
    Api insert(ApiBO apiBO);

    /**
     * 更新服务接口
     *
     * @param apiUpdateBO 接口对象
     * @return Api
     */
    Api update(ApiBO apiUpdateBO);

    /**
     * 删除服务接口
     * @param id PK
     */
    void delete(String id);

    /**
     * 查看服务接口
     *
     * @param id PK
     * @return ApiBO
     */
    ApiBO selectOne(String id);

    /**
     * 通过AppId查找未授权的API
     * @param appId 应用ID
     * @return List
     */
    List<ApiBO> selectBySettingList(String appId);
}
