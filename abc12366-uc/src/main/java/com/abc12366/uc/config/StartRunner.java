package com.abc12366.uc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动系统就执行一次的任务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-18 9:45 AM
 * @since 1.0.0
 */
@Component
public class StartRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartRunner.class);

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("启动就执行的任务放在这里。。。。。。。。。。。");
    }
}
