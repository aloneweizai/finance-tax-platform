package com.abc12366.message.service.impl;

import com.abc12366.message.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * RedisService实现类
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2017-04-19 11:21 AM
 * @since 1.0.0
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * opsForXXX & boundXxxOps的区别：
     * 前者获取一个的operator，但是没有指定操作的对象（key），可以在一个连接（事务）内操作多个key以及对应的value；
     * 后者获取一个指定操作对象（key）的operator，在一个连接（事务）内只能操作这个key对应的value。
     */
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

//    private BoundValueOperations<String, String> boundValueOperations;

//    @Resource(name = "redisTemplate")
//    private ListOperations<String, String> listOperations;

    @Override
    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    @Override
    public Set selectList(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        LOGGER.info("{}", keys.size());
        return keys;
    }

    @Override
    public String selectOne(String key) {
        return valueOperations.get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void flushDb() {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return "ok";
        });
    }
}
