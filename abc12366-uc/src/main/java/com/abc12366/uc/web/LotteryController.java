package com.abc12366.uc.web;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-20
 */


import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.LotteryBO;
import com.abc12366.uc.service.LotteryService;
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
@RequestMapping(path = "/lottery", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LotteryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);
    @Autowired
    private LotteryService lotteryService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String name,@RequestParam(required = false, defaultValue = Constant.pageNum) int page, @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>();
        if(name != null && !name.isEmpty()){
            map.put("name",name);
        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<LotteryBO> list = lotteryService.selectList(map);
        LOGGER.info("selectList:{}", list);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody LotteryBO lotteryBO) {
        LOGGER.info("insert:{}", lotteryBO);
        LotteryBO returnObj = lotteryService.insert(lotteryBO);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody LotteryBO lotteryBO,
                                 @PathVariable String id) {
        LOGGER.info("update：{} id:{}", lotteryBO, id);
        LotteryBO returnObj = lotteryService.update(lotteryBO, id);
        LOGGER.info("{}", returnObj);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("deleteDo:{}", id);
        lotteryService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LotteryBO returnObj = lotteryService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

}
