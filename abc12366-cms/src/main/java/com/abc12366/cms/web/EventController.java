package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.EventListBo;
import com.abc12366.cms.model.bo.EventSaveBo;
import com.abc12366.cms.service.EventService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 活动管理模块
 *
 * @author xieyanmao
 * @create 2017-05-08
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/event", headers = Constant.VERSION_HEAD + "=1")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 查询活动列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "begintime", required = false) String begintime,
                                     @RequestParam(value = "endtime", required = false) String endtime,
                                     @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//标题
        dataMap.put("status", status);//状态
        dataMap.put("category", category);//活动分类
        dataMap.put("begintime", begintime);//开始时间
        dataMap.put("endtime", endtime);//结束时间
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<EventListBo> dataList = eventService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询活动列表信息
     */
    @GetMapping(path = "/selectEventList")
    public ResponseEntity selectEventList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                          @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                          @RequestParam(value = "title", required = false) String title,
                                          @RequestParam(value = "category", required = false) String category,
                                          @RequestParam(value = "begintime", required = false) String begintime,
                                          @RequestParam(value = "endtime", required = false) String endtime,
                                          @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//标题
        dataMap.put("status", status);//状态
        dataMap.put("category", category);//活动分类
        dataMap.put("begintime", begintime);//开始时间
        dataMap.put("endtime", endtime);//结束时间
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<EventListBo> dataList = eventService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 新增活动
     */
    @PostMapping
    public ResponseEntity save(@RequestBody EventSaveBo eventSaveBo) {
        LOGGER.info("{}", eventSaveBo);
        //新增活动信息
        eventSaveBo = eventService.save(eventSaveBo);
        LOGGER.info("{}", eventSaveBo);
        redisTemplate.delete(eventSaveBo.getEvent().getCategory() + "_ToponeForqt");
        return ResponseEntity.ok(Utils.kv("data", eventSaveBo));
    }

    /**
     * 查询单个活动信息
     */
    @GetMapping(path = "/{eventId}")
    public ResponseEntity selectOne(@PathVariable String eventId) {
        LOGGER.info("{}", eventId);
        //查询活动信息
        EventSaveBo eventSaveBo = eventService.selectEvent(eventId);
        LOGGER.info("{}", eventSaveBo);
        return ResponseEntity.ok(Utils.kv("data", eventSaveBo));
    }

    /**
     * 查询最新的一个活动信息
     */
    @GetMapping(path = "/topone/{category}")
    public ResponseEntity selecttopone(@PathVariable String category) {
        //查询活动信息
        EventSaveBo eventSaveBo = null;
        if (redisTemplate.hasKey(category + "_ToponeForqt")) {
            eventSaveBo = JSONObject.parseObject(redisTemplate.opsForValue().get(category + "_ToponeForqt"),
                    EventSaveBo.class);
        } else {
            eventSaveBo = eventService.selecttopone(category);
            redisTemplate.opsForValue().set(category + "_ToponeForqt", JSONObject.toJSONString(eventSaveBo),
                    RedisConstant.DAY_1, TimeUnit.DAYS);
        }
        LOGGER.info("{}", eventSaveBo);
        return ResponseEntity.ok(Utils.kv("data", eventSaveBo));
    }

    /**
     * 更新活动
     */
    @PutMapping(path = "/{eventId}")
    public ResponseEntity update(@PathVariable String eventId,
                                 @Valid @RequestBody EventSaveBo eventSaveBo) {

        LOGGER.info("{}", eventSaveBo);
        //更新活动信息
        eventSaveBo = eventService.update(eventSaveBo);
        EventSaveBo eventBo = eventService.selectEvent(eventId);
        redisTemplate.delete(eventBo.getEvent().getCategory() + "_ToponeForqt");
        LOGGER.info("{}", eventSaveBo);
        return ResponseEntity.ok(Utils.kv("data", eventSaveBo));
    }

    /**
     * 删除活动
     */
    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity delete(@PathVariable String eventId) {
        LOGGER.info("{}", eventId);
        //删除活动信息
        EventSaveBo eventBo = eventService.selectEvent(eventId);
        String rtn = eventService.delete(eventId);
        redisTemplate.delete(eventBo.getEvent().getCategory() + "_ToponeForqt");
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
