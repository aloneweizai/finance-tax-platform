package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionAnswerBo;
import com.abc12366.bangbang.service.QueAnswerService;
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
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题回复管理模块
 *
 * @author xieyanmao
 * @create 2017-09-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queAnswer", headers = Constant.VERSION_HEAD + "=1")
public class QueAnswerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueAnswerController.class);

    @Autowired
    private QueAnswerService queAnswerService;

    /**
     * 问题回复列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "questionId", required = false) String questionId,
                                     @RequestParam(value = "isAccept", required = false) String isAccept) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        dataMap.put("questionId", questionId);//
        dataMap.put("isAccept", isAccept);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAnswerBo> dataList = queAnswerService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 问题回复列表查询(无需登录)
     */
    @GetMapping(path = "/selectListByQuestionId")
    public ResponseEntity selectListByQuestionId(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "questionId", required = false) String questionId,
                                     @RequestParam(value = "isAccept", required = false) String isAccept) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        dataMap.put("questionId", questionId);//
        dataMap.put("isAccept", isAccept);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAnswerBo> dataList = queAnswerService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 最新回答
     */
    @GetMapping(path = "/selectListNew")
    public ResponseEntity selectListNew(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "questionId", required = false) String questionId,
                                     @RequestParam(value = "isAccept", required = false) String isAccept) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("questionId", questionId);//
        dataMap.put("isAccept", isAccept);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAnswerBo> dataList = queAnswerService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 我的回答
     */
    @GetMapping(path = "/selectMyAnswerList")
    public ResponseEntity selectMyAnswerList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                        @RequestParam(value = "userId", required = false) String userId,
                                        @RequestParam(value = "isTip", required = false) String isTip,
                                        @RequestParam(value = "isAccept", required = false) String isAccept) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        dataMap.put("isTip", isTip);//是否被举报，1为被举报
        dataMap.put("isAccept", isAccept);//是否被采纳，1为被采纳
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAnswerBo> dataList = queAnswerService.selectMyAnswerList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 问题回复新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionAnswerBo answerBo, HttpServletRequest request) {
        //新增问题回复信息
        answerBo = queAnswerService.save(answerBo,request);
        return ResponseEntity.ok(Utils.kv("data", answerBo));
    }

    /**
     * 查询单个问题回复信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询单个问题回复信息
        QuestionAnswerBo answerBo = queAnswerService.selectAnswer(id);
        return ResponseEntity.ok(Utils.kv("data", answerBo));
    }

    /**
     * 查询单个问题回复信息(无需登录)
     */
    @GetMapping(path = "/selectAnswer/{id}")
    public ResponseEntity selectAnswer(@PathVariable String id) {
        //查询单个问题回复信息
        QuestionAnswerBo answerBo = queAnswerService.selectAnswer(id);
        return ResponseEntity.ok(Utils.kv("data", answerBo));
    }

    /**
     * 更新问题回复信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody QuestionAnswerBo answerBo) {
        //更新问题回复信息
        answerBo = queAnswerService.update(answerBo);
        return ResponseEntity.ok(Utils.kv("data", answerBo));
    }

    /**
     * 更新问题回复状态
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("id") String id) {
        queAnswerService.updateStatus(id, status);
        return ResponseEntity.ok(Utils.kv("data", id));
    }

    /**
     * 删除问题回复信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除问题回复信息
        String rtn = queAnswerService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 更新回复为已读
     *
     * @param id
     * @return
     */
    @PutMapping(path = "/updateIsRead/{id}")
    public ResponseEntity updateIsRead(@PathVariable("id") String id) {
        queAnswerService.updateIsRead(id);
        return ResponseEntity.ok(Utils.kv("data", id));
    }

    /**
     * 设置为采纳
     *
     * @param id
     * @return
     */
    @PutMapping(path = "/updateIsAccept/{id}")
    public ResponseEntity updateIsAccept(@Valid @RequestBody String questionId, @PathVariable("id") String id, HttpServletRequest request) {
        queAnswerService.updateIsAccept(questionId,id,request);
        return ResponseEntity.ok(Utils.kv("data", id));
    }


}
