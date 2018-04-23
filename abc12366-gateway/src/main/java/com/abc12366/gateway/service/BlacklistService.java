package com.abc12366.gateway.service;

import com.abc12366.gateway.model.Blacklist;
import com.abc12366.gateway.model.bo.BlacklistBO;

import java.util.List;

/**
 * 黑名单服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-06 1:46 PM
 * @since 1.0.0
 */
public interface BlacklistService {

    /**
     * 是否为黑名单
     *
     * @param addr IP地址
     * @return true: 是名单，false:不是黑名单
     */
    boolean isBlacklist(String addr);

    /**
     * 查询黑名单列表
     *
     * @return List<Blacklist>
     */
    List<Blacklist> selectList();

    /**
     * 查看黑名单
     *
     * @param id PK
     * @return Blacklist
     */
    Blacklist selectOne(String id);

    /**
     * 新增黑名单
     *
     * @param bo BlacklistBO
     * @return Blacklist
     */
    Blacklist insert(BlacklistBO bo);

    /**
     * 更新黑名单
     *
     * @param bo BlacklistBO
     * @return Blacklist
     */
    Blacklist update(BlacklistBO bo);

    /**
     * 删除黑名单
     *
     * @param id PK
     */
    void delete(String id);

    /**
     * 根据IP删除黑名单
     *
     * @param ip IP地址
     * @return Blacklist
     */
    Blacklist selectByIp(String ip);
}
