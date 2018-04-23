package com.abc12366.bangbang.model.event;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stuy on 2017/9/14.
 */
public class SingleEventListBo implements Serializable {

    private List<SingleEventBo> dataList;

    public List<SingleEventBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<SingleEventBo> dataList) {
        this.dataList = dataList;
    }
}
