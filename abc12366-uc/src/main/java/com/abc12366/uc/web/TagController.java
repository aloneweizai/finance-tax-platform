package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.TagBO;
import com.abc12366.uc.model.bo.TagInsertBO;
import com.abc12366.uc.model.bo.TagSelectParamBO;
import com.abc12366.uc.model.bo.TagUpdateBO;
import com.abc12366.uc.service.TagService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 10:26
 */
@RestController
@RequestMapping(path = "/tag", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity selectList(
            @RequestParam(required = false) String tagName,
            @RequestParam(required = false) String category,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}", tagName, category, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        if (tagName != null && StringUtils.isEmpty(tagName)) {
            tagName = null;
        }
        if (category != null && StringUtils.isEmpty(category)) {
            category = null;
        }
        TagSelectParamBO tagSelectParamBO = new TagSelectParamBO(tagName, category);
        List<TagBO> tagBOList = tagService.selectList(tagSelectParamBO);
        return (tagBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) tagBOList, "total", ((Page) tagBOList).getTotal()));
    }

    @GetMapping(path = "/taglist/{userId}")
    public ResponseEntity selectListByUserId(
            @PathVariable String userId,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        if (userId != null && StringUtils.isEmpty(userId)) {
            userId = null;
        }
        List<TagBO> tagBOList = tagService.selectListByUserId(userId);
        return (tagBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) tagBOList, "total", ((Page) tagBOList).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody TagInsertBO tagInsertBO) {
        LOGGER.info("{}", tagInsertBO);
        TagBO tagBO = tagService.insert(tagInsertBO);
        LOGGER.info("{}", tagBO);
        return ResponseEntity.ok(Utils.kv("data", tagBO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        TagBO tagBO = tagService.selectOne(id);
        LOGGER.info("{}", tagBO);
        return ResponseEntity.ok(Utils.kv("data", tagBO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody TagUpdateBO tagUpdateBO, @PathVariable String id) {
        LOGGER.info("{}", tagUpdateBO);
        TagBO tagBO = tagService.update(tagUpdateBO, id);
        LOGGER.info("{}", tagBO);
        return ResponseEntity.ok(Utils.kv("data", tagBO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        tagService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    //启用、禁用等级标签接口
    @PutMapping(path = "/{id}/{status}")
    public ResponseEntity enableOrDisable(@PathVariable String id, @PathVariable String status) {
        LOGGER.info("{}:{}", id, status);
        tagService.enableOrDisable(id, status);
        return ResponseEntity.ok(Utils.kv());
    }
}
