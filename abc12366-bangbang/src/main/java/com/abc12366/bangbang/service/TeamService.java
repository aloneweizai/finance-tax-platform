package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.TeamBO;
import com.abc12366.bangbang.model.bo.TeamInsertBO;
import com.abc12366.bangbang.model.bo.TeamUpdateBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 10:12
 */
public interface TeamService {
    List<TeamBO> selectList();

    TeamBO insert(TeamInsertBO teamInsertBO);

    void delete(String id);

    TeamBO update(TeamUpdateBO teamUpdateBO, String id);

    TeamBO selectOne(String id);

    List<TeamBO> selectListByUserId(String userId);
}
