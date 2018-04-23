package com.abc12366.uc.web;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.LotteryActivityprizeBO;
import com.abc12366.uc.service.LotteryActivityprizeService;
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
@RequestMapping(path = "/lotteryactivityprize", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LotteryActivityprizeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryActivityprizeController.class);
    @Autowired
    private LotteryActivityprizeService lotteryActivityprizeService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false)String activityId ,@RequestParam(required = false, defaultValue = Constant.pageNum) int page, @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>();
        if(activityId != null && !activityId.isEmpty()){
            map.put("activityId",activityId);
        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<LotteryActivityprizeBO> list = lotteryActivityprizeService.selectList(map);
        LOGGER.info("selectList:{}", list);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody LotteryActivityprizeBO lotteryActivityprizeBO) {
        LOGGER.info("insert:{}", lotteryActivityprizeBO);
        lotteryActivityprizeBO.setBalance(0);
        LotteryActivityprizeBO returnObj = lotteryActivityprizeService.insert(lotteryActivityprizeBO);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody LotteryActivityprizeBO lotteryActivityprizeBO,
                                 @PathVariable String id) {
        LOGGER.info("update：{} id:{}", lotteryActivityprizeBO, id);
        LotteryActivityprizeBO returnObj = lotteryActivityprizeService.update(lotteryActivityprizeBO, id);
        LOGGER.info("{}", returnObj);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("deleteDo:{}", id);
        lotteryActivityprizeService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LotteryActivityprizeBO returnObj = lotteryActivityprizeService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }
}
