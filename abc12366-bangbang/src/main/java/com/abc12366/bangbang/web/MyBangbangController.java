package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.MyCollectListBO;
import com.abc12366.bangbang.model.bo.MyFollowListBO;
import com.abc12366.bangbang.model.bo.MyFollowerListBO;
import com.abc12366.bangbang.model.bo.TeamBO;
import com.abc12366.bangbang.service.CollectService;
import com.abc12366.bangbang.service.FollowService;
import com.abc12366.bangbang.service.TeamService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-21
 * Time: 11:35
 */
@RestController
@RequestMapping(path = "/mybangbang", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class MyBangbangController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBangbangController.class);

    @Autowired
    private TeamService teamService;

    @Autowired
    private FollowService followService;

    @Autowired
    private CollectService collectService;


    @GetMapping(path = "/myteams/{userId}")
    public ResponseEntity selectTeamsListByUserId(
            @PathVariable String userId,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TeamBO> teamBOList = teamService.selectListByUserId(userId);
        LOGGER.info("{}", teamBOList);
        return (teamBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) teamBOList, "total", ((Page) teamBOList).getTotal()));
    }

    @GetMapping(path = "/myfans/{userId}")
    public ResponseEntity selectFollowerListByUserId(
            @PathVariable String userId,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<MyFollowerListBO> myFollowerBOList = followService.selectFollowerListByUserId(userId);
        LOGGER.info("{}", myFollowerBOList);
        return (myFollowerBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) myFollowerBOList, "total", ((Page) myFollowerBOList)
                        .getTotal()));
    }

    @GetMapping(path = "/mycollect/{userId}")
    public ResponseEntity selectCollectListByUserId(
            @PathVariable String userId,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<MyCollectListBO> myCollectList = collectService.selectCollectListByUserId(userId);
        LOGGER.info("{}", myCollectList);
        return (myCollectList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) myCollectList, "total", ((Page) myCollectList).getTotal
                        ()));
    }

    @GetMapping(path = "/myfollow/{userId}")
    public ResponseEntity selectMyfollowByUserId(
            @PathVariable String userId,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<MyFollowListBO> myFollowBOList = followService.selectMyfollowByUserId(userId);
        LOGGER.info("{}", myFollowBOList);
        return (myFollowBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) myFollowBOList, "total", ((Page) myFollowBOList)
                        .getTotal()));
    }
}