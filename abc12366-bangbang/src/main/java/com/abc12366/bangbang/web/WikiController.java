package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.WikiAccesslogBO;
import com.abc12366.bangbang.model.bo.WikiBO;
import com.abc12366.bangbang.service.SensitiveWordFilter;
import com.abc12366.bangbang.service.WikiService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 百科主题控制类
 *
 * @author lizhongwei
 * @create 2017-06-19
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/wiki", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class WikiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikiController.class);

    @Autowired
    private WikiService wikiService;

    @Autowired
    private SensitiveWordFilter sensitiveWordFilter;

    /**
     * 百科主题列表管理
     *
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "subject", required = false) String subject,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "content", required = false) String content,
                                     @RequestParam(value = "pin", required = false) String pin) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        WikiBO wiki = new WikiBO();
        wiki.setSubject(subject);
        wiki.setType(type);
        wiki.setContent(content);
        wiki.setStatus(true);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<WikiBO> wikiList = wikiService.selectList(wiki);
        LOGGER.info("{}", wikiList);
        return (wikiList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) wikiList, "total", ((Page) wikiList).getTotal()));
    }


    /**
     * 查询百科主题详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectOne(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        WikiBO wikiBO = wikiService.selectOne(id);
        LOGGER.info("{}", wikiBO);
        return ResponseEntity.ok(Utils.kv("data", wikiBO));
    }

    /**
     * 敏感词新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity addWiki(@Valid @RequestBody WikiBO wikiBO) {
        LOGGER.info("{}", wikiBO);
        if (wikiBO != null) {
            Set<String> set = sensitiveWordFilter.getSensitiveWord(wikiBO.toString(), 1);
            if (set != null && set.size() != 0) {
                LOGGER.info("请求存在敏感词，请求失败", set);
                throw new ServiceException(4508);
            }
        }
        WikiBO bo = wikiService.addWiki(wikiBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 修改百科主题
     *
     * @param wikiBO
     * @param id
     * @return
     */

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody WikiBO wikiBO, @PathVariable("id") String id) {
        LOGGER.info("{}", wikiBO);
        wikiBO.setId(id);
        WikiBO bo = wikiService.update(wikiBO);
        LOGGER.info("{}", bo);
        return new ResponseEntity<>(bo, HttpStatus.OK);
    }

    /**
     * 删除百科主题
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        WikiBO wikiBO = new WikiBO();
        wikiBO.setId(id);
        wikiService.deleteWiki(wikiBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 新增百科主题日志
     *
     * @return
     */
    @PostMapping(path = "/log/{wikiId}")
    public ResponseEntity addWikiLog(@Valid @RequestBody WikiAccesslogBO accesslogBO, @PathVariable("wikiId") String
            wikiId) {
        LOGGER.info("{}", accesslogBO);
        accesslogBO.setWikiId(wikiId);
        WikiAccesslogBO bo = wikiService.addWikiLog(accesslogBO);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }
}
