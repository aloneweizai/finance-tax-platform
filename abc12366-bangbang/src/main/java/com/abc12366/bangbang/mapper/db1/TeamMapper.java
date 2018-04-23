package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.Team;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 10:49
 */
public interface TeamMapper {
    int delete(String id);

    int update(Team team);

    int insert(Team team);
}
