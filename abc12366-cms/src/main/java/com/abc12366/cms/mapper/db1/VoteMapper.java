package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Vote;
import com.abc12366.cms.model.VoteHistory;
import com.abc12366.cms.model.VoteResult;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-07 4:49 PM
 * @since 1.0.0
 */
public interface VoteMapper {
    void insert(Vote vote);

    void update(Vote vote);

    void delete(String id);

    void insertResult(VoteResult result);

    void insertHistory(VoteHistory history);

    void updateStatus();

    void deleteLog(String id);
}
