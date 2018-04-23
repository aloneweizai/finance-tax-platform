package com.abc12366.bangbang.config;

import com.abc12366.gateway.util.Utils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Druid配置
 * <p>
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 14:55
 */
@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean druidServlet() throws IOException {
        return Utils.getServletRegistrationBean();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        return Utils.getFilterRegistrationBean();
    }
}
