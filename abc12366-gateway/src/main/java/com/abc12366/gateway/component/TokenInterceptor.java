package com.abc12366.gateway.component;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.service.TokenService;
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
 * Token拦截器
 *
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-05-25
 * Time: 11:34
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        String adminToken = request.getHeader(Constant.ADMIN_TOKEN_HEAD);
        String userToken = request.getHeader(Constant.USER_TOKEN_HEAD);

        response.setContentType("application/json;charset=UTF-8");

        if (StringUtils.isEmpty(adminToken) && StringUtils.isEmpty(userToken)) {
            BodyStatus bodyStatus = Utils.bodyStatus(4195);
            response.setStatus(200);
            response.getWriter().write(JSON.toJSONString(bodyStatus));
            response.getWriter().flush();
            response.getWriter().close();
            LOGGER.warn("URI:{}, IP:{}, {}", request.getRequestURI(), request.getRemoteAddr(), bodyStatus);
            return false;
        }
       if (!tokenService.isAuthentication(adminToken, userToken, request)) {
            BodyStatus bodyStatus = Utils.bodyStatus(4194);
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