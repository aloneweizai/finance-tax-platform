package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.TagStatisticsBO;
import com.abc12366.uc.service.TagStatisticsService;
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
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 10:22
 */
@RestController
@RequestMapping(path = "/tag/statistics", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TagStatisticsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectTagController.class);

    @Autowired
    private TagStatisticsService tagStatisticsService;

    @GetMapping()
    public ResponseEntity selectStatistics(@RequestParam(required = false) String tagName,
                                           @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TagStatisticsBO> tagStatisticsBOList = tagStatisticsService.selectStatistics(tagName);
        return (tagStatisticsBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) tagStatisticsBOList, "total", ((Page)
                        tagStatisticsBOList).getTotal()));
    }
}
