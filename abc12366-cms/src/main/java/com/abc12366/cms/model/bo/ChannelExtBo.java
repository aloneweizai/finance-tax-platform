package com.abc12366.cms.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * CMS栏目内容表
 **/
@SuppressWarnings("serial")
public class ChannelExtBo implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * 外部链接**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String link;

    /**
     * 栏目页模板**varchar(100)
     **/
    @NotEmpty(message = "栏目首页模板不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 1, max = 100)
    private String tplChannel;

    /**
     * 内容页模板**varchar(100)
     **/
    @NotEmpty(message = "栏目列表模板不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 1, max = 100)
    private String tplContent;

    /**
     * 标题图**varchar(100)
     **/
    @Size(min = 0, max = 100)
    private String titleImg;

    /**
     * 内容图**varchar(100)
     **/
    @Size(min = 0, max = 100)
    private String contentImg;

    /**
     * 评论(0:匿名;1:会员一次;2:关闭,3会员多次)**int(11)
     **/
    private Integer commentControl;

    /**
     * meta标题**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String title;

    /**
     * meta关键字**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String keywords;

    /**
     * meta描述**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String description;

    /**
     * 内容**longtext
     **/
    private String txt;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTplChannel() {
        return tplChannel;
    }

    public void setTplChannel(String tplChannel) {
        this.tplChannel = tplChannel;
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public Integer getCommentControl() {
        return commentControl;
    }

    public void setCommentControl(Integer commentControl) {
        this.commentControl = commentControl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
