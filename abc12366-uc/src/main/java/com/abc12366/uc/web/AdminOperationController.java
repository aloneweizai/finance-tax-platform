package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.AdminModifyUphoneLogList;
import com.abc12366.uc.model.bo.AdminModifyUserPhoneLogBO;
import com.abc12366.uc.service.admin.AdminOperationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User用户统计
 *
 * @author lizhongwei
 * @create 2017-11-21 3:18 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/admin/operation", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AdminOperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminOperationController.class);

    @Autowired
    private AdminOperationService adminOperationService;

    /**
     * 管理员修改用户手机日志
     * @param logBO AdminModifyUserPhoneLogBO
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody AdminModifyUserPhoneLogBO logBO){
        LOGGER.info("新增管理员修改用户手机号码日志：{}",logBO);
        AdminModifyUserPhoneLogBO phoneLogBO = adminOperationService.insert(logBO);
        return ResponseEntity.ok(Utils.kv("data",phoneLogBO));
    }

    /**
     * 查询管理员修改用户手机日志
     * @param userId 用户ID
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String userId,
                                     @RequestParam(required = false,defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false,defaultValue = Constant.pageSize) int size){
        LOGGER.info("查询管理员修改用户手机号码日志：{}:{}:{}",userId,page,size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<AdminModifyUphoneLogList> lists = adminOperationService.selectList(userId);
        LOGGER.info("查询管理员修改用户手机号码日志结果：{}",lists);
        return (lists == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) lists, "total", ((Page) lists).getTotal()));
    }
}