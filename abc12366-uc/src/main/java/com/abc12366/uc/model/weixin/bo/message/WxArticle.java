package com.abc12366.uc.model.weixin.bo.message;

import org.hibernate.validator.constraints.NotEmpty;

public class WxArticle {
    @NotEmpty
    private String newsId;//id
    @NotEmpty
    private String title;//标题
    @NotEmpty
    private String thumb_media_id;//图片id

    private String author;//作者

    private String digest;//个数
    @NotEmpty
    private Integer show_cover_pic; //是否显示封面，0为false，即不显示，1为true，即显示
    @NotEmpty
    private String content;//内容
    @NotEmpty
    private String content_source_url;//内容地址

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

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(Integer show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }


}
