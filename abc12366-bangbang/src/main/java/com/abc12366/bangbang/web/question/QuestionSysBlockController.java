package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionSysBlockBo;
import com.abc12366.bangbang.model.question.bo.QuestionSysBlockParamBo;
import com.abc12366.bangbang.service.QuestionSysBlockService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/17 17:21
 * 系统过滤帮帮内容（包括问题，回答，评论）
 */
@RestController
@RequestMapping(path = "/questionSysBlock", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionSysBlockController {

    @Autowired
    private QuestionSysBlockService questionSysBlockService;

    /* 列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "content", defaultValue = "") String content,
                                     @RequestParam(value = "status", defaultValue = "") String status,
                                     @RequestParam(value = "beginTime", defaultValue = "") String beginTime,
                                     @RequestParam(value = "endTime", defaultValue = "") String endTime){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        QuestionSysBlockParamBo param = new QuestionSysBlockParamBo();
        param.setStatus(status).setContent(content).setBeginTime(beginTime).setEndTime(endTime);
        List<QuestionSysBlockBo> list = questionSysBlockService.selectList(param);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }


    /*
    * 修改状态 接口
    */
    @PutMapping(path = "/status/{id}/{status}")
    public ResponseEntity modifyStatus(@PathVariable String id, @PathVariable String status, HttpServletRequest request) {
        questionSysBlockService.changeStatus(id, status, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 根据状态查询总数
    */
    @GetMapping(path = "/selectCntByStatus/{status}")
    public ResponseEntity selectCntByStatus(@PathVariable String status) {
        Long cnt = questionSysBlockService.selectCntByStatus(status);
        return ResponseEntity.ok(Utils.kv("data", cnt));
    }

}
