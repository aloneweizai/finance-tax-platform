package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionMedal;
import com.abc12366.bangbang.model.question.bo.QuestionMedalBo;
import com.abc12366.bangbang.service.QuestionMedalService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/20 18:09
 * 勋章管理
 */
@RestController
@RequestMapping(path = "/questionMedal", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionMedalController {

    @Autowired
    private QuestionMedalService questionMedalService;

    /* 勋章列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "name", defaultValue = "") String name){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map map = new HashMap<>();
        map.put("name", name);
        List<QuestionMedalBo> list = questionMedalService.selectList(map);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /* 勋章查询 */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity selectOne(@PathVariable String id){
        QuestionMedal expert = questionMedalService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", expert));
    }

    /* 勋章添加 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionMedal medal) {
        questionMedalService.add(medal);
        return ResponseEntity.ok(Utils.kv("data", medal));
    }

    /* 勋章删除 */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        questionMedalService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 勋章修改 */
    @PutMapping(path = "/modify")
    public ResponseEntity modify(@RequestBody QuestionMedal medal) {
        questionMedalService.modify(medal);
        return ResponseEntity.ok(Utils.kv("data", medal));
    }





}
