package com.abc12366.gateway.service;

import com.abc12366.gateway.model.IpSetting;

/**
 * IP控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-20 8:00 PM
 * @since 1.0.0
 */
public interface IpSettingService {

    /**
     * 查看IP设置
     *
     * @return IpSetting
     */
    IpSetting selectOne();

    /**
     * 更新IP设置
     *
     * @param ipSetting IpSetting
     * @return IpSetting
     */
    IpSetting update(IpSetting ipSetting);
}
