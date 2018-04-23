package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.EventListBo;
import com.abc12366.cms.model.bo.EventSaveBo;

import java.util.List;
import java.util.Map;

public interface EventService {

    List<EventListBo> selectList(Map<String, Object> map);

    EventSaveBo save(EventSaveBo eventSaveBo);

    EventSaveBo selectEvent(String eventId);

    EventSaveBo selecttopone(String category);

    EventSaveBo update(EventSaveBo eventSaveBo);

    String delete(String eventId);

}
