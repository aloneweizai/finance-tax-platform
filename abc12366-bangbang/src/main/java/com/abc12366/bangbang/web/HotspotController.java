package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.AskBO;
import com.abc12366.bangbang.model.bo.HotspotAskBO;
import com.abc12366.bangbang.service.AskService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-27
 * Time: 10:35
 */
@RestController
@RequestMapping(path = "/hotspot", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class HotspotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotspotController.class);

    @Autowired
    private AskService askService;

    @GetMapping(path = "/asks")
    public ResponseEntity selectHotspotAsks(
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<HotspotAskBO> askBOList = askService.selectHotspotAsks();
        LOGGER.info("{}", askBOList);
        return (askBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) askBOList, "total", ((Page) askBOList).getTotal()));
    }

    @GetMapping(path = "/comments")
    public ResponseEntity selectHotspotComments(
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<AskBO> askBOList = askService.selectHotspotComments();
        LOGGER.info("{}", askBOList);
        return (askBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) askBOList, "total", ((Page) askBOList).getTotal()));
    }

}
