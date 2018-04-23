package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class EventlltjListBo implements Serializable {

    /**
     * 浏览统计**varchar(64)
     **/
    private Map<String, List<EventlltjBo>> lltj;

    public Map<String, List<EventlltjBo>> getLltj() {
        return lltj;
    }

    public void setLltj(Map<String, List<EventlltjBo>> lltj) {
        this.lltj = lltj;
    }
}
