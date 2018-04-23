package com.abc12366.gateway.util;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.model.User;
import com.abc12366.gateway.model.bo.UCUserBO;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 工具类
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 9:44 AM
 * @since 1.0.0
 */
public class Utils {

    /**
     * 返回系统唯一UUUID
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(md5("12345678"));
    }

    /**
     * 返回Map形式的对象，参数必须为偶数个
     *
     * @param kvs 键值对
     * @return Map
     */
    public static Map kv(Object... kvs) {

        Map<Object, Object> map = new HashMap<>();
        map.put("code", 2000);
        try {
            map.put("message", Message.getValue(2000));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (kvs.length % 2 != 0) {
            throw new RuntimeException("Params must be key, value pairs.");
        }
        for (int i = 0; i < kvs.length; i += 2) {
            map.put(kvs[i], kvs[i + 1]);
        }
        return map;
    }


    /**
     * 返回枚举类型代码
     *
     * @param code int
     * @return BodyStatus
     */
    public static BodyStatus bodyStatus(int code) {
        BodyStatus body = new BodyStatus();
        body.setCode(String.valueOf(code));
        try {
            body.setMessage(Message.getValue(code));
        } catch (IOException e) {
            e.printStackTrace();
            body.setMessage(e.getMessage());
        }
        return body;
    }

    /**
     * 返回第三方错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static BodyStatus bodyStatus(int code, String message) {
        BodyStatus body = new BodyStatus();
        body.setCode(String.valueOf(code));
        body.setMessage(message);
        return body;
    }

    /**
     * 返回第三方错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static BodyStatus bodyStatus(String code, String message) {
        BodyStatus body = new BodyStatus();
        body.setCode(String.valueOf(code));
        body.setMessage(message);
        return body;
    }


    /**
     * md5字符串
     *
     * @param str 需要计算的字符串
     * @return String
     * @throws Exception Exception
     */
    public static String md5(String str) throws Exception {
        Assert.notNull(str, "MD5 string is not empty");
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] bs = digest.digest(str.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : bs) {
            int temp = b & 255;
            if (temp < 16) {
                hexString.append("0").append(Integer.toHexString(temp));
            } else {
                hexString.append(Integer.toHexString(temp));
            }
        }
        return hexString.toString();
    }

    /**
     * Base64 编码
     *
     * @param str 需要编码的字符串
     * @return String
     */
    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    /**
     * Base64 解码
     *
     * @param str 需要解码的字符串
     * @return String
     */
    public static String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    /**
     * 生成token
     * return String token
     */
    public static String token() throws Exception {
        return md5(uuid());
    }

    public static String token(String str) throws Exception {
        return md5(str);
    }

    /**
     * 生成盐值
     *
     * @return String Base64编码后的字符串
     */
    public static String salt() {
        double salt = Math.random();
        String saltValue = salt + "";
        return saltValue.substring(saltValue.indexOf("0.") + 2, 8);
    }

    public static String getAddr(HttpServletRequest request) {
        String addr = request.getRemoteAddr();
        if (!StringUtils.isEmpty(request.getHeader(Constant.CLIENT_IP))) {
            addr = request.getHeader(Constant.CLIENT_IP);
        }
        return addr;
    }

    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (!StringUtils.isEmpty(request.getHeader(Constant.CLIENT_USER_AGENT))) {
            userAgent = request.getHeader(Constant.CLIENT_USER_AGENT);
        }
        return userAgent;
    }

    public static String getUserId(HttpServletRequest request) {
        String userId = (String) request.getAttribute(Constant.USER_ID);
        if (userId == null || userId.equals("")) {
            throw new ServiceException(4193);
        }
        return userId;
    }

    /**
     * 在HttpServletRequest记录userId
     *
     * @param userId 用户ID
     */
    public static void setUserId(String userId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        request.setAttribute(Constant.USER_ID, userId);
    }

    public static String getAdminId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String adminId = (String) request.getAttribute(Constant.ADMIN_ID);
        if (StringUtils.isEmpty(adminId)) {
            throw new ServiceException(4130);
        }
        return adminId;
    }

    public static String getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String userId = (String) request.getAttribute(Constant.USER_ID);
        if (StringUtils.isEmpty(userId)) {
            throw new ServiceException(4193);
        }
        return userId;
    }

    public static String getAppId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String appId = (String) request.getAttribute(Constant.APP_ID);
        if (StringUtils.isEmpty(appId)) {
            throw new ServiceException(4044);
        }
        return appId;
    }

    public static UCUserBO getUserInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return (UCUserBO) request.getAttribute(Constant.USER_INFO);
    }

    public static User getAdminInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        User user = (User) request.getAttribute(Constant.ADMIN_USER);
        if (StringUtils.isEmpty(user)) {
            throw new ServiceException(4130);
        }
        return user;
    }

    public static ServletRegistrationBean getServletRegistrationBean() throws IOException {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        String mappings = PropertiesUtil.getValue("druid.url.mappings");
        String allow = PropertiesUtil.getValue("druid.ip.allow");
        String deny = PropertiesUtil.getValue("druid.ip.deny");
        String username = PropertiesUtil.getValue("druid.login.username");
        String password = PropertiesUtil.getValue("druid.login.password");
        if (!StringUtils.isEmpty(mappings)) {
            reg.addUrlMappings(mappings);
        }
        if (!StringUtils.isEmpty(allow)) {
            reg.addInitParameter("allow", allow);
        }
        if (!StringUtils.isEmpty(deny)) {
            reg.addInitParameter("deny", deny);
        }
        if (!StringUtils.isEmpty(username)) {
            reg.addInitParameter("loginUsername", username);
        }
        if (!StringUtils.isEmpty(password)) {
            reg.addInitParameter("loginPassword", password);
        }
        return reg;
    }

    public static FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    //获取两位随机数
    public static double getTwoDouble(double value){
        BigDecimal b = new BigDecimal(value);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
