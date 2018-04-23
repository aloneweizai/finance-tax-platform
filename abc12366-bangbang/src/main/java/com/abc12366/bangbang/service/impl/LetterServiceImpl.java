package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.LetterMapper;
import com.abc12366.bangbang.model.bo.LetterInsertBO;
import com.abc12366.bangbang.model.bo.LetterListBO;
import com.abc12366.bangbang.model.bo.LetterResponse;
import com.abc12366.bangbang.service.LetterService;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-24
 * Time: 17:41
 */
@Service
public class LetterServiceImpl implements LetterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LetterServiceImpl.class);

    @Autowired
    private LetterMapper letterMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public LetterResponse send(LetterInsertBO letterInsertBO, HttpServletRequest request) throws IOException {
        LOGGER.info("{}", letterInsertBO);
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/message/user";
        Map<String, Object> map = new HashMap<>();
        map.put("fromUserId", letterInsertBO.getFromUserId());
        map.put("toUserId", letterInsertBO.getToUserId());
        map.put("content", letterInsertBO.getContent());
        map.put("type", letterInsertBO.getType());
        String responseStr;
        try {
            responseStr = restTemplateUtil.exchange(url, HttpMethod.POST, map, request);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        LetterResponse response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, LetterResponse.class);
        }
        return response;
    }

    @Override
    public LetterListBO selectList(HttpServletRequest request) throws IOException {
        LOGGER.info("{}", request);
        String toId = (String) request.getAttribute(Constant.USER_ID);
        if (toId == null || toId.trim().equals("")) {
            throw new ServiceException(4193);
        }
        //老套路：查库
        //return letterRoMapper.selectList(toId);

        //新套路，调message接口
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/message/user";
        if (!StringUtils.isEmpty(request.getAttribute("page"))) {
            url += "?page=" + request.getAttribute("page");
            if (!StringUtils.isEmpty(request.getAttribute("size"))) {
                url += "&size=" + request.getAttribute("size");
            }
        }
        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.GET, request);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        LetterListBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, LetterListBO.class);
        }
        return response;
    }

    @Override
    public LetterResponse read(String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", id, request);
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/message/user/" + id;

        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.GET, request);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        LetterResponse response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, LetterResponse.class);
        }
        return response;
    }

    @Override
    public LetterResponse update(String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", id, request);
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/message/user/" + id;

        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.PUT, request);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        LetterResponse response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, LetterResponse.class);
        }
        return response;
    }

    @Override
    public LetterResponse delete(String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", id, request);
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/message/user/" + id;

        String responseStr;
        LetterResponse response = null;
        if (!StringUtils.isEmpty(request.getAttribute(Constant.USER_ID))) {
            try {
                responseStr = restTemplateUtil.send(url, HttpMethod.DELETE, request);
            } catch (Exception e) {
                throw new ServiceException(4821);
            }
            if (!StringUtils.isEmpty(responseStr)) {
                response = JSON.parseObject(responseStr, LetterResponse.class);
            }
        }

        return response;
    }
}
