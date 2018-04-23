package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.FollowMapper;
import com.abc12366.bangbang.mapper.db2.FollowRoMapper;
import com.abc12366.bangbang.model.FollowUser;
import com.abc12366.bangbang.model.bo.FollowUserBO;
import com.abc12366.bangbang.model.bo.MyFollowListBO;
import com.abc12366.bangbang.model.bo.MyFollowerListBO;
import com.abc12366.bangbang.service.FollowService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-06
 * Time: 16:26
 */
@Service
public class FollowServiceImpl implements FollowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowServiceImpl.class);

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private FollowRoMapper followRoMapper;

    @Override
    public FollowUserBO insert(String followedUserId, HttpServletRequest request) {
        LOGGER.info("{}:{}", followedUserId, request);
        FollowUser followUser = new FollowUser();
        Date date = new Date();
        String userId = (String) request.getAttribute(Constant.USER_ID);
        if (userId == null || userId.equals("")) {
            throw new ServiceException(4193);
        }
        if (followedUserId.equals(userId)) {
            throw new ServiceException(4703);
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("followedUserId", followedUserId);
        if (isExist(map)) {
            throw new ServiceException(4704);
        }

        followUser.setId(Utils.uuid());
        followUser.setFollowedUserId(followedUserId);
        followUser.setUserId(userId);
        followUser.setCreateTime(date);
        followUser.setLastUpdate(date);
        int result = followMapper.insert(followUser);
        if (result < 1) {
            throw new ServiceException(4101);
        }
        FollowUserBO followUserBO = new FollowUserBO();
        BeanUtils.copyProperties(followUser, followUserBO);
        return followUserBO;
    }

    @Override
    public void delete(String followedUserId, HttpServletRequest request) {
        LOGGER.info("{}:{}", followedUserId, request);
        String userId = (String) request.getAttribute(Constant.USER_ID);
        if (userId == null || userId.equals("")) {
            throw new ServiceException(4193);
        }
        Map<String, String> map = new HashMap<>();
        map.put("followedUserId", followedUserId);
        map.put("userId", userId);
        int result = followMapper.delete(map);
        if (result < 1) {
            throw new ServiceException(4103);
        }
    }

    @Override
    public List<FollowUserBO> selectPeopleIFollow(String userId) {
        LOGGER.info("{}", userId);
        return followRoMapper.selectPeopleIFollow(userId);
    }

    @Override
    public List<FollowUserBO> selectMyFollowerList(String userId) {
        LOGGER.info("{}", userId);
        return followRoMapper.selectMyFollowerList(userId);
    }

    @Override
    public int selectFollowedCount(String followedUserId) {
        LOGGER.info("{}", followedUserId);
        return Integer.parseInt(followRoMapper.selectFollowedCount(followedUserId));
    }

    @Override
    public List<MyFollowerListBO> selectFollowerListByUserId(String userId) {
        return followRoMapper.selectFollowerListByUserId(userId);
    }

    @Override
    public List<MyFollowListBO> selectMyfollowByUserId(String userId) {
        return followRoMapper.selectMyfollowByUserId(userId);
    }

    public boolean isExist(Map map) {
        LOGGER.info("{}", map);
        List<FollowUserBO> followUserBOList = followRoMapper.selectExist(map);
        if (followUserBOList == null || followUserBOList.size() == 0) {
            return false;
        }
        return true;
    }
}
