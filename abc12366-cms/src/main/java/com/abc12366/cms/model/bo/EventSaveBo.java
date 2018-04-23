package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS活动信息
 **/
@SuppressWarnings("serial")
public class EventSaveBo implements Serializable {

    /**
     * 活动信息
     **/
    private EventBo event;

    /**
     * 活动模型项信息
     **/
    private List<EventModelItemBo> modelItemList;

    public EventBo getEvent() {
        return event;
    }

    public void setEvent(EventBo event) {
        this.event = event;
    }

    public List<EventModelItemBo> getModelItemList() {
        return modelItemList;
    }

    public void setModelItemList(List<EventModelItemBo> modelItemList) {
        this.modelItemList = modelItemList;
    }
}
