package com.abc12366.gateway.util;

import java.io.IOException;

public class PropertiesUtil {

    static com.abc12366.gateway.util.Properties properties;
    private static String GATEWAY_PROPERTIES = "gateway.properties";

    static {
        try {
            properties = new com.abc12366.gateway.util.Properties(GATEWAY_PROPERTIES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置参数值方法
     *
     * @param key
     * @return String
     */
    public static String getValue(String key) throws IOException {
        if (properties == null) {
            properties = new com.abc12366.gateway.util.Properties(GATEWAY_PROPERTIES);
        }

        return properties.getValue(String.valueOf(key));
    }
} 