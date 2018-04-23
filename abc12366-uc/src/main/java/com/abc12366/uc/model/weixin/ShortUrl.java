package com.abc12366.uc.model.weixin;

/**
 * 短网址对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-05 2:14 PM
 * @since 1.0.0
 */
public class ShortUrl extends BaseWxRespon{

    /**
     * 短地址
     */
    private String short_url;

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "short_url='" + short_url + '\'' +
                "} " + super.toString();
    }
}
