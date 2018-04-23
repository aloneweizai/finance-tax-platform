package com.abc12366.gateway.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 获取应用上下文
 */
@Component
public class SpringCtxHolder implements ApplicationContextAware {

    /**
     * 应用上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * 环境配置
     */
    private static Environment env;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringCtxHolder.applicationContext = applicationContext;
        env = applicationContext.getEnvironment();
    }

    public static Environment getEnv() {
        return env;
    }

    public static void setEnv(Environment env) {
        SpringCtxHolder.env = env;
    }

    public static String getProperty(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        return env.getProperty(key);
    }
}
