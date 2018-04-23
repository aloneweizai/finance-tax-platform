package com.abc12366.gateway.service;

import com.abc12366.gateway.model.AppSetting;
import com.abc12366.gateway.model.bo.AppSettingBO;

import java.util.List;

/**
 * 应用设置服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-27 4:04 PM
 * @since 1.0.0
 */
public interface AppSettingService {

    /**
     * 应用设置列表查询
     *
     * @param appSettingBO AppSettingBO
     * @return <AppSettingBO>
     */
    List<AppSettingBO> selectList(AppSettingBO appSettingBO);

    /**
     * 更新应用设置
     *
     * @param appSettingBO AppSettingBO
     * @return AppSetting
     */
    AppSetting update(AppSettingBO appSettingBO);

    /**
     * 新增应用设置
     *
     * @param appSettingBO AppSettingBO
     * @return AppSetting
     */
    AppSetting insert(AppSettingBO appSettingBO);

    /**
     * 删除应用设置
     *
     * @param appId 应用ID
     * @param id    PK
     */
    void delete(String appId, String id);

    /**
     * 查询单个应用设置
     *
     * @param appId 应用ID
     * @param id    PK
     * @return AppSetting
     */
    AppSetting selectOne(String appId, String id);

    /**
     * 批量插入应用设置
     *
     * @param appId            应用ID
     * @param appSettingBOList List<AppSettingBO>
     * @return List<AppSetting>
     */
    List<AppSetting> insertList(String appId, List<AppSettingBO> appSettingBOList);
}
