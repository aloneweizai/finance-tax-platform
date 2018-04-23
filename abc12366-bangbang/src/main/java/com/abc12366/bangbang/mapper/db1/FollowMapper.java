package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.FollowUser;

import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-07
 * Time: 10:22
 */
public interface FollowMapper {
    int insert(FollowUser followUser);

    int delete(Map<String, String> map);
}
