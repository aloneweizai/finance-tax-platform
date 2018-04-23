package com.abc12366.cms.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * CMS栏目表
 **/
@SuppressWarnings("serial")
public class ChannelBo implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    @NotEmpty(message = "模型ID不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 64)
    /**模型ID**varchar(64)**/
    private String modelId;

    @NotEmpty(message = "站点ID不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 64)
    /**站点ID**varchar(64)**/
    private String siteId;

    /**
     * 站点路径
     **/
    private String sitePath;

    /**
     * 域名
     **/
    private String domain;

    /**
     * 内容类型**varchar(2000)
     **/
    private String contentType;

    /**
     * 父栏目ID**varchar(64)
     **/
    @NotEmpty(message = "父栏目ID不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 64)
    private String parentId;

    /**
     * 访问路径**varchar(30)
     **/
    @NotEmpty(message = "栏目路径不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 30)
    private String channelPath;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    /**
     * 是否显示**tinyint(1)
     **/
    private Integer isDisplay;

    /**
     * 栏目名称**varchar(100)
     **/
    @NotEmpty(message = "栏目名称不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 100)
    private String channelName;

    /**
     * 外部链接供查询列表用
     **/
    private String link;

    /**
     * 栏目标题图片
     **/
    private String titleImg;

    /**
     * 文章总数
     **/
    private Integer cnt;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getSiteId() {
        return this.siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChannelPath() {
        return this.channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDisplay() {
        return this.isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
