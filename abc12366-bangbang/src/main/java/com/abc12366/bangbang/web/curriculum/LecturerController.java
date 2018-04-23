package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumLecturerBo;
import com.abc12366.bangbang.service.LecturerService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
 * 学堂讲师管理模块
 *
 * @author xieyanmao
 * @create 2017-08-11
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/lecturer", headers = Constant.VERSION_HEAD + "=1")
public class LecturerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerController.class);

    @Autowired
    private LecturerService lecturerService;

    /**
     * 讲师列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "lecturerName", required = false) String lecturerName) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("lecturerName", lecturerName);//讲师姓名
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumLecturerBo> dataList = lecturerService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 根据课程Id查询讲师信息
     */
    @GetMapping(path = "/selectListByCurr")
    public ResponseEntity selectListByCurr(@RequestParam(value = "curriculumId", required = false) String curriculumId) {
        List<CurriculumLecturerBo> dataList = lecturerService.selectListByCurr(curriculumId);
        return ResponseEntity.ok(Utils.kv("dataList",dataList));

    }

    /**
     * 讲师新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumLecturerBo lecturerBo) {
        //新增讲师信息
        lecturerBo = lecturerService.save(lecturerBo);
        return ResponseEntity.ok(Utils.kv("data", lecturerBo));
    }

    /**
     * 查询单个讲师信息
     */
    @GetMapping(path = "/{lecturerId}")
    public ResponseEntity selectOne(@PathVariable String lecturerId) {
        //查询讲师信息
        CurriculumLecturerBo lecturerBo = lecturerService.selectLecturer(lecturerId);
        return ResponseEntity.ok(Utils.kv("data", lecturerBo));
    }

    /**
     * 更新讲师信息
     */
    @PutMapping(path = "/{lecturerId}")
    public ResponseEntity update(@PathVariable String lecturerId,
                                 @Valid @RequestBody CurriculumLecturerBo lecturerBo) {
        //更新讲师信息
        lecturerBo = lecturerService.update(lecturerBo);
        return ResponseEntity.ok(Utils.kv("data", lecturerBo));
    }

    /**
     * 更新讲师状态
     *
     * @param status
     * @param lecturerId
     * @return
     */
    @PutMapping(path = "/updateStatus/{lecturerId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("lecturerId") String lecturerId) {
        lecturerService.updateStatus(lecturerId, status);
        return ResponseEntity.ok(Utils.kv("data", lecturerId));
    }

    /**
     * 删除讲师信息
     */
    @DeleteMapping(path = "/{lecturerId}")
    public ResponseEntity delete(@PathVariable String lecturerId) {
        //删除讲师信息
        String rtn = lecturerService.delete(lecturerId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
