package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelInsertBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelUpdateBO;
import com.abc12366.uc.service.VipPrivilegeLevelService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin:lingsuzhi0 <554600654@qq.com.com>
 * Date: 2017-08-11
 * Time: 9:13
 */
@RestController
@RequestMapping(path = "/uvip/privilegelevel", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class VipPrivilegeLevelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VipPrivilegeLevelController.class);

    @Autowired
    private VipPrivilegeLevelService vipPrivilegeLevelService;

    //指定权益id查询
    @GetMapping(path = "/privilege")
    public ResponseEntity selectListPrivilege(@RequestParam(required = true) String privilege,
                                              @RequestParam(required = false) Boolean status,
                                              @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                              @RequestParam(required = false, defaultValue = Constant.pageSize) int
                                                          size) {
        LOGGER.info("{}:{}:{}:{}:{}", privilege, status, page, size);
        Map<String, Object> map = new HashMap<>();

        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        map.put("status", status);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<VipPrivilegeLevelBO> privilegeList = vipPrivilegeLevelService.selectListbyPrivlege(privilege);
        LOGGER.info("{}", privilegeList);
        return (privilegeList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) privilegeList, "total", ((Page) privilegeList).getTotal
                        ()));

    }

    //指定会员id 权益id 查询结果
    @GetMapping(path = "/{levelId}/{privilege}")
    public ResponseEntity select(@PathVariable String levelId,
                                 @PathVariable String privilege) {
        LOGGER.info("{}----------{}", levelId, privilege);
        VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
        obj.setLevelId(levelId);
        obj.setPrivilegeId(privilege);

        VipPrivilegeLevelBO findObj = vipPrivilegeLevelService.selectLevelIdPrivilegeId(obj);
        return ResponseEntity.ok(Utils.kv("data", findObj));
    }

    /**
     * 查询指定会员名称对应的权益
     */
    @GetMapping(path = "/levelname")
    public ResponseEntity selectLevelname(@RequestParam(required = true) String levelname) {
        LOGGER.info("{}", levelname);


        List<VipPrivilegeLevelBO> privilegeList = vipPrivilegeLevelService.selectListByLevelName(levelname);
        LOGGER.info("{}", privilegeList);
        return (privilegeList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", privilegeList, "total", privilegeList.size()));
    }

    //查询指定会员对应的权益
    @GetMapping(path = "/level")
    public ResponseEntity selectList(@RequestParam(required = true) String level,
                                     @RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}", level, status, page, size);
        Map<String, Object> map = new HashMap<>();

        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        map.put("status", status);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<VipPrivilegeLevelBO> privilegeList = vipPrivilegeLevelService.selectListByLevelId(level);
        LOGGER.info("{}", privilegeList);
        return (privilegeList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) privilegeList, "total", ((Page) privilegeList).getTotal
                        ()));
    }

    //查询全部
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", status, page, size);
        Map<String, Object> map = new HashMap<>();

        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        map.put("status", status);
        List<List<VipPrivilegeLevelBO>> privilegeList = vipPrivilegeLevelService.selectList();
        LOGGER.info("{}", privilegeList);
        return (privilegeList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", privilegeList));
    }

    //批量设置会员对应的权益 需要传一个list
    @PostMapping(value = "/updates")
    @ResponseBody
    public ResponseEntity updates(@Valid @RequestBody List<VipPrivilegeLevelBO> list) {
        LOGGER.info("{}", list);
        Integer returnI = vipPrivilegeLevelService.updates(list);
        return ResponseEntity.ok(Utils.kv("count", returnI));
    }

    //批量设置会员对应的权益 需要传一个list
    @PostMapping(value = "/updateprivilege/{privilege}")
    @ResponseBody
    public ResponseEntity updateByPrivilege(@PathVariable String privilege, @Valid @RequestBody
    List<VipPrivilegeLevelBO> list) {
        LOGGER.info("{}", list);
        Integer returnI = vipPrivilegeLevelService.updateByPrivilege(privilege, list);
        return ResponseEntity.ok(Utils.kv("count", returnI));
    }

    //批量设置会员对应的权益 需要传一个list
    @PostMapping(value = "/updatelevel/{level}")
    @ResponseBody
    public ResponseEntity updateByLevel(@PathVariable String level, @Valid @RequestBody List<VipPrivilegeLevelBO>
            list) {
        LOGGER.info("{}", list);
        Integer returnI = vipPrivilegeLevelService.updateByLevel(level, list);
        return ResponseEntity.ok(Utils.kv("count", returnI));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelService.selectOne(id);
        LOGGER.info("{}", vipPrivilegeLevelBO);
        return ResponseEntity.ok(Utils.kv("data", vipPrivilegeLevelBO));
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody VipPrivilegeLevelInsertBO vipPrivilegeLevelInsertBO) {
        LOGGER.info("{}", vipPrivilegeLevelInsertBO);
        VipPrivilegeLevelBO vipPrivilegeLevelBOReturn = vipPrivilegeLevelService.insert(vipPrivilegeLevelInsertBO);
        LOGGER.info("{}", vipPrivilegeLevelBOReturn);
        return ResponseEntity.ok(Utils.kv("data", vipPrivilegeLevelBOReturn));
    }

    //先查询，不存在就新建 存在就修改
    @PutMapping
    public ResponseEntity add(@Valid @RequestBody VipPrivilegeLevelBO vipPrivilegeLevelBO) {
        LOGGER.info("{}", vipPrivilegeLevelBO);
        VipPrivilegeLevelBO vipPrivilegeLevelBOReturn = vipPrivilegeLevelService.addOrUpdate(vipPrivilegeLevelBO);
        LOGGER.info("{}", vipPrivilegeLevelBOReturn);
        return ResponseEntity.ok(Utils.kv("data", vipPrivilegeLevelBOReturn));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody VipPrivilegeLevelUpdateBO vipPrivilegeLevelUpdateBO,
                                 @PathVariable String id) {
        LOGGER.info("{}", vipPrivilegeLevelUpdateBO);
        VipPrivilegeLevelBO vipPrivilegeLevelBOReturn = vipPrivilegeLevelService.update(vipPrivilegeLevelUpdateBO, id);
        LOGGER.info("{}", vipPrivilegeLevelBOReturn);
        return ResponseEntity.ok(Utils.kv("data", vipPrivilegeLevelBOReturn));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        vipPrivilegeLevelService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    //启用、禁用会员特权接口
    @PutMapping(path = "/{id}/{status}")
    public ResponseEntity enableOrDisable(@PathVariable String id, @PathVariable String status) {
        LOGGER.info("{}:{}", id, status);
        vipPrivilegeLevelService.enableOrDisable(id, status);
        return ResponseEntity.ok(Utils.kv());
    }
}
