package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.IdsBo;
import com.abc12366.cms.model.bo.SiteIssueBo;
import com.abc12366.cms.service.SiteIssueService;
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
 * 站点发布管理模块
 *
 * @author xieyanmao
 * @create 2017-06-10
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/siteIssue", headers = Constant.VERSION_HEAD + "=1")
public class SiteIssueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteIssueController.class);
    @Autowired
    private SiteIssueService siteIssueService;

    /**
     * 查询站点发布列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "templateName", required = false) String templateName,
                                     @RequestParam(value = "issueState", required = false) String issueState) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("templateName", templateName);
        dataMap.put("issueState", issueState);
        List<SiteIssueBo> dataList = siteIssueService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询单个站点发布信息
     */
    @GetMapping(path = "/{issueId}")
    public ResponseEntity selectOneById(@PathVariable("issueId") String issueId) {
        LOGGER.info("{}", issueId);
        SiteIssueBo siteIssueBo = siteIssueService.selectOneById(issueId);
        LOGGER.info("{}", siteIssueBo);
        return ResponseEntity.ok(Utils.kv("data", siteIssueBo));
    }

    /**
     * 新增站点发布信息
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody SiteIssueBo siteIssueBo) {
        LOGGER.info("{}", siteIssueBo);
        siteIssueBo = siteIssueService.save(siteIssueBo);
        LOGGER.info("{}", siteIssueBo);
        return ResponseEntity.ok(Utils.kv("data", siteIssueBo));
    }

    /**
     * 更新站点发布信息
     */
    @PutMapping(path = "/{issueId}")
    public ResponseEntity update(@Valid @RequestBody SiteIssueBo siteIssueBo, @PathVariable("issueId") Long issueId) {
        LOGGER.info("{}", siteIssueBo);
        siteIssueBo = siteIssueService.update(siteIssueBo);
        LOGGER.info("{}", siteIssueBo);
        return ResponseEntity.ok(Utils.kv("data", siteIssueBo));
    }

    /**
     * 删除站点发布信息
     */
    @PostMapping(path = "/deleteList")
    public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //删除站点发布信息
        String rtn = siteIssueService.deleteList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }


}
