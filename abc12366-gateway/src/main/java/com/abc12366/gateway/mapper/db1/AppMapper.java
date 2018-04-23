package com.abc12366.gateway.mapper.db1;


import com.abc12366.gateway.model.App;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 1:26 PM
 * @since 1.0.0
 */
public interface AppMapper {

    App selectOne(App app);

    int insert(App newApp);

    int update(App app);
}
