package com.abc12366.gateway.mapper.db1;

import com.abc12366.gateway.model.AppSetting;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-27 4:47 PM
 * @since 1.0.0
 */
public interface AppSettingMapper {
    int update(AppSetting appSetting);

    int insert(AppSetting appSetting);

    void delete(String id);

    int deleteByAppId(String appId);

    void batchInsert(List<AppSetting> list);
}
