package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionBo;
import com.abc12366.bangbang.service.QueLikeService;
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
 * 问题回复点赞管理
 * User: xieyanmao
 * Date: 2017-09-15
 * Time: 17:23
 */
@RestController
@RequestMapping(path = "/currQueLike", headers = Constant.VERSION_HEAD + "=1")
public class QueLikeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueLikeController.class);

    @Autowired
    private QueLikeService likeService;

    /**
     * 问题回复点赞
     */
    @PostMapping(path = "/{id}")
    public ResponseEntity insert(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String likeCnt = likeService.insert(id, request);
        return ResponseEntity.ok(Utils.kv("data", likeCnt));
    }

    /**
     * 问题回复踩
     */
    @PostMapping(path = "/trample/{id}")
    public ResponseEntity inserttrample(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String likeCnt = likeService.inserttrample(id, request);
        return ResponseEntity.ok(Utils.kv("data", likeCnt));
    }

    /**
     * 取消问题回复点赞
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String likeCnt = likeService.delete(id, request);
        return ResponseEntity.ok(Utils.kv("data", likeCnt));
    }

    /**
     * 查询问题回复点赞列表
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity selectList(@PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> likeBOList = likeService.selectList(userId);
        return (likeBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) likeBOList, "total", ((Page) likeBOList).getTotal
                        ()));
    }

    /**
     * 查询问题回复是否已点赞
     */
    @GetMapping(path = "/count/{id}")
    public ResponseEntity selectExist(@PathVariable String id,HttpServletRequest request) {
        LOGGER.info("{}", id);
        int count = Integer.parseInt(likeService.selectExist(id,request));
        return ResponseEntity.ok(Utils.kv("data", count));
    }
}
