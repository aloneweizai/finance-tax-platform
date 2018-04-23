package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.CheatsCommentBo;
import com.abc12366.bangbang.service.CheatsCommentService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
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
 * 秘籍评论管理模块
 *
 * @author xieyanmao
 * @create 2017-10-25
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/cheatsComment", headers = Constant.VERSION_HEAD + "=1")
public class CheatsCommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheatsCommentController.class);

    @Autowired
    private CheatsCommentService cheatsCommentService;

    /**
     * 秘籍评论列表查询
     */
    @GetMapping(path = "/selectList")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                               @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "cheatsId", required = false) String cheatsId) {
        Map<String, Object> dataMap = new HashMap<>();
        if(userId == null || "".equals(userId)){
            userId = "11";
        }
        dataMap.put("userId", userId);//
        dataMap.put("cheatsId", cheatsId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsCommentBo> dataList = cheatsCommentService.selectList(dataMap);
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
        List<CheatsCommentBo> dataList = cheatsCommentService.selectMyCommentList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 秘籍评论新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CheatsCommentBo commentBo) {
        //新增问题评论信息
        commentBo = cheatsCommentService.save(commentBo);
        return ResponseEntity.ok(Utils.kv("data", commentBo));
    }

    /**
     * 查询单个秘籍评论信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询单个问题回复信息
        CheatsCommentBo CommentBo = cheatsCommentService.selectComment(id);
        return ResponseEntity.ok(Utils.kv("data", CommentBo));
    }

    /**
     * 查询单个秘籍评论信息(无需登录)
     */
    @GetMapping(path = "/selectComment/{id}")
    public ResponseEntity selectComment(@PathVariable String id) {
        //查询单个问题回复信息
        CheatsCommentBo CommentBo = cheatsCommentService.selectComment(id);
        return ResponseEntity.ok(Utils.kv("data", CommentBo));
    }

    /**
     * 更新秘籍评论信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody CheatsCommentBo commentBo) {
        //更新问题回复信息
        commentBo = cheatsCommentService.update(commentBo);
        return ResponseEntity.ok(Utils.kv("data", commentBo));
    }

    /**
     * 更新秘籍评论状态
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("id") String id) {
        cheatsCommentService.updateStatus(id, status);
        return ResponseEntity.ok(Utils.kv("data", id));
    }

    /**
     * 删除秘籍评论信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除秘籍评论信息
        String rtn = cheatsCommentService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
