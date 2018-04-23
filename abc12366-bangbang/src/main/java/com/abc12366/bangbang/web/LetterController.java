package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.LetterInsertBO;
import com.abc12366.bangbang.model.bo.LetterListBO;
import com.abc12366.bangbang.model.bo.LetterResponse;
import com.abc12366.bangbang.service.LetterService;
import com.abc12366.gateway.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-24
 * Time: 17:39
 */
@RestController
@RequestMapping(path = "/letter", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LetterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LetterController.class);

    @Autowired
    private LetterService letterService;

    /**
     * 发送私信接口
     * @param letterInsertBO
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping
    public ResponseEntity send(@Valid @RequestBody LetterInsertBO letterInsertBO, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", letterInsertBO, request);
        LetterResponse response = letterService.send(letterInsertBO, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 用户获取自己的私信列表接口
     * @param request
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    @GetMapping()
    public ResponseEntity selectList(HttpServletRequest request,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) throws IOException {
        LOGGER.info("{}:{}:{}", request, page, size);
        request.setAttribute("page", page);
        request.setAttribute("size", size);
        LetterListBO letterListBO = letterService.selectList(request);
        return ResponseEntity.ok(letterListBO);
    }

    /**
     * 用户查看消息，如果消息状态为'未读'，则将消息状态置为'已读'
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity read(@PathVariable String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", id, request);
        LetterResponse response = letterService.read(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 直接将'未读'消息置为'已读'，不需要进入消息
     * @param id
     * @param request
     * @return
     * @throws IOException
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", id, request);
        LetterResponse response = letterService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 用户删除消息，物理删除
     *
     * @param id      消息ID
     * @param
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", id, request);
        LetterResponse response = letterService.delete(id, request);
        return ResponseEntity.ok(response);
    }

}
