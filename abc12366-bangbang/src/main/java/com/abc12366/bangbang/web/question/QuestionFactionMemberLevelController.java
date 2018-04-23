package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionFactionMemberLevel;
import com.abc12366.bangbang.service.QuestionFactionMemberLevelService;
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
 * @Date 2017/9/22 14:54
 * 帮手等级管理
 */
@RestController
@RequestMapping(path = "/questionFactionMemberLevel", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionFactionMemberLevelController {

    @Autowired
    private QuestionFactionMemberLevelService questionFactionMemberLevelService;

    /* 帮手等级列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionMemberLevel> list = questionFactionMemberLevelService.selectList();

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    @GetMapping(path = "/view/{id}")
    public ResponseEntity selectOne(@PathVariable String id){
        QuestionFactionMemberLevel record = questionFactionMemberLevelService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", record));
    }

    /* 帮手等级添加 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionFactionMemberLevel record) {
        questionFactionMemberLevelService.add(record);
        return ResponseEntity.ok(Utils.kv("data", record));
    }

    /* 帮手等级删除 */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        questionFactionMemberLevelService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 帮手等级修改 */
    @PutMapping(path = "/modify")
    public ResponseEntity modify(@RequestBody QuestionFactionMemberLevel record) {
        questionFactionMemberLevelService.modify(record);
        return ResponseEntity.ok(Utils.kv("data", record));
    }

}
