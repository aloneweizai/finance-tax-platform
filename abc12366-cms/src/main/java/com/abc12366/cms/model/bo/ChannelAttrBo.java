package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS栏目扩展属性表
 **/
@SuppressWarnings("serial")
public class ChannelAttrBo implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * 名称**varchar(30)
     **/
    private String attrName;

    /**
     * 值**varchar(255)
     **/
    private String attrValue;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAttrName() {
        return this.attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return this.attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

}
