package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * redis控制器
 *
 * @author ljun51@outlook.com
 * @version V1.0
 * @date 2017-12-13
 */
@RestController
@RequestMapping(path = "/redis", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;

    /**
     * 设置redis键值对
     *
     * @return void
     */
    @PutMapping
    public ResponseEntity set() {
        redisService.set("foo", "bar");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 根据redis正则表达式查询key集合 如：foo*
     *
     * @param pattern redis正则表达式
     * @return Set key集合
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam String pattern) {
        LOGGER.info("{}", pattern);
        return ResponseEntity.ok(Utils.kv("data", redisService.selectList(pattern)));
    }

    /**
     * 根据key值查看value
     *
     * @param key redis键
     * @return redis值
     */
    @GetMapping("/{key}")
    public ResponseEntity selectOne(@PathVariable("key") String key) {
        LOGGER.info("{}", key);
        return ResponseEntity.ok(Utils.kv("data", redisService.selectOne(key)));
    }

    /**
     * 删除redis键值对
     *
     * @param key redis键
     * @return void
     */
    @DeleteMapping("/{key}")
    public ResponseEntity delete(@PathVariable("key") String key) {
        LOGGER.info("{}", key);
        redisService.delete(key);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 清空redis数据库，请慎用
     *
     * @return void
     */
    @PostMapping("/flushdb")
    public ResponseEntity flushDb() {
        redisService.flushDb();
        return ResponseEntity.ok(Utils.kv());
    }
}
