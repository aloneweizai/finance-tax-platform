package com.abc12366.gateway.component;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.service.AppService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IO拦截器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-23 9:31 AM
 * @since 1.0.0
 */
public class AppInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppInterceptor.class);

    @Autowired
    private AppService appService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 3.App验证
        String accessToken = request.getHeader(Constant.APP_TOKEN_HEAD);
        response.setContentType("application/json;charset=UTF-8");
        if (StringUtils.isEmpty(accessToken)) {
            BodyStatus bodyStatus = Utils.bodyStatus(4000);
            response.setStatus(200);
            response.getWriter().write(JSON.toJSONString(bodyStatus));
            response.getWriter().flush();
            response.getWriter().close();
            LOGGER.warn("URI:{}, IP:{}, {}", request.getRequestURI(), request.getRemoteAddr(), bodyStatus);
            return false;
        }
        // 4.App授权
        if (!appService.isAuthentization(request)) {
            BodyStatus bodyStatus = Utils.bodyStatus(4002);
            response.setStatus(200);
            response.getWriter().write(JSON.toJSONString(bodyStatus));
            response.getWriter().flush();
            response.getWriter().close();
            LOGGER.warn("URI:{}, IP:{}, {}", request.getRequestURI(), request.getRemoteAddr(), bodyStatus);
            return false;
        }

        return true;
    }
}
