package com.abc12366.uc.jrxt.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: ljun
 * Date: 11/5/13
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class BaseObject {

    /**
     * 日志
     */
    protected static Logger _log = LoggerFactory.getLogger(BaseObject.class);

    /**
     * TPDS系统参数
     */
    protected static Config config;

    /**
     * 默认构造函数
     */
    public BaseObject() {
        if (config == null) {
            config = new Config("config");
        }
    }

    static {
        try {
            // 读取配置文件
            config = new Config("config");
        } catch (Exception mye) {
            String msg = "读取配置文件异常";
            _log.error(msg,mye);
            mye.printStackTrace();
        }
    }

    /**
     * 获取配置参数值方法
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        if (config == null) {
            config = new Config("config");
        }

        return config.getValue(key);
    }

    public static void putHashTable(Hashtable table, String key, Object value) {
        if (null != value) {
            table.put(key, value);
        }
    }
}
