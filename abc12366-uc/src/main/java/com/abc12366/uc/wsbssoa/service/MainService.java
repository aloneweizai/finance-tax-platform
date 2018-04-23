package com.abc12366.uc.wsbssoa.service;


import com.abc12366.uc.wsbssoa.dto.AuthorizationDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouzhi on 2015-11-18.
 */
public interface MainService {
    /**
     * 从soa获取公钥，将字符串加密
     *
     * @param inputStr
     * @return
     * @throws Exception
     */
    String RSAEncrypt(HttpServletRequest request, String inputStr) throws Exception;

    /**
     * 将权限对象list转化为map
     */
    Map<String, String> authorizationObj2Map(List<AuthorizationDto> authorList);

    Object getRSAPublicKey(HttpServletRequest request) throws Exception;


}
