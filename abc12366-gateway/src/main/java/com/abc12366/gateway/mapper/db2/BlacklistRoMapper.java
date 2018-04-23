package com.abc12366.gateway.mapper.db2;


import com.abc12366.gateway.model.Blacklist;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-06 2:07 PM
 * @since 1.0.0
 */
public interface BlacklistRoMapper {
    Blacklist selectOne(Blacklist blacklist);

    List<Blacklist> isBlacklist(Blacklist blackList);

    List<Blacklist> selectList();

    Blacklist selectByIp(String ip);
}
