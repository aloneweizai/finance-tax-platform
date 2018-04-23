package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.VipLevelBO;
import com.abc12366.uc.model.bo.VipLevelInsertBO;
import com.abc12366.uc.model.bo.VipLevelUpdateBO;
import com.abc12366.uc.service.VipLevelService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 9:13
 */
@RestController
@RequestMapping(path = "/uvip/level", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class VipLevelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VipLevelController.class);

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 会员列表查询
     *
     * @param level  会员等级
     * @param status 状态
     * @param page   页数
     * @param size   大小
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String level,
                                     @RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}", level, status, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> map = new HashMap<>();
        if (level != null && StringUtils.isEmpty(level)) {
            level = null;
        }
        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        map.put("level", level);
        map.put("status", status);
        List<VipLevelBO> vipLevelBOList = vipLevelService.selectList(map);
        return (vipLevelBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) vipLevelBOList, "total", ((Page) vipLevelBOList)
                        .getTotal()));
    }

    /**
     * 查看单个会员
     *
     * @param id 会员id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        VipLevelBO vipLevelBO = vipLevelService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", vipLevelBO));
    }

    /**
     * 新增会员
     *
     * @param vipLevelInsertBO 会员信息
     * @return
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody VipLevelInsertBO vipLevelInsertBO) {
        LOGGER.info("{}", vipLevelInsertBO);
        VipLevelBO vipLevelBOReturn = vipLevelService.insert(vipLevelInsertBO);
        LOGGER.info("{}", vipLevelBOReturn);
        return ResponseEntity.ok(Utils.kv("data", vipLevelBOReturn));
    }

    /**
     * 更新会员
     *
     * @param vipLevelUpdateBO 会员信息
     * @param id               会员ID
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody VipLevelUpdateBO vipLevelUpdateBO, @PathVariable String id) {
        LOGGER.info("{}:{}", vipLevelUpdateBO, id);
        VipLevelBO vipLevelBO = vipLevelService.selectOne(id);
        VipLevelBO vipLevelBOReturn = vipLevelService.update(vipLevelUpdateBO, id);
        redisTemplate.delete(vipLevelBO.getLevelCode() + "_VipLevel");
        LOGGER.info("{}", vipLevelBOReturn);
        return ResponseEntity.ok(Utils.kv("data", vipLevelBOReturn));
    }

    /**
     * 删除会员
     *
     * @param id 会员id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        VipLevelBO vipLevelBO = vipLevelService.selectOne(id);
        vipLevelService.delete(id);
        redisTemplate.delete(vipLevelBO.getLevelCode() + "_VipLevel");
        return ResponseEntity.ok(Utils.kv());
    }

    //启用、禁用会员等级接口
    @PutMapping(path = "/{id}/{status}")
    public ResponseEntity enableOrDisable(@PathVariable String id, @PathVariable String status) {
        LOGGER.info("{}:{}", id, status);
        VipLevelBO vipLevelBO = vipLevelService.selectOne(id);
        vipLevelService.enableOrDisable(id, status);
        redisTemplate.delete(vipLevelBO.getLevelCode() + "_VipLevel");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 通过code查找会员
     *
     * @param levelCode 会员code
     * @return
     */
    @GetMapping(path = "/bo/{levelCode}")
    public ResponseEntity selectByLevelCode(@PathVariable String levelCode) {
        LOGGER.info("{}", levelCode);
        VipLevelBO vipLevelBO = null;
        if (redisTemplate.hasKey(levelCode + "_VipLevel")) {
            vipLevelBO = JSONObject.parseObject(redisTemplate.opsForValue().get(levelCode + "_VipLevel"), VipLevelBO
                    .class);
        } else {
            vipLevelBO = vipLevelService.selectByLevelCode(levelCode);
            if (vipLevelBO != null) {
                redisTemplate.opsForValue().set(levelCode + "_VipLevel", JSONObject.toJSONString(vipLevelBO));
            }
        }
        return ResponseEntity.ok(Utils.kv("data", vipLevelBO));
    }
}


