package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.SupportBO;
import com.abc12366.bangbang.service.SupportService;
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
public class SupportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectController.class);

    @Autowired
    private SupportService supportService;

    @PostMapping(path = "/support/{subject}/{id}")
    public ResponseEntity insert(@PathVariable String subject, @PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, request);
        SupportBO supportBO = supportService.insert(subject, id, request);
        return ResponseEntity.ok(Utils.kv("data", supportBO));
    }

    @DeleteMapping(path = "/support/{subject}/{id}")
    public ResponseEntity delete(@PathVariable String subject, @PathVariable String id, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, request);
        supportService.delete(subject, id, request);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/support/{subject}/{userId}")
    public ResponseEntity selectList(@PathVariable String subject,
                                     @PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}:{}", subject, userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<SupportBO> supportBOList = supportService.selectList(subject, userId);
        return (supportBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) supportBOList, "total", ((Page) supportBOList).getTotal
                        ()));
    }

    @GetMapping(path = "/count/support/{subject}/{id}")
    public ResponseEntity selectCount(@PathVariable String subject,
                                      @PathVariable String id) {
        LOGGER.info("{}:{}", subject, id);
        int count = supportService.selectCount(subject, id);
        return ResponseEntity.ok(Utils.kv("data", count));
    }

}
