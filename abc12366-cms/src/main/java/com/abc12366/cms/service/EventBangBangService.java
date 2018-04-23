package com.abc12366.cms.service;


import com.abc12366.cms.model.event.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by stuy on 2017/9/13.
 */
public interface EventBangBangService {

    SingleEventBo singleEvent(Map map);

    List<SingleEventBo> singleEventList(Map map);

    EventRecordBbBo addEventRecord(EventRecordBbBo eventRecord);

    EventBrowseCountBo browseCount(Map<String,String> map);

    EventIdBo selectEventId(Map map);

    EventApplyBbBo saveEventApply(EventApplyBbBo eventApplyBbBo, HttpServletRequest request);

    int selectEventApplyStatus(Map map);


}
