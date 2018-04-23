package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.event.EventApplyBbBo;
import com.abc12366.bangbang.model.event.EventIdBo;
import com.abc12366.bangbang.model.event.EventRecordBbBo;
import com.abc12366.bangbang.model.event.SingleEventBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by stuy on 2017/9/13.
 */
public interface EventService {

    SingleEventBo singleEvent(HttpServletRequest request, String category);

    List<SingleEventBo> singleEventList(HttpServletRequest request, String category);

    EventIdBo saveeventrecord(HttpServletRequest request, String eventid,String userid);

    EventRecordBbBo addEventRecord(HttpServletRequest request,EventRecordBbBo eventRecordBbBo);

    EventApplyBbBo saveEventApply(HttpServletRequest request,EventApplyBbBo eventApplyBbBo);
}
