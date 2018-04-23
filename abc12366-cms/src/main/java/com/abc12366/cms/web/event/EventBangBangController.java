package com.abc12366.cms.web.event;

import com.abc12366.cms.model.event.EventApplyBbBo;
import com.abc12366.cms.model.event.EventIdBo;
import com.abc12366.cms.model.event.EventRecordBbBo;
import com.abc12366.cms.model.event.SingleEventBo;
import com.abc12366.cms.service.EventBangBangService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/9/13.
 */
@RestController
@RequestMapping(path = "/bangbang/event", headers = Constant.VERSION_HEAD + "=1")
public class EventBangBangController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventBangBangController.class);

    @Autowired
    private EventBangBangService eventService;

    @GetMapping(path = "/singleevent")
    public ResponseEntity singleevent(@RequestParam(defaultValue = "") String category) {
        Map map = new HashMap<>();
        map.put("category",category);
        SingleEventBo singleevent = eventService.singleEvent(map);
        return ResponseEntity.ok(Utils.kv("data", singleevent));
    }


    @GetMapping(path = "/singleeventlist")
    public ResponseEntity singleeventlist(@RequestParam(defaultValue = "") String category) {
        Map map=new HashMap();
        map.put("category",category);
        List<SingleEventBo> dataList = eventService.singleEventList(map);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }


    @PostMapping(path = "/saveeventrecord")
    public ResponseEntity saveeventrecord(@RequestBody EventRecordBbBo eventRecordBbBo) {
        LOGGER.info("{}", eventRecordBbBo);
        eventService.addEventRecord(eventRecordBbBo);
        return ResponseEntity.ok(Utils.kv("dataList", eventRecordBbBo));
    }


    @GetMapping(path = "/details/{eventid}")
    public ResponseEntity saveeventrecord(@PathVariable String eventid,
                                           @RequestParam(value = "userid", defaultValue = "") String userid) {
        LOGGER.info("{}", eventid);
        Map map=new HashMap();
        map.put("eventid",eventid);
        map.put("userid",userid);
        EventIdBo event = eventService.selectEventId(map);
        return ResponseEntity.ok(Utils.kv("data", event));
    }


    @PostMapping(path = "/saveEventApply")
    public ResponseEntity saveeventrecord( HttpServletRequest request,@RequestBody EventApplyBbBo eventApplyBbBo) {
        LOGGER.info("{}", eventApplyBbBo);
        eventApplyBbBo =eventService.saveEventApply(eventApplyBbBo,request);
        return ResponseEntity.ok(Utils.kv("data", eventApplyBbBo));
    }

}
