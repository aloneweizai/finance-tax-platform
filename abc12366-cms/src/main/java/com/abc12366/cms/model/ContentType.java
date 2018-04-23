package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容类型表
 **/
@SuppressWarnings("serial")
public class ContentType implements Serializable {

    /**
     * typeId**varchar(64)
     **/
    private String typeId;

    /**
     * 名称**varchar(20)
     **/
    private String typeName;

    /**
     * 图片宽**int(11)
     **/
    private Integer imgWidth;

    /**
     * 图片高**int(11)
     **/
    private Integer imgHeight;

    /**
     * 是否有图片**tinyint(1)
     **/
    private Integer hasImage;

    /**
     * 是否禁用**tinyint(1)
     **/
    private Integer isDisabled;

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getImgWidth() {
        return this.imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Integer getImgHeight() {
        return this.imgHeight;
    }

    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    public Integer getHasImage() {
        return this.hasImage;
    }

    public void setHasImage(Integer hasImage) {
        this.hasImage = hasImage;
    }

    public Integer getIsDisabled() {
        return this.isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }

}
