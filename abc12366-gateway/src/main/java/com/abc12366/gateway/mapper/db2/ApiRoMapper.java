package com.abc12366.gateway.mapper.db2;


import com.abc12366.gateway.model.Api;
import com.abc12366.gateway.model.bo.ApiBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-06 8:53 AM
 * @since 1.0.0
 */
public interface ApiRoMapper {
    ApiBO selectOne(Api api);

    List<ApiBO> selectList(Api api);

    List<ApiBO> selectBySettingList(String appId);

    ApiBO selectByUriAndVersion(Api temp);
}
