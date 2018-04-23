package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.VoteAddition;

import java.util.List;

/**
 * Created by lijun on 6/27/17.
 */
public interface VoteAdditionRoMapper {
    List<VoteAddition> selectList(String voteId);
}
