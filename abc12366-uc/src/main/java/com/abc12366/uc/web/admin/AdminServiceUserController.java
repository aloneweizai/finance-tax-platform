package com.abc12366.uc.web.admin;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.bo.AdminServiceUserBo;
import com.abc12366.uc.service.admin.AdminServUserService;
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
 * 客服经理会员设置管理模块
 *
 * @author xieyanmao
 * @create 2017-09-25
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/adminServiceUser", headers = Constant.VERSION_HEAD + "=1")
public class AdminServiceUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceUserController.class);

    @Autowired
    private AdminServUserService adminServUserService;

    /**
     * 客服经理会员设置列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "username", required = false) String username) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", username);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<AdminServiceUserBo> dataList = adminServUserService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 客服经理会员设置新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody AdminServiceUserBo adminServiceUserBo) {
        //新增客服经理会员设置信息
        adminServiceUserBo = adminServUserService.save(adminServiceUserBo);
        return ResponseEntity.ok(Utils.kv("data", adminServiceUserBo));
    }

    /**
     * 查询单个客服经理会员设置信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询客服经理会员设置信息
        AdminServiceUserBo adminServiceUserBo = adminServUserService.selectAdminService(id);
        return ResponseEntity.ok(Utils.kv("data", adminServiceUserBo));
    }

    /**
     * 更新客服经理会员设置信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody AdminServiceUserBo adminServiceUserBo) {
        //更新客服经理会员设置信息
        adminServiceUserBo = adminServUserService.update(adminServiceUserBo);
        return ResponseEntity.ok(Utils.kv("data", adminServiceUserBo));
    }

    /**
     * 删除客服经理会员设置信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除客服经理会员设置信息
        String rtn = adminServUserService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
