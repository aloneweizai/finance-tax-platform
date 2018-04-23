package com.abc12366.uc.web;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-19
 */

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.LotteryTemplateBO;
import com.abc12366.uc.service.LotteryTemplateService;
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
@RequestMapping(path = "/lotterytemplate", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LotteryTemplateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryTemplateController.class);
    @Autowired
    private LotteryTemplateService lotteryTemplateService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String name,@RequestParam(required = false, defaultValue = Constant.pageNum) int page, @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>();
        if(name != null && !name.isEmpty()){
            map.put("name",name);
        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<LotteryTemplateBO> list = lotteryTemplateService.selectList(map);
        LOGGER.info("selectList:{}", list);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody LotteryTemplateBO lotteryTemplateBO) {
        LOGGER.info("insert:{}", lotteryTemplateBO);
        LotteryTemplateBO returnObj = lotteryTemplateService.insert(lotteryTemplateBO);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody LotteryTemplateBO lotteryTemplateBO,
                                 @PathVariable String id) {
        LOGGER.info("update：{} id:{}", lotteryTemplateBO, id);
        LotteryTemplateBO returnObj = lotteryTemplateService.update(lotteryTemplateBO, id);
        LOGGER.info("{}", returnObj);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("deleteDo:{}", id);
        lotteryTemplateService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LotteryTemplateBO returnObj = lotteryTemplateService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", returnObj));
    }
}
