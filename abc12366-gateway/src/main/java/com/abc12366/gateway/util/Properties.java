package com.abc12366.gateway.util;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 读取Properties文件
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-06 11:38 AM
 * @since 1.0.0
 */
public class Properties {

    private java.util.Properties properties;

    private String filePath;

    public Properties(String filePath) {
        this.filePath = filePath;
    }

    public String getValue(String code) throws IOException {
        if (properties == null) {
            properties = new java.util.Properties();
        }
        InputStreamReader isr = new InputStreamReader(Properties.class.getClassLoader().getResourceAsStream(filePath),
                "UTF-8");
        properties.load(isr);
        return properties.getProperty(code);
    }
}
