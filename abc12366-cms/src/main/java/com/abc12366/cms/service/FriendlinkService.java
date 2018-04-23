package com.abc12366.cms.service;

import com.abc12366.cms.model.bo.FriendlinkBo;

import java.util.List;

public interface FriendlinkService {

    List<FriendlinkBo> selectList();

    FriendlinkBo save(FriendlinkBo friendlinkBo);

    FriendlinkBo update(FriendlinkBo friendlinkBo);

    FriendlinkBo selectOneById(String friendlinkId);

    String delete(String friendlinkId);
}
