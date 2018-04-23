package com.abc12366.gateway.mapper.db2;

import com.abc12366.gateway.model.App;
import com.abc12366.gateway.model.bo.AppBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-24 10:38 AM
 * @since 1.0.0
 */
public interface AppRoMapper {

    App selectOne(App app);

    App selectById(String id);

    List<AppBO> selectList(AppBO appGeneralBO);

    List<AppBO> selectBySettingIdList(String id);

    App selectByName(String name);
}
