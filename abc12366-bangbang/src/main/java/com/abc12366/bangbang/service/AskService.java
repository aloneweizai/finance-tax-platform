package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.*;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 15:55
 */
public interface AskService {
    List<AskBO> selectListForAdmin(AsksQueryParamBO asksQueryParamBO);

    List<AskBO> selectListForUser(AskQueryParamBO askQueryParamBO);

    AskBO insert(AskInsertBO askInsertBO);

    AskBO selectOne(String id);

    AskBO update(String id, AskUpdateBO askUpdateBO, String userId);

    int delete(String id, String userId);

    int block(String id, String userId);

    List<HotspotAskBO> selectHotspotAsks();

    List<AskBO> selectHotspotComments();
}
