package com.abc12366.uc.web;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-11-11
 */


import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.LotteryActivityipBO;
import com.abc12366.uc.service.LotteryActivityipService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/lotteryactivityip", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LotteryActivityipController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryActivityipController.class);
    @Autowired
    private LotteryActivityipService lotteryActivityipService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false)String activityId ,
                                     @RequestParam(required = false)String ip ,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page, @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>();
        if(activityId != null && !activityId.isEmpty()){            map.put("activityId",activityId);        }
        if(ip != null && !ip.isEmpty()){            map.put("ip",ip);        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<LotteryActivityipBO> list = lotteryActivityipService.selectList(map);
        LOGGER.info("selectList:{}", list);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody LotteryActivityipBO lotteryActivityipBO) {
        LOGGER.info("insert:{}", lotteryActivityipBO);
        LotteryActivityipBO returnObj = lotteryActivityipService.insert(lotteryActivityipBO);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody LotteryActivityipBO lotteryActivityipBO,
                                 @PathVariable String id) {
        LOGGER.info("update：{} id:{}", lotteryActivityipBO, id);
        LotteryActivityipBO returnObj = lotteryActivityipService.update(lotteryActivityipBO, id);
        LOGGER.info("{}", returnObj);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("deleteDo:{}", id);
        lotteryActivityipService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LotteryActivityipBO returnObj = lotteryActivityipService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }
}
