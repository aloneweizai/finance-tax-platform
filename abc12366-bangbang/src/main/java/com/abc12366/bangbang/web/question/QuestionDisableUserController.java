package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionDisableUser;
import com.abc12366.bangbang.model.question.bo.QuestionDisableUserBo;
import com.abc12366.bangbang.service.QuestionDisableUserService;
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
 * @Date 2017/10/18 17:21
 * 帮帮禁言用户管理
 */
@RestController
@RequestMapping(path = "/questionDisableUser", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionDisableUserController {

    @Autowired
    private QuestionDisableUserService questionDisableUserService;

    /* 列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionDisableUserBo> list = questionDisableUserService.selectList(null);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /*
    * 禁用用户 接口
    */
    @PostMapping(path = "/disable")
    public ResponseEntity disable(@RequestBody QuestionDisableUser record, HttpServletRequest request) {
        questionDisableUserService.disable(record, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 撤销禁用用户 接口
    */
    @DeleteMapping(path = "/enable/{userId}")
    public ResponseEntity enable(@PathVariable String userId, HttpServletRequest request) {
        questionDisableUserService.enable(userId, request);
        return ResponseEntity.ok(Utils.kv());
    }

}
