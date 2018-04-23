package com.abc12366.gateway.mapper.db2;

import com.abc12366.gateway.model.AppSetting;
import com.abc12366.gateway.model.bo.AppSettingBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-27 2:56 PM
 * @since 1.0.0
 */
public interface AppSettingRoMapper {
    List<AppSettingBO> selectList(AppSettingBO appId);

    AppSetting selectOne(AppSetting appSetting);

    AppSettingBO selectByAppId(AppSettingBO appSettingBO);
}
