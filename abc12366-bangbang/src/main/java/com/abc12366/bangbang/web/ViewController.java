package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.ViewBO;
import com.abc12366.bangbang.service.ViewService;
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
 * Date: 2017-07-11
 * Time: 11:45
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ViewController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    private ViewService viewService;

    @PostMapping(path = "/view/{askId}")
    public ResponseEntity insert(@PathVariable String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        ViewBO viewBO = viewService.insert(askId, request);
        return ResponseEntity.ok(Utils.kv("data", viewBO));
    }

    @DeleteMapping(path = "/view/{askId}")
    public ResponseEntity delete(@PathVariable String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        viewService.delete(askId, request);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/view/{userId}")
    public ResponseEntity selectList(@PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ViewBO> viewBOList = viewService.selectList(userId);
        return (viewBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) viewBOList, "total", ((Page) viewBOList).getTotal()));
    }

    @GetMapping(path = "/count/view/{askId}")
    public ResponseEntity selectCount(@PathVariable String askId) {
        LOGGER.info("{}", askId);
        int count = viewService.selectCount(askId);
        return ResponseEntity.ok(Utils.kv("data", count));
    }

}
