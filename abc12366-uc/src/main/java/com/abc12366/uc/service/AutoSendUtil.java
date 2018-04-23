package com.abc12366.uc.service;

import java.util.Map;

/**
 * 定时任务请求，工具类
 * @author lizhongwei
 */
public interface AutoSendUtil {


    /**
     * 企业使用情况统计
     * @param map
     */
    void autoRecordCompany(Map<String, Object> map);


    /**
     * 软件用户行为统计
     * @param map
     */
    void autoRecordStatis(Map<String, Object> map);
}
