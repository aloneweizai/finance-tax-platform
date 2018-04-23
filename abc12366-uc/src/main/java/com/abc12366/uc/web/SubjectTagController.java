package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.BatchTagInsertBO;
import com.abc12366.uc.model.bo.SubjectTagBO;
import com.abc12366.uc.service.SubjectTagService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 11:38
 */
@RestController
@RequestMapping(path = "/tag", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SubjectTagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectTagController.class);

    @Autowired
    private SubjectTagService subjectTagService;

    @PostMapping(path = "/{subject}/{id}/{tagId}")
    public ResponseEntity insert(@PathVariable String subject, @PathVariable String id, @PathVariable String tagId,
                                 HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, tagId);
        SubjectTagBO subjectTagBO = subjectTagService.insert(subject, id, tagId, request);
        return ResponseEntity.ok(Utils.kv("data", subjectTagBO));
    }

    /*@PostMapping(path = "/{subject}/{id}")
    public ResponseEntity batchTagInsert(@PathVariable String subject, @PathVariable String id, @RequestBody TagList
    tagList, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, tagList);
        List<SubjectTagBO> subjectTagBOList = subjectTagService.batchInsert(subject, id, tagList, request);
        return (subjectTagBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", subjectTagBOList, "total", subjectTagBOList.size()));
    }

    @PostMapping(path = "/batch/{subject}/{tagId}")
    public ResponseEntity batchUserInsert(@PathVariable String subject, @PathVariable String tagId, @RequestBody
    String subjectIds, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}:{}", subject, tagId, subjectIds, request);
        List<SubjectTagBO> subjectTagBOList = subjectTagService.batchUserInsert(subject, tagId, subjectIds, request);
        return (subjectTagBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", subjectTagBOList, "total", subjectTagBOList.size()));
    }*/

    /**
     * 批量大
     * @param subject
     * @param batchTagInsertBO
     * @param request
     * @return
     */
    @PostMapping(path = "/batch/{subject}")
    public ResponseEntity batchInsert(@PathVariable String subject, @RequestBody BatchTagInsertBO batchTagInsertBO,
                                      HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, batchTagInsertBO, request);
        List<SubjectTagBO> subjectTagBOList = subjectTagService.batchInsert2(subject, batchTagInsertBO, request);
        return (subjectTagBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", subjectTagBOList, "total", subjectTagBOList.size()));
    }

    @GetMapping(path = "/{subject}/{id}")
    public ResponseEntity selectList(@PathVariable String subject,
                                     @PathVariable String id,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}", subject, id, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<SubjectTagBO> subjectTagBOList = subjectTagService.selectList(subject, id);
        return (subjectTagBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) subjectTagBOList, "total", ((Page) subjectTagBOList)
                        .getTotal()));
    }

    @DeleteMapping(path = "/{subject}/{id}/{tagId}")
    public ResponseEntity delete(@PathVariable String subject, @PathVariable String id, @PathVariable String tagId) {
        LOGGER.info("{}:{}:{}", subject, id, tagId);
        subjectTagService.delete(subject, id, tagId);
        return ResponseEntity.ok(Utils.kv());
    }
}
