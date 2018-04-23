package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.TeamMember;
import com.abc12366.bangbang.model.bo.TeamMemberBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 14:41
 */
public interface TeamMemberRoMapper {
    List<TeamMemberBO> selectList();

    TeamMemberBO selectOne(TeamMember teamMember);
}
