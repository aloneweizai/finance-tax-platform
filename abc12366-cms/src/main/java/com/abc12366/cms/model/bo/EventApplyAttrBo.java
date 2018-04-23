package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS活动报名扩展属性表
 **/
@SuppressWarnings("serial")
public class EventApplyAttrBo implements Serializable {

    /**
     * 报名申请ID**varchar(64)
     **/
    private String applyId;

    /**
     * 名称**varchar(30)
     **/
    private String attrName;

    /**
     * 值**varchar(255)
     **/
    private String attrValue;

    public String getApplyId() {
        return this.applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
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
