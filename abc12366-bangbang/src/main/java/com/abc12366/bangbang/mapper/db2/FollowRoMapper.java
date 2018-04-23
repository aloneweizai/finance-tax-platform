package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.FollowUserBO;
import com.abc12366.bangbang.model.bo.MyFollowListBO;
import com.abc12366.bangbang.model.bo.MyFollowerListBO;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-10
 * Time: 11:03
 */
public interface FollowRoMapper {
    List<FollowUserBO> selectPeopleIFollow(String userId);

    List<FollowUserBO> selectMyFollowerList(String userId);

    String selectFollowedCount(String followedUserId);

    List<FollowUserBO> selectExist(Map map);

    List<MyFollowerListBO> selectFollowerListByUserId(String userId);

    List<MyFollowListBO> selectMyfollowByUserId(String userId);
}
