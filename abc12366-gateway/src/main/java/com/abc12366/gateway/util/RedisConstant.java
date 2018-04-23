package com.abc12366.gateway.util;

/**
 * 专门存储redis各种时间限制
 *
 * @author zhushuai 2017-12-12
 */
public class RedisConstant {

    /**
     * redis失效时间：1小时
     */
    public static final long HOUR_1 = 1;

    /**
     * redis失效时间：1天
     */
    public static final long DAY_1 = 1;

    /**
     * redis失效时间：30天
     */
    public static final long DAY_30 = 30;

    /**
     * 电子申报纳税人一天内登录次数统计
     */
    public static final String DZSBNSR_LOGIN_TIMES_DAY= "dzsbnsr_login_times_day";
    /**
     * 纳税人一个月内登录次数统计
     */
    public static final String NSR_LOGIN_TIMES_MONTH= "nsr_login_times_month";
}
