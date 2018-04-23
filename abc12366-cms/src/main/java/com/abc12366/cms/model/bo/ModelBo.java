package com.abc12366.cms.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * CMS模型表
 **/
@SuppressWarnings("serial")
public class ModelBo implements Serializable {

    /**
     * 模型Id**varchar(64)
     **/
    private String modelId;

    /**
     * 模型名称**varchar(100)
     **/
    @NotEmpty(message = "模型名称不能为空！")
    @Size(min = 1, max = 100)
    private String modelName;

    /**
     * 模型路径**varchar(100)
     **/
    @Size(min = 0, max = 100)
    private String modelPath;

    /**
     * 栏目模板前缀**varchar(20)
     **/
    @Size(min = 0, max = 20)
    private String tplChannelPrefix;

    /**
     * 内容模板前缀**varchar(20)
     **/
    @Size(min = 0, max = 20)
    private String tplContentPrefix;

    /**
     * 栏目标题图宽度**int(11)
     **/
    private Integer titleImgWidth;

    /**
     * 栏目标题图高度**int(11)
     **/
    private Integer titleImgHeight;

    /**
     * 栏目内容图宽度**int(11)
     **/
    private Integer contentImgWidth;

    /**
     * 栏目内容图高度**int(11)
     **/
    private Integer contentImgHeight;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    /**
     * 是否有内容**tinyint(1)
     **/
    private Integer hasContent;

    /**
     * 是否禁用**tinyint(1)
     **/
    private Integer isDisabled;

    /**
     * 是否默认模型**tinyint(1)
     **/
    private Integer isDef;

    /**
     * 是否全站模型**tinyint(1)
     **/
    private Integer isGlobal;

    /**
     * 非全站模型所属站点**varchar(64)
     **/
    @Size(min = 0, max = 64)
    private String siteId;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getTplChannelPrefix() {
        return tplChannelPrefix;
    }

    public void setTplChannelPrefix(String tplChannelPrefix) {
        this.tplChannelPrefix = tplChannelPrefix;
    }

    public String getTplContentPrefix() {
        return tplContentPrefix;
    }

    public void setTplContentPrefix(String tplContentPrefix) {
        this.tplContentPrefix = tplContentPrefix;
    }

    public Integer getTitleImgWidth() {
        return titleImgWidth;
    }

    public void setTitleImgWidth(Integer titleImgWidth) {
        this.titleImgWidth = titleImgWidth;
    }

    public Integer getTitleImgHeight() {
        return titleImgHeight;
    }

    public void setTitleImgHeight(Integer titleImgHeight) {
        this.titleImgHeight = titleImgHeight;
    }

    public Integer getContentImgWidth() {
        return contentImgWidth;
    }

    public void setContentImgWidth(Integer contentImgWidth) {
        this.contentImgWidth = contentImgWidth;
    }

    public Integer getContentImgHeight() {
        return contentImgHeight;
    }

    public void setContentImgHeight(Integer contentImgHeight) {
        this.contentImgHeight = contentImgHeight;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getHasContent() {
        return hasContent;
    }

    public void setHasContent(Integer hasContent) {
        this.hasContent = hasContent;
    }

    public Integer getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Integer getIsDef() {
        return isDef;
    }

    public void setIsDef(Integer isDef) {
        this.isDef = isDef;
    }

    public Integer getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(Integer isGlobal) {
        this.isGlobal = isGlobal;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
