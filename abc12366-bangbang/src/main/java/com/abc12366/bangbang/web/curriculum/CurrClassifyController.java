package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.CurriculumClassifyTag;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumClassifyBo;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumClassifyTagBo;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumClassifysBo;
import com.abc12366.bangbang.service.CurrClassifyService;
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
 * 课程分类管理模块
 *
 * @author xieyanmao
 * @create 2017-08-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/classify", headers = Constant.VERSION_HEAD + "=1")
public class CurrClassifyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrClassifyController.class);

    @Autowired
    private CurrClassifyService classifyService;

    /**
     * 课程分类列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "classifyId", required = false) String classifyId,
                                     @RequestParam(value = "parentId", required = false) String parentId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("classifyId",classifyId);//分类ID
        dataMap.put("parentId",parentId);//父ID
        List<CurriculumClassifyBo> dataList = classifyService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 课程分类列表查询(供前端使用)
     */
    @GetMapping(path = "/selectListsy")
    public ResponseEntity selectListsy(@RequestParam(value = "classifyId", required = false) String classifyId,
                                     @RequestParam(value = "parentId", required = false) String parentId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("classifyId",classifyId);//分类ID
        dataMap.put("parentId",parentId);//父ID
        List<CurriculumClassifyBo> dataList = classifyService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 课程分类列表查询(查大类小类标签)(供前端使用)
     */
    @GetMapping(path = "/selectClassifyListsy")
    public ResponseEntity selectClassifyListsy() {
        List<CurriculumClassifysBo> dataList = classifyService.selectClassifyListsy();
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 课程分类新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumClassifyBo classifyBo) {
        //新增课程分类信息
        classifyBo = classifyService.save(classifyBo);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 查询单个课程分类信息
     */
    @GetMapping(path = "/{classifyId}")
    public ResponseEntity selectOne(@PathVariable String classifyId) {
        //查询课程分类信息
        CurriculumClassifyBo classifyBo = classifyService.selectClassify(classifyId);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 根据分类查询相关标签
     */
    @GetMapping(path = "/selectClassifyTagList")
    public ResponseEntity selectClassifyTagList(@RequestParam(value = "classifyId", required = false) String classifyId,
                                                @RequestParam(value = "tagName", defaultValue = "") String tagName) {
        Map<String, Object> map = new HashMap<>();
        map.put("classifyId",classifyId);
        map.put("tagName",tagName);
        List<CurriculumClassifyTagBo> dataList = classifyService.selectClassifyTagList(map);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 更新课程分类信息
     */
    @PutMapping(path = "/{classifyId}")
    public ResponseEntity update(@PathVariable String classifyId,
                                 @Valid @RequestBody CurriculumClassifyBo classifyBo) {
        //更新课程分类信息
        classifyBo = classifyService.update(classifyBo);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 更新课程分类信息
     */
    @PutMapping(path = "/updateClassify/{classifyId}")
    public ResponseEntity updateClassify(@PathVariable String classifyId,
                                 @Valid @RequestBody CurriculumClassifyBo classifyBo) {
        //更新课程分类信息
        classifyBo = classifyService.updateClassify(classifyBo);
        return ResponseEntity.ok(Utils.kv("data", classifyBo));
    }

    /**
     * 更新课程分类状态
     *
     * @param status
     * @param classifyId
     * @return
     */
    @PutMapping(path = "/updateStatus/{classifyId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("classifyId") String classifyId) {
        classifyService.updateStatus(classifyId, status);
        return ResponseEntity.ok(Utils.kv("data", classifyId));
    }

    /**
     * 删除课程分类信息
     */
    @DeleteMapping(path = "/{classifyId}")
    public ResponseEntity delete(@PathVariable String classifyId) {
        //删除课程分类信息
        String rtn = classifyService.delete(classifyId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /*课程添加标签，并且让标签和选择的课程分类关联*/
    @PutMapping(path = "/addTagAndRefClassify")
    public ResponseEntity addTagAndRefClassify(@RequestBody List<CurriculumClassifyTag> tagList) {
        classifyService.addTagAndRefClassify(tagList);
        return ResponseEntity.ok(Utils.kv("dataList", tagList));
    }

}
