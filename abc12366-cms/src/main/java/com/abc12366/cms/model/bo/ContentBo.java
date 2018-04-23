package com.abc12366.cms.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * CMS内容表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentBo implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * userid**varchar(64)
     **/
    private String userid;

    /**
     * username**varchar(100)
     **/
    private String username;

    /**
     * 栏目ID**varchar(64)
     **/
    @NotEmpty(message = "栏目ID不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 64)
    private String channelId;

    /**
     * 访问路径**varchar(30)
     **/
    private String channelPath;

    /**
     * 属性ID**varchar(64)
     **/
    private String typeId;

    /**
     * 内容类型ID**varchar(64)
     **/
    private String contentType;

    /**
     * 模型ID**varchar(64)
     **/
    @NotEmpty(message = "模型ID不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 64)
    private String modelId;

    /**
     * 站点ID**varchar(64)
     **/
    @NotEmpty(message = "站点ID不能为空,如未配置该字段，请先前往模型管理配置！")
    @Size(min = 0, max = 64)
    private String siteId;

    /**
     * 域名**varchar(50)
     **/
    private String domain;

    /**
     * 站点路径**varchar(20)
     **/
    private String sitePath;

    /**
     * 固顶级别**tinyint(4)
     **/
    private Integer topLevel;

    /**
     * 是否推荐**tinyint(1)
     **/
    private Integer isRecommend;

    /**
     * 状态(0:草稿;1:审核中;2:审核通过;3:回收站;4:投稿;5:归档)**tinyint(4)
     **/
    private Integer status;

    /**
     * 日访问数**int(11)
     **/
    private Integer viewsDay;

    /**
     * 推荐级别**tinyint(4)
     **/
    private Integer recommendLevel;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(Integer topLevel) {
        this.topLevel = topLevel;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViewsDay() {
        return viewsDay;
    }

    public void setViewsDay(Integer viewsDay) {
        this.viewsDay = viewsDay;
    }

    public Integer getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(Integer recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public String getChannelPath() {
        return channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }
}
