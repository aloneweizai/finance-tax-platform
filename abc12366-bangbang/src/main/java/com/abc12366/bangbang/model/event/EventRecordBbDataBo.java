package com.abc12366.bangbang.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventRecordBbDataBo implements Serializable {
    private EventRecordBbBo data;

    public EventRecordBbBo getData() {
        return data;
    }

    public void setData(EventRecordBbBo data) {
        this.data = data;
    }
}
