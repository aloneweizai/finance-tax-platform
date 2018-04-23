package com.abc12366.uc.service;

/**
 * IP地址归属记录服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-22 10:33 AM
 * @since 1.0.0
 */
public interface IpService {

    /**
     * 更新IP地址库和用户地址信息
     *
     * @param ip     IP地址
     * @param userId 用户ID
     */
    void merge(String ip, String userId);
}
