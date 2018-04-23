package com.abc12366.uc.web;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-13
 */


import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.LotteryTimeBO;
import com.abc12366.uc.service.LotteryTimeService;
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
@RequestMapping(path = "/lotterytime", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LotteryTimeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryTimeController.class);
    @Autowired
    private LotteryTimeService lotteryTimeService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false)String activityId ,@RequestParam(required = false, defaultValue = Constant.pageNum) int page, @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>();
        if(activityId != null && !activityId.isEmpty()){
            map.put("activityId",activityId);
        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<LotteryTimeBO> list = lotteryTimeService.selectList(map);
        LOGGER.info("selectList:{}", list);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody LotteryTimeBO lotteryTimeBO) {
        LOGGER.info("insert:{}", lotteryTimeBO);
        LotteryTimeBO returnObj = lotteryTimeService.insert(lotteryTimeBO);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody LotteryTimeBO lotteryTimeBO,
                                 @PathVariable String id) {
        LOGGER.info("update：{} id:{}", lotteryTimeBO, id);
        LotteryTimeBO returnObj = lotteryTimeService.update(lotteryTimeBO, id);
        LOGGER.info("{}", returnObj);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("deleteDo:{}", id);
        lotteryTimeService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LotteryTimeBO returnObj = lotteryTimeService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }
}
