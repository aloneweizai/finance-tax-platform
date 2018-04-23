package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.EventSponsorBo;

import java.util.List;

public interface EventSponsorService {

    List<EventSponsorBo> selectList();

    EventSponsorBo save(EventSponsorBo eventSponsorBo);

    EventSponsorBo selectEventSponsor(String sponsorId);

    EventSponsorBo update(EventSponsorBo eventSponsorBo);

    String delete(String sponsorId);

}
