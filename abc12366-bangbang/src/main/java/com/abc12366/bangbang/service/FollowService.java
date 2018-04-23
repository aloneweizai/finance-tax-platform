package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.FollowUserBO;
import com.abc12366.bangbang.model.bo.MyFollowListBO;
import com.abc12366.bangbang.model.bo.MyFollowerListBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-06
 * Time: 16:26
 */
public interface FollowService {
    FollowUserBO insert(String followedUserId, HttpServletRequest request);

    void delete(String followedUserId, HttpServletRequest request);

    List<FollowUserBO> selectPeopleIFollow(String userId);

    List<FollowUserBO> selectMyFollowerList(String userId);

    int selectFollowedCount(String followedUserId);

    List<MyFollowerListBO> selectFollowerListByUserId(String userId);

    List<MyFollowListBO> selectMyfollowByUserId(String userId);
}
