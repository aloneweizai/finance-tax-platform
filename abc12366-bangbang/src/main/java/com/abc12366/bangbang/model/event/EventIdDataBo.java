package com.abc12366.bangbang.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventIdDataBo implements Serializable {

    private EventIdBo data;

    public EventIdBo getData() {
        return data;
    }

    public void setData(EventIdBo data) {
        this.data = data;
    }
}
