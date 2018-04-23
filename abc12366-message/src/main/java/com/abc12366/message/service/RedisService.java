package com.abc12366.message.service;

import java.util.Set;

/**
 * Redis服务
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2017-04-19 11:20 AM
 * @since 1.0.0
 */
public interface RedisService {

    /**
     * 设置redis中的键值对
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, String value);

    /**
     * 查询redis中的键值对集合
     *
     * @param pattern 正则表达式
     * @return Set集合
     */
    Set selectList(String pattern);

    /**
     * 查询redis中的键值对
     *
     * @param key redis键
     * @return value值
     */
    String selectOne(String key);

    /**
     * 删除redis中的键值对
     *
     * @param key redis键
     */
    void delete(String key);

    /**
     * 情况redis数据库
     */
    void flushDb();
}
