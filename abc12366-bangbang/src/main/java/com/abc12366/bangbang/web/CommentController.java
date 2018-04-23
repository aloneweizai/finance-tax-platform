package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.CommentBO;
import com.abc12366.bangbang.model.bo.CommentInsertBO;
import com.abc12366.bangbang.model.bo.CommentUpdateBO;
import com.abc12366.bangbang.service.CommentService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
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
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-19
 * Time: 11:10
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class CommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @PostMapping(path = "/comment/{answerId}")
    public ResponseEntity insert(@Valid @RequestBody CommentInsertBO commentInsertBO, @PathVariable String answerId) {
        LOGGER.info("{}:{}", commentInsertBO, answerId);
        CommentBO commentBO = commentService.insert(commentInsertBO, answerId);
        return ResponseEntity.ok(Utils.kv("data", commentBO));
    }

    @GetMapping(path = "/comments")
    public ResponseEntity selectListForAdmin(@RequestParam(required = false) String userId,
                                             @RequestParam(required = false) String answerId,
                                             @RequestParam(required = false) String commentedUserId,
                                             @RequestParam(required = false) String status,
                                             @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}:{}", userId, answerId, commentedUserId, status, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        if (userId != null && StringUtils.isEmpty(userId)) {
            userId = null;
        }
        if (answerId != null && StringUtils.isEmpty(answerId)) {
            answerId = null;
        }
        if (commentedUserId != null && StringUtils.isEmpty(commentedUserId)) {
            commentedUserId = null;
        }
        if (status != null && StringUtils.isEmpty(status)) {
            status = null;
        }
        List<CommentBO> commentBOList = commentService.selectListForAdmin(userId, answerId, commentedUserId, status);
        LOGGER.info("{}", commentBOList);
        return (commentBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) commentBOList, "total", ((Page) commentBOList).getTotal
                        ()));
    }

    @GetMapping(path = "/comment")
    public ResponseEntity selectListForUser(@RequestParam(required = false) String userId,
                                            @RequestParam(required = false) String answerId,
                                            @RequestParam(required = false) String commentedUserId,
                                            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}:{}", userId, answerId, commentedUserId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        if (userId != null && StringUtils.isEmpty(userId)) {
            userId = null;
        }
        if (answerId != null && StringUtils.isEmpty(answerId)) {
            answerId = null;
        }
        if (commentedUserId != null && StringUtils.isEmpty(commentedUserId)) {
            commentedUserId = null;
        }
        List<CommentBO> commentBOList = commentService.selectListForUser(userId, answerId, commentedUserId);
        LOGGER.info("{}", commentBOList);
        return (commentBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) commentBOList, "total", ((Page) commentBOList).getTotal
                        ()));
    }

    @GetMapping(path = "/comment/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        CommentBO commentBO = commentService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", commentBO));
    }

    @PutMapping(path = "/comment/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody CommentUpdateBO commentUpdateBO) {
        LOGGER.info("{}:{}", id, commentUpdateBO);
        CommentBO commentBO = commentService.update(id, commentUpdateBO);
        return ResponseEntity.ok(Utils.kv("data", commentBO));
    }

    @PutMapping(path = "/block/comment/{id}")
    public ResponseEntity block(@PathVariable String id) {
        LOGGER.info("{}", id);
        CommentBO commentBO = commentService.block(id);
        return ResponseEntity.ok(Utils.kv("data", commentBO));
    }

    @DeleteMapping(path = "/comment/{id}")
    public ResponseEntity delete(@PathVariable() String id, HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = request.getHeader(Constant.USER_TOKEN_HEAD);
        commentService.delete(id, userId);
        return ResponseEntity.ok(Utils.kv());
    }
}
