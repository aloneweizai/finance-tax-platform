package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumOrderBo;
import com.abc12366.bangbang.service.CurrOrderService;
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
 * 学堂课程订单管理模块
 *
 * @author xieyanmao
 * @create 2017-08-11
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/currOrder", headers = Constant.VERSION_HEAD + "=1")
public class CurrOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrOrderController.class);

    @Autowired
    private CurrOrderService currOrderService;

    /**
     * 课程订单列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "curriculumId", required = false) String curriculumId,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "orderStatus", required = false) String orderStatus,
                                     @RequestParam(value = "nickname", required = false) String nickname,
                                     @RequestParam(value = "begintime", required = false) String begintime,
                                     @RequestParam(value = "endtime", required = false) String endtime) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId",curriculumId);
        dataMap.put("nickname",nickname);
        dataMap.put("userId",userId);
        dataMap.put("orderStatus",orderStatus);
        if(begintime != null && !"".equals(begintime)){
            dataMap.put("begintime", begintime);//开始时间
        }
        if(endtime != null && !"".equals(endtime)){
            dataMap.put("endtime", endtime);//结束时间
        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumOrderBo> dataList = currOrderService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 课程订单新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumOrderBo orderBo) {
        //新增课程学习信息
        orderBo = currOrderService.save(orderBo);
        return ResponseEntity.ok(Utils.kv("data", orderBo));
    }

    /**
     * 查询单个课程订单信息
     */
    @GetMapping(path = "/{orderId}")
    public ResponseEntity selectOne(@PathVariable String orderId) {
        //查询课程订单信息
        CurriculumOrderBo orderBo = currOrderService.selectOrder(orderId);
        return ResponseEntity.ok(Utils.kv("data", orderBo));
    }

    /**
     * 更新课程订单信息
     */
    @PutMapping(path = "/{orderId}")
    public ResponseEntity update(@PathVariable String orderId,
                                 @Valid @RequestBody CurriculumOrderBo orderBo) {
        //更新课程订单信息
        orderBo = currOrderService.update(orderBo);
        return ResponseEntity.ok(Utils.kv("data", orderBo));
    }

    /**
     * 更新课程订单状态
     *
     * @param status
     * @param orderId
     * @return
     */
    @PutMapping(path = "/updateStatus/{orderId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("orderId") String orderId) {
        currOrderService.updateStatus(orderId, status);
        return ResponseEntity.ok(Utils.kv("data", orderId));
    }

    /**
     * 删除课程订单信息
     */
    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity delete(@PathVariable String orderId) {
        //删除课程订单信息
        String rtn = currOrderService.delete(orderId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
