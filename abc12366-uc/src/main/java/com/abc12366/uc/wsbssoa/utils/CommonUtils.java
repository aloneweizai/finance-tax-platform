package com.abc12366.uc.wsbssoa.utils;

import org.springframework.stereotype.Component;

/**
 * 接入系统工具类
 *
 * @author lijun
 * @version v1.0
 * @created 2014-3-7
 */
@Component("commonUtils2")
public class CommonUtils {

    public static boolean nullOrBlank(String param) {
        return (param == null || param.trim().equals("")) ? true : false;
    }
}
