package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionExpert;
import com.abc12366.bangbang.model.question.bo.QuestionExpertBO;
import com.abc12366.bangbang.model.question.bo.QuestionExpertParamBo;
import com.abc12366.bangbang.service.QuestionExpertService;
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
 * @Date 2017/9/15 10:24
 * 帮邦专家接口
 */
@RestController
@RequestMapping(path = "/questionExpert", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionExpertController {

    @Autowired
    private QuestionExpertService questionExpertService;

    /* 专家列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "realName", required = false) String realName,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "status", required = false) String status){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);

        QuestionExpertParamBo param = new QuestionExpertParamBo();
        param.setPhone(phone).setRealName(realName).setStatus(status).setUsername(username).setType(type);
        List<QuestionExpertBO> list = questionExpertService.selectList(param);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /* 专家大侠列表查询 */
    @GetMapping(path = "/listDX")
    public ResponseEntity selectListDX(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "type", required = false) String type){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("type", type);//专家类型
        List<QuestionExpertBO> list = questionExpertService.selectListDX(dataMap);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /* 专家列表查询 */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity selectOne(@PathVariable String id){
        QuestionExpertBO expert = questionExpertService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", expert));
    }

    /* 专家添加 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionExpert questionExpert) {
        questionExpertService.add(questionExpert);
        return ResponseEntity.ok(Utils.kv("data", questionExpert));
    }

    /* 专家删除 */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        questionExpertService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 专家修改 */
    @PutMapping(path = "/modify")
    public ResponseEntity modify(@RequestBody QuestionExpert questionExpert) {
        questionExpertService.modify(questionExpert);
        return ResponseEntity.ok(Utils.kv("data", questionExpert));
    }

    /* 专家列表查询 */
    @GetMapping(path = "/listByUserId/{userId}")
    public ResponseEntity selectListByUserId(@PathVariable String userId){

        List<QuestionExpertBO> list = questionExpertService.selectListByUserId(userId);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", list));
    }
}
