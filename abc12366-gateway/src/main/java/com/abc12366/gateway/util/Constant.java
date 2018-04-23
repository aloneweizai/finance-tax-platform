package com.abc12366.gateway.util;

/**
 * 系统没有归类的常量定义
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2017-03-28 5:12 PM
 * @since 1.0.0
 */
public class Constant {

    /**
     * 接口版本常量
     */
    public final static String VERSION_HEAD = "Version";
    public final static String VERSION_1 = "1";
    public final static String VERSION_2 = "2";

    /**
     * 公司应用名称包含abc
     */
    public static final String ABC = "abc";

    /**
     * App验证头
     */
    public final static String APP_TOKEN_HEAD = "Access-Token";

    /**
     * 用户验证头
     */
    public final static String USER_TOKEN_HEAD = "User-Token";

    /**
     * Admin用户验证头
     */
    public final static String ADMIN_TOKEN_HEAD = "admin-token";

    /**
     * 应用ID
     */
    public final static String APP_ID = "App-Id";
    public static final String APP_NAME = "App-Name";
    public final static String USER_ID = "User-Id";
    public final static String USER_INFO = "User-Info";
    public final static String ADMIN_ID = "Admin-Id";
    public final static String ADMIN_USER = "Admin-User";

    /**
     * 客户端每次接口访问，在请求头中传入用户IP、用户代理类型，以便服务端记录
     */
    public final static String CLIENT_IP = "Client-Ip";

    /**
     * 客户端用户代理
     */
    public final static String CLIENT_USER_AGENT = "Client-User-Agent";

    /**
     * App默认有效期：3年
     */
    public final static int APP_VALID_YEARS = 3;

    /**
     * App token有效期：2小时
     */
    public final static long APP_TOKEN_VALID_SECONDS = 3600 * 2;

    /**
     * 用户token有效期：3600*2秒
     */
    public final static int USER_TOKEN_VALID_SECONDS = 3600 * 2;

    /**
     * Admin用户token有效期：3600*2*1000毫秒
     */
    public final static long ADMIN_TOKEN_VALID_SECONDS = 3600 * 2 * 1000;

    /**
     * 分页-当前页
     */
    public final static String pageNum = "1";

    /**
     * 分页-每页大小
     */
    public final static String pageSize = "10";

    /**
     * 时间-当前日期
     */
    public final static String startTime = DateUtils.getTodaySting();
    public final static String endTime = DateUtils.getTodaySting();

    /**
     * 系统设置默认密码
     */
    public final static String DEFAULT_PASSWORD = "abc@12366";

    /**
     * 用户重置密码的默认密码
     */
    public final static String USER_DEFAULT_PASSWORD = "abc12366";


    /**
     * 客户端用户代理
     */
    public final static String IP_QUERY_URL = "http://ip.taobao.com/service/getIpInfo.php?ip={ip}";

    /**
     * 获取微信二维码图片地址
     */
    public final static String WEIXIN_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    public final static String WEIXIN_LOTTERY = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID"
            + "&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    /**
     * 用户初始会员等级(普通会员)
     */
    public final static String USER_ORIGINAL_LEVEL = "VIP0";

    /**
     * 银卡
     */
    public final static String USER_VIP_LEVEL_1 = "VIP1";

    /**
     * 金卡
     */
    public final static String USER_VIP_LEVEL_2 = "VIP2";

    /**
     * 钻石
     */
    public final static String USER_VIP_LEVEL_3 = "VIP3";

    /**
     * 超级
     */
    public final static String USER_VIP_LEVEL_4 = "VIP4";

    /**
     * 订单自动取消时间：小时
     */
    public final static Integer ORDER_CANCEL_TIME = 2;

    /**
     * 订单退货期限，单位：天
     */
    public final static Integer ORDER_BACK_DAYS = 7;

    /**
     * 订单换货期限，单位：天
     */
    public final static Integer ORDER_EXCHANGE_DAYS = 15;

    /**
     * 订单换货自动收货期限，单位：天
     */
    public final static Integer ORDER_EXCHANGE_RECEIPT_DAYS = 15;

    /**
     * 订单自动确认收货，单位：天
     */
    public final static Integer ORDER_RECEIPT_DAYS = 15;

    /**
     * 用户连续输错密码阈值
     */
    public final static int USER_CONTINUE_PASSWORD_WRONG_MAX = 5;

    /**
     * 用户连续输错密码超过阈值锁定时间，单位：毫秒
     */
    public final static int LOCK_TIME = 10 * 60 * 1000;

    /**
     * 电子申报系统信息查询数量
     */
    public static final String DZSBQNUM = "200";

    /**
     * 电子申报绑定时间，单位：月
     */
    public final static Integer DZSB_BIND_DATE = 7;

}
