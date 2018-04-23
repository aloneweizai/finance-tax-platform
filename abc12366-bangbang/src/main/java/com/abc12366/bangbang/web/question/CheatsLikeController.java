package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.CheatsBo;
import com.abc12366.bangbang.service.CheatsLikeService;
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
 * 秘籍及评论点赞管理
 * User: xieyanmao
 * Date: 2017-09-15
 * Time: 17:23
 */
@RestController
@RequestMapping(path = "/cheatsLike", headers = Constant.VERSION_HEAD + "=1")
public class CheatsLikeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheatsLikeController.class);

    @Autowired
    private CheatsLikeService likeService;

    /**
     * 点赞
     */
    @PostMapping(path = "/{id}")
    public ResponseEntity insert(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String likeCnt = likeService.insert(id, request);
        return ResponseEntity.ok(Utils.kv("data", likeCnt));
    }

    /**
     * 踩
     */
    @PostMapping(path = "/trample/{id}")
    public ResponseEntity inserttrample(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String likeCnt = likeService.inserttrample(id, request);
        return ResponseEntity.ok(Utils.kv("data", likeCnt));
    }

    /**
     * 取消点赞
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String likeCnt = likeService.delete(id, request);
        return ResponseEntity.ok(Utils.kv("data", likeCnt));
    }

    /**
     * 查询点赞列表
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity selectList(@PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsBo> likeList = likeService.selectList(userId);
        return (likeList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) likeList, "total", ((Page) likeList).getTotal
                        ()));
    }

    /**
     * 查询是否已点赞
     */
    @GetMapping(path = "/count/{id}")
    public ResponseEntity selectExist(@PathVariable String id,HttpServletRequest request) {
        LOGGER.info("{}", id);
        int count = Integer.parseInt(likeService.selectExist(id,request));
        return ResponseEntity.ok(Utils.kv("data", count));
    }
}
