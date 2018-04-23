package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.TeamMemberBO;
import com.abc12366.bangbang.model.bo.TeamMemberInsertBO;
import com.abc12366.bangbang.model.bo.TeamMemberUpdateBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 14:26
 */
public interface TeamMemberService {
    List<TeamMemberBO> selectList();

    TeamMemberBO insert(TeamMemberInsertBO teamMemberInsertBO);

    void delete(String teamId, String userId);

    TeamMemberBO update(TeamMemberUpdateBO teamMemberUpdateBO, String teamId, String userId);

    TeamMemberBO selectOne(String teamId, String userId);
}
