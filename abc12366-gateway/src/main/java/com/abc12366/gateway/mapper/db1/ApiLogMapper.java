package com.abc12366.gateway.mapper.db1;

import com.abc12366.gateway.model.ApiLog;
import com.abc12366.gateway.model.bo.TableBO;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 10:17 AM
 * @since 1.0.0
 */
public interface ApiLogMapper {

    void insert(ApiLog log);

    void create(TableBO tableBO);
}
