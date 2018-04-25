package com.abc12366.uc.web.points;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.UserPointsApply;
import com.abc12366.uc.model.bo.UserPointsApplyBO;
import com.abc12366.uc.service.UserPointsApplyService;
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
 * 积分返还控制器
 *
 * @author lizhongwei
 * @create 2017-04-24 2:51 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/points/pay", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class PointsPayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsPayController.class);

    @Autowired
    private UserPointsApplyService pointsApplyService;

    /**
     * 积分返还列表查询
     */
    @GetMapping
    public ResponseEntity selectUserPointsApplyList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                             @RequestParam(value = "phone", required = false) String phone,
                                             @RequestParam(value = "username", required = false) String username,
                                             @RequestParam(value = "status", required = false) String status,
                                             @RequestParam(value = "startTime", required = false) String startTime,
                                             @RequestParam(value = "endTime", required = false) String endTime) {
        Map<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("status",status);
        map.put("username",username);
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime",startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime",endTime);
        }
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UserPointsApply> userPointsPayList = pointsApplyService.selectUserPointsApplyList(map);
        LOGGER.info("{}", userPointsPayList);
        return (userPointsPayList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) userPointsPayList, "total", ((Page) userPointsPayList).getTotal()));
    }

    /**
     * 用户查询积分返还列表
     */
    @GetMapping("/user")
    public ResponseEntity selectUserPointsApplyList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                                    @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                                    @RequestParam(value = "status", required = false) String status,
                                                    @RequestParam(value = "id") String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UserPointsApply> userPointsList = pointsApplyService.selectUserPointsApplyList(map);
        LOGGER.info("{}", userPointsList);
        return (userPointsList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) userPointsList, "total", ((Page) userPointsList).getTotal()));
    }
    
    /**
     * 查看积分返还详情
     *
     * @param id 主键ID
     * @return ResponseEntity {@linkplain UserPointsApply UserPointsApply}响应实体
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable("id") String id) {
        UserPointsApply userPoints = pointsApplyService.selectById(id);
        LOGGER.info("{}", userPoints);
        return ResponseEntity.ok(Utils.kv("data", userPoints));
    }

    /**
     * 积分返还删除
     *
     * @param pointsNo 积分返还ID
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{pointsNo}")
    public ResponseEntity delete(@PathVariable("pointsNo") String pointsNo) {
        LOGGER.info("{}", pointsNo);
        pointsApplyService.delete(pointsNo);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 积分返还修改
     * @param pointsNo           积分返还ID
     * @return ResponseEntity {@linkplain UserPointsApply UserPointsApply}响应实体
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody UserPointsApplyBO userPoints, @PathVariable("pointsNo") String pointsNo) {
        LOGGER.info("{}", userPoints, pointsNo);
        userPoints.setPointsNo(pointsNo);
        UserPointsApplyBO userPointsBO = pointsApplyService.update(userPoints);
        LOGGER.info("{}", userPointsBO);
        return ResponseEntity.ok(Utils.kv("data", userPointsBO));
    }

    /**
     * 积分返还新增
     *
     * @return ResponseEntity UserPointsApplyBO实体
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody UserPointsApplyBO bo) {
        LOGGER.info("{}", bo);
        UserPointsApplyBO userPointsBO = pointsApplyService.insert(bo);
        LOGGER.info("{}", userPointsBO);
        return ResponseEntity.ok(Utils.kv("data", userPointsBO));
    }
}
