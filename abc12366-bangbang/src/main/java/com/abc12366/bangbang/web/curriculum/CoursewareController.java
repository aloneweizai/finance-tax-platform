package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumCoursewareBo;
import com.abc12366.bangbang.service.CoursewareService;
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
 * 学堂课件管理模块
 *
 * @author xieyanmao
 * @create 2017-08-10
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/courseware", headers = Constant.VERSION_HEAD + "=1")
public class CoursewareController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);

    @Autowired
    private CoursewareService coursewareService;

    /**
     * 课件列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "curriculumId", required = false) String curriculumId,
                                     @RequestParam(value = "fileName", required = false) String fileName,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "uploadWay", required = false) String uploadWay,
                                     @RequestParam(value = "chapterId", required = false) String chapterId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId", curriculumId);//课程ID
        dataMap.put("chapterId", chapterId);//章节ID
        dataMap.put("fileName", fileName);//文件名称
        dataMap.put("title", title);//课件标题
        dataMap.put("type", type);//课件类型
        dataMap.put("uploadWay", uploadWay);//上传方式
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumCoursewareBo> dataList = coursewareService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 课件列表去重查询
     */
    @GetMapping(path = "/list")
    public ResponseEntity selectCoursewareList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "curriculumId", required = false) String curriculumId,
                                     @RequestParam(value = "fileName", required = false) String fileName,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "uploadWay", required = false) String uploadWay,
                                     @RequestParam(value = "chapterId", required = false) String chapterId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId", curriculumId);//课程ID
        dataMap.put("chapterId", chapterId);//章节ID
        dataMap.put("fileName", fileName);//文件名称
        dataMap.put("title", title);//课件标题
        dataMap.put("type", type);//课件类型
        dataMap.put("uploadWay", uploadWay);//上传方式
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumCoursewareBo> dataList = coursewareService.selectCoursewareList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 课件新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumCoursewareBo coursewareBo) {
        //新增课件信息
        coursewareBo = coursewareService.save(coursewareBo);
        return ResponseEntity.ok(Utils.kv("data", coursewareBo));
    }

    /**
     * 查询单个课件信息
     */
    @GetMapping(path = "/{coursewareId}")
    public ResponseEntity selectOne(@PathVariable String coursewareId) {
        //查询课件信息
        CurriculumCoursewareBo coursewareBo = coursewareService.selectCourseware(coursewareId);
        return ResponseEntity.ok(Utils.kv("data", coursewareBo));
    }

    /**
     * 更新课件信息
     */
    @PutMapping(path = "/{coursewareId}")
    public ResponseEntity update(@PathVariable String coursewareId,
                                 @Valid @RequestBody CurriculumCoursewareBo coursewareBo) {
        //更新课件信息
        coursewareBo = coursewareService.update(coursewareBo);
        return ResponseEntity.ok(Utils.kv("data", coursewareBo));
    }

    /**
     * 更新课件状态
     *
     * @param status
     * @param coursewareId
     * @return
     */
    @PutMapping(path = "/updateStatus/{coursewareId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("coursewareId") String coursewareId) {
        coursewareService.updateStatus(coursewareId, status);
        return ResponseEntity.ok(Utils.kv("data", coursewareId));
    }

    /**
     * 删除课件信息
     */
    @DeleteMapping(path = "/{coursewareId}")
    public ResponseEntity delete(@PathVariable String coursewareId) {
        //删除课件信息
        String rtn = coursewareService.delete(coursewareId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 查询用户是否有权限播放
     */
    @GetMapping(path = "/selectCourseware/{coursewareId}")
    public ResponseEntity selectCourseware(@PathVariable String coursewareId,HttpServletRequest request) {
        //查询课件信息
        String userId = Utils.getUserId();

        CurriculumCoursewareBo coursewareBo = coursewareService.selectCoursewarebf(coursewareId,userId);
        return ResponseEntity.ok(Utils.kv("data", coursewareBo));
    }

}
