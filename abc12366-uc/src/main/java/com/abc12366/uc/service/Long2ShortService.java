package com.abc12366.uc.service;

import com.abc12366.uc.model.weixin.ShortUrl;

/**
 * 长链接转短链接服务
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-05 2:01 PM
 * @since 1.0.0
 */
public interface Long2ShortService {

    /**
     * 微信长链接转短链接
     *
     * @param url 长url地址
     * @return {"errcode":0,"errmsg":"ok","short_url":"http:\/\/w.url.cn\/s\/AvCo6Ih"}
     */
    ShortUrl long2short(String url);

    /**
     * tiny长链接转短链接
     *
     * @param url 长url地址
     * @return 端地址
     */
    String tinyUrl(String url);
}
