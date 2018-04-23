package com.abc12366.bangbang.mapper.db1;


import com.abc12366.bangbang.model.TeamMember;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 14:41
 */
public interface TeamMemberMapper {

    int insert(TeamMember teamMember);

    int delete(TeamMember teamMember);

    int update(TeamMember teamMember);
}
