package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.WikiBO;
import com.abc12366.bangbang.service.WikiService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单控制类
 *
 * @author lizhongwei
 * @create 2017-06-19
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/wikis", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class WikisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikisController.class);

    @Autowired
    private WikiService wikiService;

    /**
     * 订单列表管理
     *
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "subject", required = false) String subject,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "content", required = false) String content,
                                     @RequestParam(value = "status", required = false) Boolean status,
                                     @RequestParam(value = "pin", required = false) String pin) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        WikiBO wiki = new WikiBO();
        wiki.setSubject(subject);
        wiki.setType(type);
        wiki.setContent(content);
        wiki.setStatus(status);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<WikiBO> wikiList = wikiService.selectList(wiki);
        LOGGER.info("{}", wikiList);
        return (wikiList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) wikiList, "total", ((Page) wikiList).getTotal()));
    }

}
