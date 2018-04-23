package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.*;
import com.abc12366.cms.service.EventApplyService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动报名管理模块
 *
 * @author xieyanmao
 * @create 2017-05-08
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/eventApply", headers = Constant.VERSION_HEAD + "=1")
public class EventApplyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventApplyController.class);

    @Autowired
    private EventApplyService eventApplyService;

    /**
     * 活动报名列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "eventId", required = false) String eventId,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "tel", required = false) String tel,
                                     @RequestParam(value = "email", required = false) String email,
                                     @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("eventId", eventId);//活动ID
        //姓名，手机号，邮箱
        dataMap.put("name", name);
        dataMap.put("tel", tel);
        dataMap.put("email", email);
        dataMap.put("status", status);//状态
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<EventApplyBo> dataList = eventApplyService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 活动报名统计
     */
    @GetMapping(path = "/selectbmtj")
    public ResponseEntity selectbmtj(@RequestParam(value = "eventId", required = false) String eventId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("eventId", eventId);//活动ID
        EventbmtjBo data = eventApplyService.selectbmtj(dataMap);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }

    /**
     * 活动浏览统计
     */
    @GetMapping(path = "/selectlltj")
    public ResponseEntity selectlltj(@RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "eventId", required = false) String eventId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("eventId", eventId);//活动ID
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            if (startTime != null && !"".equals(startTime)) {
//                Date startTime1 = sdf.parse(startTime);
//                dataMap.put("startTime", startTime1.getTime() / 1000);
//            }
//            if (endTime != null && !"".equals(endTime)) {
//                Date startTime2 = sdf.parse(endTime);
//                dataMap.put("endTime", startTime2.getTime() / 1000);
//            }
//        } catch (ParseException e) {
//            LOGGER.error("时间类转换异常：{}", e);
//            throw new RuntimeException("时间类型转换异常：{}", e);
//        }
        dataMap.put("startTime",startTime);
        dataMap.put("endTime",endTime);
        EventlltjListBo data = eventApplyService.selectlltj(dataMap,type);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }

    /**
     * 活动报名新增
     */
    @PostMapping
    public ResponseEntity save(@RequestBody EventApplySaveBo eventApplySaveBo) {
        LOGGER.info("{}", eventApplySaveBo);
        //新增活动报名信息
        eventApplySaveBo = eventApplyService.save(eventApplySaveBo);
        LOGGER.info("{}", eventApplySaveBo);
        return ResponseEntity.ok(Utils.kv("data", eventApplySaveBo));
    }

    /**
     * 查询单个活动报名信息
     */
    @GetMapping(path = "/{applyId}")
    public ResponseEntity selectOne(@PathVariable String applyId) {
        LOGGER.info("{}", applyId);
        //查询活动报名信息
        EventApplySaveBo eventApplySaveBo = eventApplyService.selectEventApply(applyId);
        LOGGER.info("{}", eventApplySaveBo);
        return ResponseEntity.ok(Utils.kv("data", eventApplySaveBo));
    }

    /**
     * 更新活动报名信息
     */
    @PutMapping(path = "/{applyId}")
    public ResponseEntity update(@PathVariable String applyId,
                                 @Valid @RequestBody EventApplySaveBo eventApplySaveBo) {

        LOGGER.info("{}", eventApplySaveBo);
        //更新活动报名信息
        eventApplySaveBo = eventApplyService.update(eventApplySaveBo);
        LOGGER.info("{}", eventApplySaveBo);
        return ResponseEntity.ok(Utils.kv("data", eventApplySaveBo));
    }

    /**
     * 删除活动报名信息
     */
    @DeleteMapping(path = "/{applyId}")
    public ResponseEntity delete(@PathVariable String applyId) {
        LOGGER.info("{}", applyId);
        //删除活动报名信息
        String rtn = eventApplyService.delete(applyId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 批量删除活动报名信息
     */
    @PostMapping(path = "/deleteList")
    public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //删除活动报名信息
        String rtn = eventApplyService.deleteList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }

    /**
     * 批量审批活动报名信息
     */
    @PutMapping(path = "/updateStatusList")
    public ResponseEntity updateStatusList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //审批活动报名信息
        String rtn = eventApplyService.updateStatusList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }

    /**
     * 批量审批拒绝活动报名信息
     */
    @PutMapping(path = "/updateStatusNoList")
    public ResponseEntity updateStatusNoList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //审批活动报名信息
        String rtn = eventApplyService.updateStatusNoList(idsBo.getIds(),idsBo.getText());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }



}
