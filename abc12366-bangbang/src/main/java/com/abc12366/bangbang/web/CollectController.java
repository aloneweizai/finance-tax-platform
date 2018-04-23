package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.CollectBO;
import com.abc12366.bangbang.model.bo.CollectListBO;
import com.abc12366.bangbang.service.CollectService;
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
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-10
 * Time: 17:23
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class CollectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectController.class);

    @Autowired
    private CollectService collectService;

    @PostMapping(path = "/collect/{askId}")
    public ResponseEntity insert(@PathVariable String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        CollectBO collectBO = collectService.insert(askId, request);
        return ResponseEntity.ok(Utils.kv("data", collectBO));
    }

    @DeleteMapping(path = "/collect/{askId}")
    public ResponseEntity delete(@PathVariable String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        collectService.delete(askId, request);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/collect/{userId}")
    public ResponseEntity selectList(@PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CollectListBO> collectBOList = collectService.selectList(userId);
        return (collectBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) collectBOList, "total", ((Page) collectBOList).getTotal
                        ()));
    }

    @GetMapping(path = "/count/collect/{askId}")
    public ResponseEntity selectCount(@PathVariable String askId) {
        LOGGER.info("{}", askId);
        int count = Integer.parseInt(collectService.selectCount(askId));
        return ResponseEntity.ok(Utils.kv("data", count));
    }
}
