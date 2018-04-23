package com.abc12366.uc.wsbssoa.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.uc.wsbssoa.dto.AuthorizationDto;
import com.abc12366.uc.wsbssoa.response.RSAPkResponse;
import com.abc12366.uc.wsbssoa.service.MainService;
import com.abc12366.uc.wsbssoa.utils.RSAUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-11-26.
 */
@Service
public class MainServiceImpl implements MainService {
    protected Logger _log = LoggerFactory.getLogger(MainServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String RSAEncrypt(HttpServletRequest request, String inputStr) throws Exception {
        RSAPublicKey pbk = (RSAPublicKey) getRSAPublicKey(request);
        return new String(RSAUtil.encrypt(pbk, inputStr.getBytes("iso-8859-1")), "iso-8859-1");
    }

    /**
     * 从soa获取公钥，将getRSAPublicKeyStr()获取到的序列化了的公钥反序列化成对象
     *
     * @return Object
     * @throws Exception
     */
    @Override
    public Object getRSAPublicKey(HttpServletRequest request) throws Exception {
        RSAPkResponse rsaPkResp = getRSAPublicKeyStr(request);
        if (rsaPkResp.isSuccess()) {
            ObjectInputStream i = new ObjectInputStream(new ByteArrayInputStream(rsaPkResp.getPk()));
            return i.readObject();
        } else if ("01".equals(rsaPkResp.getCode())) {
            _log.error("获取公钥网络异常。");
            throw new Exception("NETWORK_ERROR");

        } else {
            _log.error("获取公钥失败。");
            throw new Exception("getRSAPublicKeyFail");
        }


    }

    /**
     * 从soa获取序列化了的公钥
     * @return RSAPkResponse
     */
    private RSAPkResponse getRSAPublicKeyStr(HttpServletRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constant.APP_TOKEN_HEAD, request.getHeader(Constant.APP_TOKEN_HEAD));
        headers.add(Constant.VERSION_HEAD,Constant.VERSION_1);
        String api = "/pk";
        String url = SpringCtxHolder.getProperty("abc12366.message.url")+"/hngs/get?api="+ Base64.getEncoder().encodeToString(api.getBytes());
        HttpEntity requestEntity = new HttpEntity(null, headers);
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        if (RestTemplateUtil.isExchangeSuccessful(responseEntity)) {
            return JSON.parseObject(String.valueOf(responseEntity.getBody()), RSAPkResponse.class);
        } else {
            throw new ServiceException(4192);
        }
    }

    @Override
    public Map<String, String> authorizationObj2Map(List<AuthorizationDto> authorList) {
        Map<String, String> authorMap = new HashMap<String, String>();
        if (authorList != null && authorList.size() > 0) {
            for (AuthorizationDto oneAuthor : authorList) {
                authorMap.put(oneAuthor.getYyfwDm(), oneAuthor.getYyfwMc());
            }
        }
        return authorMap;
    }
}
