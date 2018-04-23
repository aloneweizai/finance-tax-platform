package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.VipLogBO;
import com.abc12366.uc.model.bo.VipLogOrderBO;
import com.abc12366.uc.service.VipLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-19
 * Time: 15:33
 */
@RestController
@RequestMapping(path = "/uvip/log", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class VipLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VipLogController.class);

    @Autowired
    private VipLogService vipLogService;

    /**
     * 会员日志查询
     * @param userId 用户ID
     * @param page 页数
     * @param size 大小
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam String userId,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<VipLogBO> logList = vipLogService.selectList(userId);
        LOGGER.info("{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }

    /**
     * 会员日志列表
     * @param userId 用户ID
     * @param page 页数
     * @param size 大小
     * @return
     */
    @GetMapping(path = "/order")
    public ResponseEntity selectListByOrder(@RequestParam String userId,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<VipLogOrderBO> logList = vipLogService.selectListByOrder(userId);
        LOGGER.info("{}", logList);
        return (logList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) logList, "total", ((Page) logList).getTotal()));
    }

/*    @PostMapping
    public ResponseEntity insert(@RequestBody VipLogBO vipLogBO){
        VipLogBO vipLogBO1 = vipLogService.insert(vipLogBO);
        return ResponseEntity.ok(vipLogBO1);
    }*/
}
