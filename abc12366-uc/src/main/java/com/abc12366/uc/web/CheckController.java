package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.Check;
import com.abc12366.uc.model.CheckRank;
import com.abc12366.uc.model.ReCheck;
import com.abc12366.uc.model.bo.CheckListBO;
import com.abc12366.uc.service.CheckService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户签到接口控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-21
 * Time: 10:39
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class CheckController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckController.class);

    @Autowired
    private CheckService checkService;

    /**
     * 用户签到接口
     * @param check {@linkplain com.abc12366.uc.model.Check}
     * @return ResponseEntity 签到获取的积分值
     */
    @PostMapping(path = "/check")
    public synchronized ResponseEntity check(@Valid @RequestBody Check check){
        LOGGER.info("用户签到：{}", check);
        int points = checkService.check(check);
        LOGGER.info("用户签到获取的积分：{}", points);
        return ResponseEntity.ok(Utils.kv("data", points));
    }

    /**
     * 用户补签接口
     * @param check {@linkplain com.abc12366.uc.model.ReCheck}
     * @return ResponseEntity
     */
    @PostMapping(path = "/recheck")
    public ResponseEntity reCheck(@Valid @RequestBody ReCheck check){
        LOGGER.info("用户补签到：{}", check);
        checkService.reCheck(check);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 用户签到排行列表
     * @param year 年份
     * @param page 页码
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.CheckRank}
     */
    @GetMapping(path = "/check/rank")
    public ResponseEntity rankingList(@RequestParam(required = false) String year,
                                        @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                      @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        LOGGER.info("获取用户签到排行列表：{}", year);
        List<CheckRank> rankList = checkService.rank(year);
        LOGGER.info("获取用户签到排行列表返回结果：{}", rankList);
        return (rankList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) rankList, "total", ((Page) rankList).getTotal()));
    }

    /**
     * 获取用户的签到情况
     * @param yearMonth 年月：2017-09
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.CheckListBO}
     */
    @GetMapping(path = "/check/list")
    public ResponseEntity checklist(@RequestParam(required = true) String yearMonth){
        LOGGER.info("获取用户签到列表{}", yearMonth);
        List<CheckListBO> checkList = checkService.checklist(yearMonth);
        LOGGER.info("获取用户签到列表{}", checkList);
        return ResponseEntity.ok(Utils.kv("dataList", checkList));
    }

    /**
     * 查询用户本年度累计签到天数
     * @return ResponseEntity
     */
    @GetMapping(path = "/check/total")
    public ResponseEntity checkTotal(){
        String userId = Utils.getUserId();
        LOGGER.info("用户获取本年累计签到天数：{},{}", userId);
        int total = checkService.checkTotal(userId);
        LOGGER.info("用户获取本年累计签到天数返回结果：{}", total);
        return ResponseEntity.ok(Utils.kv("data", total));
    }
}
