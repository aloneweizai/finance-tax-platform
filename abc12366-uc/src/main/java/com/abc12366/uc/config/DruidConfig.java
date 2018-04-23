package com.abc12366.uc.config;

import com.abc12366.gateway.util.Utils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Druid配置
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-29 4:04 PM
 * @since 1.0.0
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
