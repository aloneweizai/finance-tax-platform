package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumEvaluateBo;
import com.abc12366.bangbang.service.CurrEvaluateService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学堂课程评价管理模块
 *
 * @author xieyanmao
 * @create 2017-08-11
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/currEvaluate", headers = Constant.VERSION_HEAD + "=1")
public class CurrEvaluateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrEvaluateController.class);

    @Autowired
    private CurrEvaluateService currEvaluateService;

    /**
     * 课程评价列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        Map<String, Object> dataMap = new HashMap<>();
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumEvaluateBo> dataList = currEvaluateService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 课程评价列表查询
     */
    @GetMapping(path = "/selectListBycurrId")
    public ResponseEntity selectListBycurrId(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                             @RequestParam(value = "curriculumId", required = false) String curriculumId,
                                             @RequestParam(value = "grade", required = false) String grade) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId", curriculumId);//课程ID
        dataMap.put("grade", grade);//评价等级
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumEvaluateBo> dataList = currEvaluateService.selectListBycurrId(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 课程评价新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumEvaluateBo evaluateBo,HttpServletRequest request) {
        //新增课程评价信息
        evaluateBo = currEvaluateService.save(evaluateBo,request);
        return ResponseEntity.ok(Utils.kv("data", evaluateBo));
    }

    /**
     * 查询单个课程评价信息
     */
    @GetMapping(path = "/{evaluateId}")
    public ResponseEntity selectOne(@PathVariable String evaluateId) {
        //查询课程评价信息
        CurriculumEvaluateBo evaluateBo = currEvaluateService.selectEvaluate(evaluateId);
        return ResponseEntity.ok(Utils.kv("data", evaluateBo));
    }

    /**
     * 更新课程评价信息
     */
    @PutMapping(path = "/{evaluateId}")
    public ResponseEntity update(@PathVariable String evaluateId,
                                 @Valid @RequestBody CurriculumEvaluateBo evaluateBo) {
        //更新课程评价信息
        evaluateBo = currEvaluateService.update(evaluateBo);
        return ResponseEntity.ok(Utils.kv("data", evaluateBo));
    }

    /**
     * 更新课程评价状态
     *
     * @param status
     * @param evaluateId
     * @return
     */
    @PutMapping(path = "/updateStatus/{evaluateId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("evaluateId") String evaluateId) {
        currEvaluateService.updateStatus(evaluateId, status);
        return ResponseEntity.ok(Utils.kv("data", evaluateId));
    }

    /**
     * 删除课程评价信息
     */
    @DeleteMapping(path = "/{evaluateId}")
    public ResponseEntity delete(@PathVariable String evaluateId) {
        //删除课程评价信息
        String rtn = currEvaluateService.delete(evaluateId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 课程评价次数
     */
    @GetMapping(path = "/selectEvaluateCnt")
    public ResponseEntity selectEvaluateCnt(@RequestParam(value = "curriculumId", required = false) String curriculumId,
                                         @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId",curriculumId);
        dataMap.put("userId",userId);

        int rtn = currEvaluateService.selectEvaluateCnt(dataMap);
        return ResponseEntity.ok(Utils.kv("data", rtn));

    }

}
