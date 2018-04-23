package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.VipPrivilege;
import com.abc12366.uc.model.bo.VipPrivilegeBO;
import com.abc12366.uc.service.VipPrivilegeService;
import com.github.pagehelper.PageInfo;
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
 * 会员特权控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-08 4:02 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/uvip/privilege", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class VipPrivilegeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VipPrivilegeController.class);

    @Autowired
    private VipPrivilegeService vipPrivilegeService;

    /**
     * 查询会员特权列表
     *
     * @param name   特权名称
     * @param code   特权代码
     * @param status 状态
     * @param page   当前页
     * @param size   每页大小
     * @return 会员特权列表
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String code,
                                     @RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("code", code);
        map.put("status", status);
        LOGGER.info("{}:{}:{}", map, page, size);

        List<VipPrivilege> dataList = vipPrivilegeService.selectList(map, page, size);
        PageInfo<VipPrivilege> pageInfo = new PageInfo<>(dataList);

        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 根据ID查看会员特权
     *
     * @param id PK
     * @return 会员特权对象
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        VipPrivilege vipPrivilege = vipPrivilegeService.selectOne(id);
        LOGGER.info("{}", vipPrivilege);
        return ResponseEntity.ok(Utils.kv("data", vipPrivilege));
    }

    /**
     * 新增会员特权对象
     *
     * @param vipPrivilegeBO 会员特权对象
     * @return 会员特权对象
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody VipPrivilegeBO vipPrivilegeBO) {
        LOGGER.info("{}", vipPrivilegeBO);

        VipPrivilege data = vipPrivilegeService.insert(vipPrivilegeBO);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }

    /**
     * 修改会员特权对象
     *
     * @param vipPrivilegeBO 会员特权对象
     * @param id             PK
     * @return 会员特权对象
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody VipPrivilegeBO vipPrivilegeBO,
                                 @PathVariable String id) {
        vipPrivilegeBO.setId(id);
        LOGGER.info("{}", vipPrivilegeBO);

        VipPrivilege data = vipPrivilegeService.update(vipPrivilegeBO);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }

    /**
     * 根据ID删除会员特权对象
     *
     * @param id PK
     * @return true or false
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        boolean flag = vipPrivilegeService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", flag));
    }

    /**
     * 启用、禁用会员特权接口
     *
     * @param id PK
     * @return 无
     */
    @PutMapping(path = "/switch/{id}")
    public ResponseEntity enableOrDisable(@PathVariable String id) {
        LOGGER.info("{}", id);
        vipPrivilegeService.enableOrDisable(id);
        return ResponseEntity.ok(Utils.kv());
    }
}
