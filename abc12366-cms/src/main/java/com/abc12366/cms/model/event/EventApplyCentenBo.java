package com.abc12366.cms.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventApplyCentenBo implements Serializable {

    private String attrName;

    private String attrValue;

    private String applyId;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }
}
