package com.abc12366.bangbang.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class SingleEventOneBo implements Serializable {

    private SingleEventBo data;

    public SingleEventBo getData() {
        return data;
    }

    public void setData(SingleEventBo data) {
        this.data = data;
    }
}
