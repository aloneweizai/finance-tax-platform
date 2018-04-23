package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionDisableIp;
import com.abc12366.bangbang.model.question.bo.QuestionDisableIpBo;
import com.abc12366.bangbang.service.QuestionDisableIpService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/18 17:21
 * 帮帮禁言ip管理
 */
@RestController
@RequestMapping(path = "/questionDisableIp", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionDisableIpController {

    @Autowired
    private QuestionDisableIpService questionDisableIpService;

    /* 列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionDisableIpBo> list = questionDisableIpService.selectList(null);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /*
    * 禁用 接口
    */
    @PostMapping(path = "/disable")
    public ResponseEntity disable(@RequestBody QuestionDisableIp record) {
        questionDisableIpService.disable(record);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 撤销禁用 接口
    */
    @DeleteMapping(path = "/enable/{ip}")
    public ResponseEntity enable(@PathVariable String ip) {
        questionDisableIpService.enable(ip);
        return ResponseEntity.ok(Utils.kv());
    }

}
