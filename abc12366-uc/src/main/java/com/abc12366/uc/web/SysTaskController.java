package com.abc12366.uc.web;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.SysTaskBO;
import com.abc12366.uc.model.bo.SysTaskInsertAndUpdateBO;
import com.abc12366.uc.model.bo.SysTaskListBO;
import com.abc12366.uc.service.SysTaskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-23
 * Time: 17:40
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SysTaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysTaskController.class);

    @Autowired
    private SysTaskService sysTaskService;

    /**
     * 查询系统任务列表
     * @param name 系统任务名称
     * @param type 系统任务类型
     * @param page 页数
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    @GetMapping(path = "/task")
    public ResponseEntity selectList(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String type,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}", name, type, page, size);
        Map<String, String> map = new HashMap<>();
        if (name != null && StringUtils.isEmpty(name)) {
            name = null;
        }
        if (type != null && StringUtils.isEmpty(type)) {
            type = null;
        }
        map.put("name", name);
        map.put("type", type);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<SysTaskBO> taskList = sysTaskService.selectList(map);
        return (taskList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) taskList, "total", ((Page) taskList).getTotal()));
    }

    /**
     * 查询系统任务列表：只查询启用的
     * @param name 系统任务名称
     * @param type 系统任务类型
     * @param page 页数
     * @param size 每页数据条数
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    @GetMapping(path = "/tasks")
    public ResponseEntity selectDeployedList(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String type,
                                             @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                             @RequestParam(required = false, defaultValue = Constant.pageSize) int
                                                         size) {
        LOGGER.info("{}:{}:{}:{}", name, type, page, size);
        Map<String, String> map = new HashMap<>();
        if (name != null && StringUtils.isEmpty(name)) {
            name = null;
        }
        if (type != null && StringUtils.isEmpty(type)) {
            type = null;
        }
        map.put("name", name);
        map.put("type", type);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<SysTaskBO> taskList = sysTaskService.selectDeployedList(map);
        return (taskList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) taskList, "total", ((Page) taskList).getTotal()));
    }

    /**
     * 根据ID查询一条系统任务
     * @param id 系统任务ID
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */

    @GetMapping(path = "/task/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        SysTaskBO sysTaskBO = sysTaskService.selectOne(id);
        LOGGER.info("{}", sysTaskBO);
        return ResponseEntity.ok(Utils.kv("data", sysTaskBO));
    }

    /**
     * 增加一条系统任务
     * @param sysTaskInsertBO {@linkplain com.abc12366.uc.model.bo.SysTaskInsertAndUpdateBO}
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */

    @PostMapping(path = "/task")
    public ResponseEntity insert(@Valid @RequestBody SysTaskInsertAndUpdateBO sysTaskInsertBO) {
        LOGGER.info("新增系统任务：{}", sysTaskInsertBO);
        SysTaskBO sysTaskBO = sysTaskService.insert(sysTaskInsertBO);
        LOGGER.info("{}", sysTaskBO);
        return ResponseEntity.ok(Utils.kv("data", sysTaskBO));
    }

    /**
     * 根据ID修改一条系统任务
     * @param sysTaskUpdateBO {@linkplain com.abc12366.uc.model.bo.SysTaskInsertAndUpdateBO}
     * @param id 系统任务ID
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    @PutMapping(path = "/task/{id}")
    public ResponseEntity update( @RequestBody SysTaskInsertAndUpdateBO sysTaskUpdateBO, @PathVariable String
            id) {
        LOGGER.info("{}:{}", sysTaskUpdateBO, id);
        SysTaskBO sysTaskBO = sysTaskService.update(sysTaskUpdateBO, id);
        LOGGER.info("{}", sysTaskBO);
        return ResponseEntity.ok(Utils.kv("data", sysTaskBO));
    }

    /**
     * 根据ID删除一条系统任务（逻辑删除）
     * @param id 系统任务ID
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */

    @DeleteMapping(path = "/task/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        sysTaskService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    
}