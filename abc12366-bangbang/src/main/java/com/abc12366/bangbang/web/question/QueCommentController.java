package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionCommentBo;
import com.abc12366.bangbang.service.QueCommentService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题评论管理模块
 *
 * @author xieyanmao
 * @create 2017-10-25
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queComment", headers = Constant.VERSION_HEAD + "=1")
public class QueCommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueCommentController.class);

    @Autowired
    private QueCommentService queCommentService;

    /**
     * 问题回复评论列表查询
     */
    @GetMapping(path = "/selectList")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                               @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "questionId", required = false) String questionId,
                                     @RequestParam(value = "answerId", required = false) String answerId) {
        Map<String, Object> dataMap = new HashMap<>();
        if(userId == null || "".equals(userId)){
            userId = "11";
        }
        dataMap.put("userId", userId);
        dataMap.put("questionId", questionId);
        dataMap.put("answerId", answerId);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionCommentBo> dataList = queCommentService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 我的评论
     */
    @GetMapping(path = "/selectMyCommentList")
    public ResponseEntity selectMyCommentList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                             @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionCommentBo> dataList = queCommentService.selectMyCommentList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 问题评论新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionCommentBo CommentBo, HttpServletRequest request) {
        //新增问题评论信息
        CommentBo = queCommentService.save(CommentBo,request);
        return ResponseEntity.ok(Utils.kv("data", CommentBo));
    }

    /**
     * 查询单个问题评论信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询单个问题回复信息
        QuestionCommentBo CommentBo = queCommentService.selectComment(id);
        return ResponseEntity.ok(Utils.kv("data", CommentBo));
    }

    /**
     * 查询单个问题评论信息(无需登录)
     */
    @GetMapping(path = "/selectComment/{id}")
    public ResponseEntity selectComment(@PathVariable String id) {
        //查询单个问题回复信息
        QuestionCommentBo CommentBo = queCommentService.selectComment(id);
        return ResponseEntity.ok(Utils.kv("data", CommentBo));
    }

    /**
     * 更新问题评论信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody QuestionCommentBo commentBo) {
        //更新问题回复信息
        commentBo = queCommentService.update(commentBo);
        return ResponseEntity.ok(Utils.kv("data", commentBo));
    }

    /**
     * 更新问题评论状态
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("id") String id) {
        queCommentService.updateStatus(id, status);
        return ResponseEntity.ok(Utils.kv("data", id));
    }

    /**
     * 删除问题评论信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除问题评论信息
        String rtn = queCommentService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
