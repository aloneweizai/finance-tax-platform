package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.Feedback;
import com.abc12366.bangbang.model.bo.FeedbackParamBO;
import com.abc12366.bangbang.service.FeedbackService;
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
 * @Author liuqi
 * @Date 2017/8/7 13:39
 */
@RestController
@RequestMapping(path = "/feedback", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class FeedbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);


    @Autowired
    private FeedbackService feedbackService;


    /*
    *  意见反馈 分页查询
    *  支持 来源类型, 反馈类型 查询
    */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "sourceType", required = false) String sourceType,
                                     @RequestParam(value = "feedbackType", required = false) String feedbackType,
                                     @RequestParam(value = "isReply", required = false) Boolean isReply) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);

        FeedbackParamBO param = new FeedbackParamBO(sourceType, feedbackType, isReply);
        List<Feedback> list = feedbackService.selectList(param);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }


    /**
     * 添加意见反馈接口
     */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody Feedback feedback, HttpServletRequest request) {
        feedbackService.add(feedback, request);
        return ResponseEntity.ok(Utils.kv("data", feedback));
    }

    /*
    * 删除意见反馈 接口
    */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        feedbackService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 删除意见反馈 接口
    */
    @DeleteMapping(path = "/delete")
    public ResponseEntity delete(@RequestBody List<String> ids) {
        feedbackService.delete(ids);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 查询未回复的总数
    */
    @GetMapping(path = "/selectCntUnanswered")
    public ResponseEntity selectCntByStatus() {
        Long cnt = feedbackService.selectCntUnanswered();
        return ResponseEntity.ok(Utils.kv("data", cnt));
    }

}
