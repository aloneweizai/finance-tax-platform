package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionTipOff;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffBo;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffParamBo;
import com.abc12366.bangbang.service.QuestionTipOffService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/17 17:21
 * 帮帮举报内容（包括问题，回答，评论）
 */
@RestController
@RequestMapping(path = "/questionTipOff", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionTipOffController {

    @Autowired
    private QuestionTipOffService questionTipOffService;

    /* 列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "status", defaultValue = "") String status){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        QuestionTipOffParamBo paramBo = new QuestionTipOffParamBo();
        paramBo.setStatus(status);
        List<QuestionTipOffBo> list = questionTipOffService.selectList(paramBo);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /* 查询 */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity selectOne(@PathVariable String id){
        QuestionTipOffBo tipOff = questionTipOffService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data", tipOff));
    }

    /*
    * 后台审核修改状态 接口
    */
    @PutMapping(path = "/modifyStatus")
    public ResponseEntity modifyStatus(@Valid @RequestBody QuestionTipOff questionTipOff, 
                                       HttpServletRequest request) {
        questionTipOffService.changeStatusByAdmin(questionTipOff, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 举报新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionTipOffBo questionTipOffBo) {
        //新增问题信息
        questionTipOffBo = questionTipOffService.save(questionTipOffBo);
        return ResponseEntity.ok(Utils.kv("data", questionTipOffBo));
    }

    /**
     * 屏蔽
     */
    @PostMapping(path = "/questionPb")
    public ResponseEntity questionPb(@Valid @RequestBody QuestionTipOffBo questionTipOffBo) {
        //屏蔽问题信息
        questionTipOffBo = questionTipOffService.savepb(questionTipOffBo);
        return ResponseEntity.ok(Utils.kv("data", questionTipOffBo));
    }

    /* 根据状态查询总数 */
    @GetMapping(path = "/selectCntByStatus/{status}")
    public ResponseEntity selectCntByStatus(@PathVariable String status){
        Long cnt = questionTipOffService.selectCntByStatus(status);
        return ResponseEntity.ok(Utils.kv("data", cnt));
    }
}
