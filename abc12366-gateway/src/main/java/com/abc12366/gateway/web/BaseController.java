package com.abc12366.gateway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 提供访问外部接口的API
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-20 8:00 PM
 * @since 1.0.0
 */
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private RestTemplate restTemplate;

    public BaseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 对请求参数为Map的exchange方法的封装，可以参考对应的方法RestTemplate.exchange
     *
     * @param url           相对url地址
     * @param method        HttpMethod方法类型
     * @param requestEntity 包含请求头(HttpHeaders)或(和)请求体(Object)的实体
     * @param responseType  响应类型，通常为Object.class
     * @param uriVariables  uri中的参数占位符
     * @param <T>           范型T
     * @return ResponseEntity
     */
    public <T> ResponseEntity<T> exchange(String url,
                                          HttpMethod method,
                                          HttpEntity<?> requestEntity,
                                          Class<T> responseType,
                                          Map<String, ?> uriVariables) {
        LOGGER.info("RestClient: {}", url);

        ResponseEntity<T> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
        } catch (RestClientException e) {
            e.printStackTrace();
            LOGGER.error("RestClient调用服务出现异常" + e.getMessage(), e);
        }
        LOGGER.info("RestClient: {}:\n{}", url, responseEntity);
        return responseEntity;
    }

    /**
     * 对请求参数为rest型变量的exchange方法的封装，可以参考对应的方法RestTemplate.exchange
     *
     * @param url           相对url地址
     * @param method        HttpMethod方法类型
     * @param requestEntity 包含请求头(HttpHeaders)或(和)请求体(Object)的实体
     * @param responseType  响应类型，通常为Object.class
     * @param uriVariables  uri中的参数占位符
     * @param <T>           范型T
     * @return ResponseEntity
     */
    public <T> ResponseEntity<T> exchange(String url,
                                          HttpMethod method,
                                          HttpEntity<?> requestEntity,
                                          Class<T> responseType,
                                          Object... uriVariables) {
        LOGGER.info("RestClient: {}", url);

        ResponseEntity<T> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
        } catch (RestClientException e) {
            e.printStackTrace();
            LOGGER.error("RestClient调用服务出现异常" + e.getMessage(), e);
        }
        LOGGER.info("RestClient: {}:\n{}", url, responseEntity);
        return responseEntity;
    }

    /**
     * 对RestTemplate.getForObject的简单封装
     *
     * @param url          相对URL
     * @param responseType 返回类型
     * @param uriVariables 参数
     * @param <T>          范型T
     * @return T
     */
    public <T> T getForObject(String url,
                              Class<T> responseType,
                              Object... uriVariables) {
        LOGGER.info("RestClient: {}", url);

        T response = null;
        try {
            response = restTemplate.getForObject(url, responseType, uriVariables);
        } catch (RestClientException e) {
            e.printStackTrace();
            LOGGER.error("RestClient调用服务出现异常" + e.getMessage(), e);
        }
        LOGGER.info("RestClient: {}:\n{}", url, response);
        return response;
    }
}
