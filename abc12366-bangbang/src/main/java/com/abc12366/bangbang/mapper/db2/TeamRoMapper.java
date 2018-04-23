package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.TeamBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 10:48
 */
public interface TeamRoMapper {
    List<TeamBO> selectList();

    TeamBO selectOne(String id);

    List<TeamBO> selectListByUserId(String userId);
}
