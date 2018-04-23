package com.abc12366.cms.mapper.db2;


import com.abc12366.cms.model.event.*;

import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/9/13.
 */
public interface EventBangBangMapper {

    SingleEventBo singleEvent(Map map);

    List<SingleEventBo> singleEventList(Map map);

    EventBrowseCountBo browseCount(Map<String,String> map);

    List<EventApplyFieldBo> selectModelItem(Map map);

    EventIdBo selectEventId(Map map);

    EventSponsorBbBo selectEventSponsor(Map map);

    int selectEventApplyStatus(Map map);

    List<EventIdBo> selectEventRelevant(Map map);
}
