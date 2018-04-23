package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.ExperienceLevelBO;
import com.abc12366.uc.model.bo.ExperienceLevelInsertAndUpdateBO;
import com.abc12366.uc.service.ExperienceLevelService;
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
 * 经验值等级接口控制器
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 15:44
 */
@RestController
@RequestMapping(path = "/uexp/level", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ExperienceLevelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceLevelController.class);

    @Autowired
    private ExperienceLevelService experienceLevelService;

    /**
     * 查询当前经验值等级列表
     * @param name
     * @param status
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}", name, status, page, size);
        Map<String, Object> map = new HashMap<>();
        if (name != null && StringUtils.isEmpty(name)) {
            name = null;
        }
        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        map.put("name", name);
        map.put("status", status);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ExperienceLevelBO> levelList = experienceLevelService.selectList(map);
        LOGGER.info("{}", levelList);
        return (levelList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) levelList, "total", ((Page) levelList).getTotal()));
    }

    /**
     * 根据经验值等级ID查询一条经验值等级信息
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        ExperienceLevelBO experienceLevelQueryBO = experienceLevelService.selectOne(id);
        LOGGER.info("{}", experienceLevelQueryBO);
        return ResponseEntity.ok(Utils.kv("data", experienceLevelQueryBO));
    }

    /**
     * 增加一条经验值等级
     * @param experienceLevelInsertBO
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody ExperienceLevelInsertAndUpdateBO experienceLevelInsertBO) {
        LOGGER.info("{}", experienceLevelInsertBO);
        ExperienceLevelBO experienceLevelBOReturn = experienceLevelService.insert(experienceLevelInsertBO);
        LOGGER.info("{}", experienceLevelBOReturn);
        return ResponseEntity.ok(Utils.kv("data", experienceLevelBOReturn));
    }

    /**
     * 修改经验值等级
     * @param experienceLevelUpdateBO
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody ExperienceLevelInsertAndUpdateBO experienceLevelUpdateBO,
                                 @PathVariable String id) {
        LOGGER.info("{}:{}", experienceLevelUpdateBO, id);
        ExperienceLevelBO experienceLevelReturn = experienceLevelService.update(experienceLevelUpdateBO, id);
        LOGGER.info("{}", experienceLevelReturn);
        return ResponseEntity.ok(Utils.kv("data", experienceLevelReturn));
    }

    /**
     * 删除经验值等级
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        experienceLevelService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 启用、禁用等级勋章接口
     * @param id
     * @param status
     * @return
     */
    @PutMapping(path = "/{id}/{status}")
    public ResponseEntity enableOrDisable(@PathVariable String id, @PathVariable String status) {
        LOGGER.info("{}:{}", id, status);
        experienceLevelService.enableOrDisable(id, status);
        return ResponseEntity.ok(Utils.kv());
    }
}
