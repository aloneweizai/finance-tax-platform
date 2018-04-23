package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.VoteAddition;

/**
 * Created by lijun on 6/27/17.
 */
public interface VoteAdditionMapper {
    void insert(VoteAddition voteAddition);

    void delete(String voteId);
}
