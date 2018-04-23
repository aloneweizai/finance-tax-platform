package com.abc12366.gateway.util;

import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-03
 * Time: 14:49
 */
@Component
public class RestTemplateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);

    @Autowired
    private RestTemplate restTemplate;

    public static boolean isExchangeSuccessful(ResponseEntity response) {
        if (response != null && response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            return true;
        }
        return false;
    }

    public String send(String url, HttpMethod method, HttpServletRequest request, Object... uriVariables) {
        //请求头设置
        HttpHeaders httpHeaders = getHeaders(request);

        HttpEntity requestEntity = new HttpEntity(null, httpHeaders);
        LOGGER.info("Request: {}, {}", url, requestEntity);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, String.class, uriVariables);
        } catch (RestClientException e) {
            LOGGER.error("{}", e);
            throw new ServiceException("0000", "调用接口异常，地址：" + url);
        }
        LOGGER.info("Response: {}, {}", url, responseEntity);
        return responseEntity != null ? responseEntity.getBody() : null;
    }

    public String exchange(String url, HttpMethod method, Object o, HttpServletRequest request) {
        //请求头设置
        HttpHeaders httpHeaders = getHeaders(request);

        HttpEntity requestEntity = new HttpEntity(o, httpHeaders);
        LOGGER.info("Request: {}, {}", url, requestEntity);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
        } catch (RestClientException e) {
            throw new ServiceException("0000", "调用接口异常，地址：" + url);
        }
        LOGGER.info("Response: {}, {}", url, responseEntity);
        return responseEntity != null ? responseEntity.getBody() : null;
    }

    public HttpHeaders getHeaders(HttpServletRequest request){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!StringUtils.isEmpty(request.getHeader(Constant.APP_TOKEN_HEAD))) {
            httpHeaders.add(Constant.APP_TOKEN_HEAD, request.getHeader(Constant.APP_TOKEN_HEAD));
        }
        if (!StringUtils.isEmpty(request.getHeader(Constant.ADMIN_TOKEN_HEAD))) {
            httpHeaders.add(Constant.ADMIN_TOKEN_HEAD, request.getHeader(Constant.ADMIN_TOKEN_HEAD));
        }
        if (!StringUtils.isEmpty(request.getHeader(Constant.USER_TOKEN_HEAD))) {
            httpHeaders.add(Constant.USER_TOKEN_HEAD, request.getHeader(Constant.USER_TOKEN_HEAD));
        }
        if (!StringUtils.isEmpty(request.getHeader(Constant.VERSION_HEAD))) {
            httpHeaders.add(Constant.VERSION_HEAD, request.getHeader(Constant.VERSION_HEAD));
        }
        return httpHeaders;
    }
}
