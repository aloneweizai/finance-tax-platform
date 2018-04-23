package com.abc12366.bangbang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Application程序入口
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 14:55
 */
@SpringBootApplication(scanBasePackages = "com.abc12366.bangbang, com.abc12366.gateway")
@PropertySource("classpath:application.properties")
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setThreadNamePrefix("bb-");
        executor.initialize();
        return executor;
    }
}

