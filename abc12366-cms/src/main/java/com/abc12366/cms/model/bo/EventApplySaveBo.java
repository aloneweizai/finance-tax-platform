package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS报名信息
 **/
@SuppressWarnings("serial")
public class EventApplySaveBo implements Serializable {

    /**
     * 报名信息
     **/
    private EventApplyBo eventApply;

    /**
     * 报名扩展项信息
     **/
    private List<EventApplyAttrBo> applyAttrList;

    public EventApplyBo getEventApply() {
        return eventApply;
    }

    public void setEventApply(EventApplyBo eventApply) {
        this.eventApply = eventApply;
    }

    public List<EventApplyAttrBo> getApplyAttrList() {
        return applyAttrList;
    }

    public void setApplyAttrList(List<EventApplyAttrBo> applyAttrList) {
        this.applyAttrList = applyAttrList;
    }
}
