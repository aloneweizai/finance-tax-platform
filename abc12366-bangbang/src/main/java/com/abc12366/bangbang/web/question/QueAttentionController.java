package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionAttentionBo;
import com.abc12366.bangbang.service.QueAttentionService;
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
import java.util.List;

/**
 * 用户关注管理
 * User: xieyanmao
 * Date: 2017-09-15
 * Time: 17:23
 */
@RestController
@RequestMapping(path = "/queAttention", headers = Constant.VERSION_HEAD + "=1")
public class QueAttentionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueAttentionController.class);

    @Autowired
    private QueAttentionService attentionService;

    /**
     * 用户关注
     */
    @PostMapping(path = "/{id}")
    public ResponseEntity insert(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String attentionCnt = attentionService.insert(id, request);
        return ResponseEntity.ok(Utils.kv("data", attentionCnt));
    }

    /**
     * 取消用户关注
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String attentionCnt = attentionService.delete(id, request);
        return ResponseEntity.ok(Utils.kv("data", attentionCnt));
    }

    /**
     * 查询我关注的用户列表
     */
    @GetMapping(path = "/selectAttentionUserList/{userId}")
    public ResponseEntity selectAttentionUserList(@PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAttentionBo> attentionBoList = attentionService.selectAttentionUserList(userId);
        return (attentionBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) attentionBoList, "total", ((Page) attentionBoList).getTotal
                        ()));
    }

    /**
     * 查询关注我的用户列表
     */
    @GetMapping(path = "/selectUserList/{attentionUserId}")
    public ResponseEntity selectUserList(@PathVariable String attentionUserId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", attentionUserId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAttentionBo> attentionBoList = attentionService.selectUserList(attentionUserId);
        return (attentionBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) attentionBoList, "total", ((Page) attentionBoList).getTotal
                        ()));
    }

    /**
     * 查询用户是否已关注
     */
    @GetMapping(path = "/count/{id}")
    public ResponseEntity selectExist(@PathVariable String id,HttpServletRequest request) {
        LOGGER.info("{}", id);
        int count = Integer.parseInt(attentionService.selectExist(id,request));
        return ResponseEntity.ok(Utils.kv("data", count));
    }

    /**
     * 更新粉丝为已读
     *
     * @param id
     * @return
     */
    @PutMapping(path = "/updateIsRead/{id}")
    public ResponseEntity updateIsRead(@PathVariable("id") String id) {
        attentionService.updateIsRead(id);
        return ResponseEntity.ok(Utils.kv("data", id));
    }
}
