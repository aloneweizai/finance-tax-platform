package com.abc12366.uc.service.impl;

import com.abc12366.gateway.web.BaseController;
import com.abc12366.uc.model.weixin.ShortUrl;
import com.abc12366.uc.service.Long2ShortService;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.abc12366.uc.web.wx.WxUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 长链接转短链接实现类
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-05 2:03 PM
 * @since 1.0.0
 */
@Service
public class Long2ShortServiceImpl extends BaseController implements Long2ShortService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUserController.class);

    public Long2ShortServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public ShortUrl long2short(String url) {
        Map<String, String> headParams = new HashMap<>();
        headParams.put("access_token", WxGzhClient.getInstanceToken());

        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("action", "long2short");
        bodyParams.put("long_url", url);
        ShortUrl shortUrl = WxConnectFactory.post(WechatUrl.LONG2SHORT, headParams, bodyParams, ShortUrl.class);
        LOGGER.info("{}", shortUrl);
        return shortUrl;
    }

    @Override
    public String tinyUrl(String url) {
        return getForObject("http://tinyurl.com/api-create.php?url=" + url, String.class);
    }
}
