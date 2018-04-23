package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionFactionLevel;
import com.abc12366.bangbang.service.QuestionFactionLevelService;
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
 * @Date 2017/9/22 14:53
 * 帮派等级管理
 */
@RestController
@RequestMapping(path = "/questionFactionLevel", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionFactionLevelController {

    @Autowired
    private QuestionFactionLevelService questionFactionLevelService;

    /* 帮派等级列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionLevel> list = questionFactionLevelService.selectList();

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @GetMapping(path = "/view/{id}")
    public ResponseEntity selectOne(@PathVariable String id){
        QuestionFactionLevel record = questionFactionLevelService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", record));
    }

    /* 帮派等级添加 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionFactionLevel record) {
        questionFactionLevelService.add(record);
        return ResponseEntity.ok(Utils.kv("data", record));
    }

    /* 帮派等级删除 */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        questionFactionLevelService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 帮派等级修改 */
    @PutMapping(path = "/modify")
    public ResponseEntity modify(@RequestBody QuestionFactionLevel record) {
        questionFactionLevelService.modify(record);
        return ResponseEntity.ok(Utils.kv("data", record));
    }

}
