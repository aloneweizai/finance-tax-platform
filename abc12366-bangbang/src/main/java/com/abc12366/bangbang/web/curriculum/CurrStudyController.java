package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumStudyBo;
import com.abc12366.bangbang.service.CurrStudyService;
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
 * 学堂课程学习管理模块
 *
 * @author xieyanmao
 * @create 2017-08-11
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/currStudy", headers = Constant.VERSION_HEAD + "=1")
public class CurrStudyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrStudyController.class);

    @Autowired
    private CurrStudyService currStudyService;

    /**
     * 课程学习列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "curriculumId", required = false) String curriculumId,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "begintime", required = false) String begintime,
                                     @RequestParam(value = "endtime", required = false) String endtime) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId",curriculumId);
        dataMap.put("username",username);
        if(begintime != null && !"".equals(begintime)){
            dataMap.put("begintime", begintime);//开始时间
        }
        if(endtime != null && !"".equals(endtime)){
            dataMap.put("endtime", endtime);//结束时间
        }

        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumStudyBo> dataList = currStudyService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 课程学习新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumStudyBo studyBo,HttpServletRequest request) {
        //新增课程学习信息
        studyBo = currStudyService.save(studyBo,request);
        return ResponseEntity.ok(Utils.kv("data", studyBo));
    }

    /**
     * 查询单个课程学习信息
     */
    @GetMapping(path = "/{studyId}")
    public ResponseEntity selectOne(@PathVariable String studyId) {
        //查询课程学习信息
        CurriculumStudyBo studyBo = currStudyService.selectStudy(studyId);
        return ResponseEntity.ok(Utils.kv("data", studyBo));
    }

    /**
     * 更新课程学习信息
     */
    @PutMapping(path = "/{studyId}")
    public ResponseEntity update(@PathVariable String studyId,
                                 @Valid @RequestBody CurriculumStudyBo studyBo) {
        //更新课程学习信息
        studyBo = currStudyService.update(studyBo);
        return ResponseEntity.ok(Utils.kv("data", studyBo));
    }

    /**
     * 更新课程学习状态
     *
     * @param status
     * @param studyId
     * @return
     */
    @PutMapping(path = "/updateStatus/{studyId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("studyId") String studyId) {
        currStudyService.updateStatus(studyId, status);
        return ResponseEntity.ok(Utils.kv("data", studyId));
    }

    /**
     * 删除课程学习信息
     */
    @DeleteMapping(path = "/{studyId}")
    public ResponseEntity delete(@PathVariable String studyId) {
        //删除课程学习信息
        String rtn = currStudyService.delete(studyId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 课程学习次数
     */
    @GetMapping(path = "/selectStudyCnt")
    public ResponseEntity selectStudyCnt(@RequestParam(value = "curriculumId", required = false) String curriculumId,
                                     @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId",curriculumId);
        dataMap.put("userId",userId);

        int rtn = currStudyService.selectStudyCnt(dataMap);
        return ResponseEntity.ok(Utils.kv("data", rtn));

    }

}
