package com.abc12366.message.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.mapper.db1.UpyunMapper;
import com.abc12366.message.mapper.db2.UpyunRoMapper;
import com.abc12366.message.model.UpyunTemplate;
import com.abc12366.message.model.bo.UpyunTemplateAcceptBaseBO;
import com.abc12366.message.model.bo.UpyunTemplatesAcceptBO;
import com.abc12366.message.service.UpyunService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-08
 * Time: 15:59
 */
@Service
public class UpyunServiceImpl implements UpyunService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpyunServiceImpl.class);

    @Autowired
    private UpyunRoMapper upyunRoMapper;

    @Autowired
    private UpyunMapper upyunMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<UpyunTemplate> selectList(Map<String, String> map) {
        return upyunRoMapper.selectList(map);
    }

    @Override
    public void synchronizeTemplate() {
        String url = SpringCtxHolder.getProperty("message.upyun.send.url") + "/templates";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        httpHeaders.add("Authorization", SpringCtxHolder.getProperty("message.upyun.auth"));
        //调用又拍接口请求体设置
        LinkedMultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        HttpEntity entity = new HttpEntity(requestBody, httpHeaders);
        ResponseEntity responseEntity;
        UpyunTemplatesAcceptBO templates = null;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (RestTemplateUtil.isExchangeSuccessful(responseEntity)) {
                templates = JSON.parseObject(String.valueOf(responseEntity.getBody()), UpyunTemplatesAcceptBO.class);
            }
            LOGGER.info("从又拍获取到的模板数据是：{}", String.valueOf(responseEntity.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(4827);
        }

        //将调用接口获取的模板数据写入表中
        upyunMapper.delete();
        System.out.println(!StringUtils.isEmpty(templates));
        System.out.println(StringUtils.isEmpty(templates.getTemplates()));
        System.out.println(templates.getTemplates().size() > 0);
        if (!StringUtils.isEmpty(templates) && !StringUtils.isEmpty(templates.getTemplates()) && templates
                .getTemplates().size() > 0) {
            for (UpyunTemplateAcceptBaseBO templateBase : templates.getTemplates()) {
                UpyunTemplate upyunTemplate = new UpyunTemplate();
                upyunTemplate.setId(Utils.uuid());
                upyunTemplate.setTitle(templateBase.getTitle());
                upyunTemplate.setSign(templateBase.getTemp_sign());
                upyunTemplate.setContent(templateBase.getContent());
                upyunTemplate.setType(templateBase.getType());
                upyunTemplate.setStatus(templateBase.getStatus());
                upyunTemplate.setCreateTime(templateBase.getUpdated_at());
                upyunTemplate.setReason(templateBase.getReason());
                upyunTemplate.setTemplateId(templateBase.getId());
                upyunTemplate.setSynchronizeTime(new Date());
                upyunTemplate.setOwnerId(templateBase.getOwner().getId());
                upyunTemplate.setOwnerName(templateBase.getOwner().getName());
                upyunTemplate.setOwnerIndustry(templateBase.getOwner().getIndustry());
                upyunTemplate.setOwnerMarketing(templateBase.getOwner().getMarketing());
                upyunMapper.insert(upyunTemplate);
            }
        }

    }
}
