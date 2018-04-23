package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.SensitiveWords;
import com.abc12366.bangbang.service.SensitiveWordsService;
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
 * 敏感词控制类
 *
 * @author lizhongwei
 * @create 2017-06-20
 * @since 1.0.0
 * modify by liuQi 2017-10-23
 */
@RestController
@RequestMapping(path = "/sensitiveWords", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SensitiveWordsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveWordsController.class);

    @Autowired
    private SensitiveWordsService sensitiveWordsService;

    /**
     * 敏感词列表管理
     * @return
     */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "keywords", required = false) String keywords) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        SensitiveWords sensitiveWords = new SensitiveWords();
        sensitiveWords.setKeywords(keywords);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<SensitiveWords> sensitiveWordsList = sensitiveWordsService.selectList(sensitiveWords);
        LOGGER.info("{}", sensitiveWordsList);
        return (sensitiveWordsList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) sensitiveWordsList, "total", ((Page) sensitiveWordsList).getTotal()));

    }


    /**
     * 查询敏感词详情
     * @param id
     * @return
     */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity<?> selectOne(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        SensitiveWords sensitiveWords = sensitiveWordsService.selectOne(id);
        LOGGER.info("{}", sensitiveWords);
        return ResponseEntity.ok(Utils.kv("data", sensitiveWords));
    }

    /**
     * 敏感词新增
     * @return
     */
    @PostMapping(path = "/add")
    public ResponseEntity addSensitiveWords(@Valid @RequestBody SensitiveWords sensitiveWords) {
        LOGGER.info("{}", sensitiveWords);
        SensitiveWords bo = sensitiveWordsService.add(sensitiveWords);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 修改敏感词
     * @param sensitiveWords
     * @return
     */

    @PutMapping(path = "/modify")
    public ResponseEntity update(@Valid @RequestBody SensitiveWords sensitiveWords) {
        LOGGER.info("{}", sensitiveWords);
        SensitiveWords bo = sensitiveWordsService.update(sensitiveWords);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 删除敏感词
     * @param id
     * @return
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        sensitiveWordsService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

}
