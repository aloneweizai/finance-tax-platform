package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.SiteBo;
import com.abc12366.cms.model.bo.SiteListBo;
import com.abc12366.cms.service.SiteService;
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
import java.util.List;

/**
 * 站点管理模块
 *
 * @author xieyanmao
 * @create 2017-04-27
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/site", headers = Constant.VERSION_HEAD + "=1")
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);
    @Autowired
    private SiteService siteService;

    /**
     * 查询站点列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<SiteListBo> dataList = siteService.selectList();
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询单个站点
     */
    @GetMapping(path = "/{siteId}")
    public ResponseEntity selectOneById(@PathVariable("siteId") String siteId) {
        SiteBo siteBo = siteService.selectOneById(siteId);
        return ResponseEntity.ok(Utils.kv("data", siteBo));
    }

    /**
     * 新增站点
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody SiteBo siteBo) {
        siteBo = siteService.save(siteBo);
        return ResponseEntity.ok(Utils.kv("data", siteBo));
    }

    /**
     * 更新站点
     */
    @PutMapping(path = "/{siteId}")
    public ResponseEntity update(@Valid @RequestBody SiteBo siteBo, @PathVariable("siteId") String siteId) {
        siteBo = siteService.update(siteBo);
        return ResponseEntity.ok(Utils.kv("data", siteBo));
    }


}
