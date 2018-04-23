package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.FollowUserBO;
import com.abc12366.bangbang.service.FollowService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-06
 * Time: 16:21
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class FollowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FollowController.class);

    @Autowired
    private FollowService followService;

    //关注用户
    @PostMapping(path = "/follow/{followedUserId}")
    public ResponseEntity insert(@PathVariable String followedUserId, HttpServletRequest request) {
        LOGGER.info("{}", followedUserId);
        FollowUserBO followUserBO = followService.insert(followedUserId, request);
        return ResponseEntity.ok(Utils.kv("data", followUserBO));
    }

    //取消关注
    @DeleteMapping(path = "/follow/{followedUserId}")
    public ResponseEntity delete(@PathVariable String followedUserId, HttpServletRequest request) {
        LOGGER.info("{}:{}", followedUserId, request);
        followService.delete(followedUserId, request);
        return ResponseEntity.ok(Utils.kv());
    }

    //查看已关注用户
    @GetMapping(path = "/follow/{userId}")
    public ResponseEntity selectPeopleIFollow(@PathVariable String userId,
                                              @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                              @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                          size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<FollowUserBO> followUserBOList = followService.selectPeopleIFollow(userId);
        LOGGER.info("{}", followUserBOList);
        return (followUserBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) followUserBOList, "total", ((Page) followUserBOList)
                        .getTotal()));
    }

    //查询谁关注了我
    @GetMapping(path = "/followed/{userId}")
    public ResponseEntity selectMyFollowerList(@PathVariable String userId,
                                               @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                               @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                           size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<FollowUserBO> followUserBOList = followService.selectMyFollowerList(userId);
        LOGGER.info("{}", followUserBOList);
        return (followUserBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) followUserBOList, "total", ((Page) followUserBOList)
                        .getTotal()));
    }

    //查询用户被关注次数
    @GetMapping(path = "count/follow/{followedUserId}")
    public ResponseEntity selectFollowedCount(@PathVariable String followedUserId) {
        LOGGER.info("{}", followedUserId);
        int count = followService.selectFollowedCount(followedUserId);
        return ResponseEntity.ok(Utils.kv("data", count));
    }
}
