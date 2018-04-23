package com.abc12366.uc.model.weixin.bo.message;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 微信图文消息
 * @author zhushuai 2017-11-6
 *
 */
public class Article {
    @NotEmpty
    private String newsId;//图文ID
    @NotEmpty
    private String title;//图文标题
    @NotEmpty
    private String description;//描述
    @NotEmpty
    private String picUrl;//图片地址
    @NotEmpty
    private String url;//地址

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
