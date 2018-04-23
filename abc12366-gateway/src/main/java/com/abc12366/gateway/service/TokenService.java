package com.abc12366.gateway.service;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-21
 * Time: 9:49
 */
public interface TokenService {

    /**
     * adminToken，userToken校验
     *
     * @param adminToken 管理员token
     * @param userToken  用户token
     * @param request    HttpServletRequest
     * @return boolean
     */
    boolean isAuthentication(String adminToken, String userToken, HttpServletRequest request);
}
