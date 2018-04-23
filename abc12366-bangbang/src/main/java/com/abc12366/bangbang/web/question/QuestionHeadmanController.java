package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionHeadman;
import com.abc12366.bangbang.model.question.bo.QuestionHeadmanBo;
import com.abc12366.bangbang.service.QuestionHeadmanService;
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
 * @Date 2017/10/16 10:54
 * 掌门人管理
 */
@RestController
@RequestMapping(path = "/questionHeadman", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionHeadmanController {

    @Autowired
    private QuestionHeadmanService questionHeadmanService;

    /* 掌门人列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionHeadman> list = questionHeadmanService.selectList();

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /* 掌门人 详情 */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity selectList(@PathVariable String id){
        QuestionHeadmanBo headmanBo = questionHeadmanService.selectByPrimaryKey(id);
        return ResponseEntity.ok(Utils.kv("data", headmanBo));
    }

    /* 掌门人 审核 */
    @PutMapping(path = "/modifyStatus")
    public ResponseEntity modifyStatus(@RequestBody QuestionHeadman headman, HttpServletRequest request) {
        questionHeadmanService.changeStatus(headman, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 掌门人添加 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionHeadmanBo headmanBo) {
        questionHeadmanService.add(headmanBo);
        return ResponseEntity.ok(Utils.kv("data", headmanBo));
    }


}
