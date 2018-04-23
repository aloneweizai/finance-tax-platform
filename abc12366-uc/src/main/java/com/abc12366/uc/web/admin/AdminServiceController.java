package com.abc12366.uc.web.admin;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.bo.AdminServiceBo;
import com.abc12366.uc.service.admin.AdminServService;
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
 * 客服经理管理模块
 *
 * @author xieyanmao
 * @create 2017-09-25
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/adminService", headers = Constant.VERSION_HEAD + "=1")
public class AdminServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceController.class);

    @Autowired
    private AdminServService adminServService;

    /**
     * 客服经理列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "username", required = false) String username) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", username);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<AdminServiceBo> dataList = adminServService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 客服经理新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody AdminServiceBo adminServiceBo) {
        //新增客服经理信息
        adminServiceBo = adminServService.save(adminServiceBo);
        return ResponseEntity.ok(Utils.kv("data", adminServiceBo));
    }

    /**
     * 查询单个客服经理信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询客服经理信息
        AdminServiceBo adminServiceBo = adminServService.selectAdminService(id);
        return ResponseEntity.ok(Utils.kv("data", adminServiceBo));
    }

    /**
     * 更新客服经理信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody AdminServiceBo adminServiceBo) {
        //更新客服经理信息
        adminServiceBo = adminServService.update(adminServiceBo);
        return ResponseEntity.ok(Utils.kv("data", adminServiceBo));
    }

    /**
     * 删除客服经理信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除客服经理信息
        String rtn = adminServService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
