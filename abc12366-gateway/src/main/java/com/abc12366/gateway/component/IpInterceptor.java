package com.abc12366.gateway.component;

import com.abc12366.gateway.model.ApiLog;
import com.abc12366.gateway.model.Blacklist;
import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.model.bo.BlacklistBO;
import com.abc12366.gateway.service.ApiLogService;
import com.abc12366.gateway.service.BlacklistService;
import com.abc12366.gateway.service.IpSettingService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.PropertiesUtil;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * IP拦截器
 *
 * @since 1.0.0
 */
public class IpInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpInterceptor.class);

    @Autowired
    private ApiLogService apiLogService;

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private IpSettingService ipSettingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 查询IP设定
        long threshold = Long.valueOf(PropertiesUtil.getValue("gateway.blacklist.ip.threshold"));
        long setTime = Long.valueOf(PropertiesUtil.getValue("gateway.blacklist.ip.set.time"));
        long lockingTime = Long.valueOf(PropertiesUtil.getValue("gateway.blacklist.ip.locking.time"));

        //根据IP设定查询日志表
        ApiLog apiLog = new ApiLog();
        String ip = Utils.getAddr(request);
        apiLog.setIp(ip);
        apiLog.setYyyyMMdd(DateUtils.getDataString());
        long time = System.currentTimeMillis();
        apiLog.setStartTime(time - (setTime * 1000));
        apiLog.setEndTime(time);

        int count = apiLogService.selectApiLogCount(apiLog);
        if (count > threshold) {
            LOGGER.warn("此IP访问超过每分钟阀值：{}", count);
            BodyStatus bodyStatus = Utils.bodyStatus(4034);
            response.setStatus(401);
            response.getWriter().write(JSON.toJSONString(bodyStatus));
            response.getWriter().flush();
            response.getWriter().close();
            LOGGER.warn("URI:{}, IP:{}, {}", request.getRequestURI(), request.getRemoteAddr(), bodyStatus);
            //加入黑名单表
            BlacklistBO bo = new BlacklistBO();
            bo.setIp(ip);
            bo.setUserId((String) request.getAttribute(Constant.USER_ID));
            Date date = new Date();
            bo.setCreateTime(date);
            bo.setStartTime(date);
            bo.setEndTime(DateUtils.getLongToDate(System.currentTimeMillis() + lockingTime));
            bo.setStatus(true);
            bo.setRemark("系统自动锁定");
            blacklistService.insert(bo);
            return false;
        } else {
            //查询黑名单是否有这个IP信息，并且是锁定状态的，有就解锁
            Blacklist black = blacklistService.selectByIp(ip);
            if (black != null) {
                BlacklistBO listBO = new BlacklistBO();
                listBO.setId(black.getId());
                listBO.setStatus(true);
                blacklistService.update(listBO);
            }
        }
       /* if (!ipSettingService.isAuthentication(userToken)) {
            BodyStatus bodyStatus = Utils.bodyStatus(4198);
            response.setStatus(401);
            response.getWriter().write(JSON.toJSONString(bodyStatus));
            response.getWriter().flush();
            response.getWriter().close();
            LOGGER.warn("URI:{}, IP:{}, {}", request.getRequestURI(), request.getRemoteAddr(), bodyStatus);
            return false;
        }*/

        return true;
    }
}
