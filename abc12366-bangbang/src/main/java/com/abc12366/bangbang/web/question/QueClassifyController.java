package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionClassifyStatistics;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyBo;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyTagBo;
import com.abc12366.bangbang.service.QueClassifyService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题分类管理模块
 *
 * @author xieyanmao
 * @create 2017-09-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queclassify", headers = Constant.VERSION_HEAD + "=1")
public class QueClassifyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueClassifyController.class);

    @Autowired
    private QueClassifyService classifyService;

    /**
     * 问题分类列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "classifyCode", required = false) String classifyCode,
                                     @RequestParam(value = "parentCode", required = false) String parentCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("classifyCode",classifyCode);//分类ID
        dataMap.put("parentCode",parentCode);//父ID
        List<QuestionClassifyBo> dataList = classifyService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 问题分类列表查询(无需登录)
     */
    @GetMapping(path = "/selectClassifyList")
    public ResponseEntity selectClassifyList(@RequestParam(value = "classifyCode", required = false) String classifyCode,
                                     @RequestParam(value = "parentCode", required = false) String parentCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("classifyCode",classifyCode);//分类ID
        dataMap.put("parentCode",parentCode);//父ID
        List<QuestionClassifyBo> dataList = classifyService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 问题分类新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionClassifyBo classifyBo) {
        //新增问题分类信息
        classifyBo = classifyService.save(classifyBo);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 查询单个问题分类信息
     */
    @GetMapping(path = "/{classifyCode}")
    public ResponseEntity selectOne(@PathVariable String classifyCode) {
        //查询问题分类信息
        QuestionClassifyBo classifyBo = classifyService.selectClassify(classifyCode);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 更新问题分类信息
     */
    @PutMapping(path = "/{classifyCode}")
    public ResponseEntity update(@PathVariable String classifyCode,
                                 @Valid @RequestBody QuestionClassifyBo classifyBo) {
        //更新问题分类信息
        classifyBo = classifyService.update(classifyBo);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 根据分类查询相关标签
     */
    @GetMapping(path = "/selectClassifyTagList")
    public ResponseEntity selectClassifyTagList(@RequestParam(value = "classifyCode", required = false) String classifyCode) {
        List<QuestionClassifyTagBo> dataList = classifyService.selectClassifyTagList(classifyCode);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 修改分类标签关联
     */
    @PutMapping(path = "/updateClassifyTag")
    public ResponseEntity updateClassifyTag(@RequestBody QuestionClassifyBo classifyBo) {
        classifyService.updateClassifyTag(classifyBo);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 更新问题分类状态
     *
     * @param status
     * @param classifyCode
     * @return
     */
    @PutMapping(path = "/updateStatus/{classifyCode}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("classifyCode") String classifyCode) {
        classifyService.updateStatus(classifyCode, status);
        return ResponseEntity.ok(Utils.kv("data", classifyCode));
    }

    /**
     * 删除问题分类信息
     */
    @DeleteMapping(path = "/{classifyCode}")
    public ResponseEntity delete(@PathVariable String classifyCode) {
        //删除问题分类信息
        String rtn = classifyService.delete(classifyCode);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 问题分类列表查询(无需登录)
     */
    @GetMapping(path = "/selectClassifyStatistics")
    public ResponseEntity selectClassifyStatistics(@RequestParam(value = "beginTime", defaultValue = "") String beginTime,
                                                   @RequestParam(value = "endTime", defaultValue = "") String endTime,
                                                   @RequestParam(value = "parentCode") String parentCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("parentCode",parentCode);//分类ID
        dataMap.put("beginTime",beginTime);
        dataMap.put("endTime",endTime);
        List<QuestionClassifyStatistics> dataList = classifyService.selectClassifyStatistics(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }
}
