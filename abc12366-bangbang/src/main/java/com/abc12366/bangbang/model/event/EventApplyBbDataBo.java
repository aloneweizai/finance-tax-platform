package com.abc12366.bangbang.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventApplyBbDataBo implements Serializable {
    private EventApplyBbBo data;

    public EventApplyBbBo getData() {
        return data;
    }

    public void setData(EventApplyBbBo data) {
        this.data = data;
    }
}
