package com.abc12366.bangbang.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis映射文件扫描配置
 * <p>
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 14:55
 */
@Configuration
@AutoConfigureAfter(DatabaseConfig.class)
public class MapperScannerConfig {

    @Bean
    public MapperScannerConfigurer db1MapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // 设置自动扫描包, 该包下的Mapper(Dao)将会被mybatis自动注册, 不用写实现类
        configurer.setBasePackage("com.abc12366.bangbang.mapper.db1");
        configurer.setSqlSessionFactoryBeanName("db1SqlSessionFactory");
        return configurer;
    }

    @Bean
    public MapperScannerConfigurer db2MapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // 设置自动扫描包, 该包下的Mapper(Dao)将会被mybatis自动注册, 不用写实现类
        configurer.setBasePackage("com.abc12366.bangbang.mapper.db2");
        configurer.setSqlSessionFactoryBeanName("db2SqlSessionFactory");
        return configurer;
    }

    @Bean
    public MapperScannerConfigurer gw1MapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // 设置自动扫描包, 该包下的Mapper(Dao)将会被mybatis自动注册, 不用写实现类
        configurer.setBasePackage("com.abc12366.gateway.mapper.db1");
        configurer.setSqlSessionFactoryBeanName("gw1SqlSessionFactory");
        return configurer;
    }

    @Bean
    public MapperScannerConfigurer gw2MapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // 设置自动扫描包, 该包下的Mapper(Dao)将会被mybatis自动注册, 不用写实现类
        configurer.setBasePackage("com.abc12366.gateway.mapper.db2");
        configurer.setSqlSessionFactoryBeanName("gw2SqlSessionFactory");
        return configurer;
    }
}
