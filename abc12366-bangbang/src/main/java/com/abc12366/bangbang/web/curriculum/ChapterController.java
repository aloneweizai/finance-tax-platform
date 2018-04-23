package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumChapterBo;
import com.abc12366.bangbang.service.ChapterService;
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
 * 学堂章节管理模块
 *
 * @author xieyanmao
 * @create 2017-08-11
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/chapter", headers = Constant.VERSION_HEAD + "=1")
public class ChapterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChapterController.class);

    @Autowired
    private ChapterService chapterService;

    /**
     * 章节列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "curriculumId", required = false) String curriculumId,
                                     @RequestParam(value = "chapterId", required = false) String chapterId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("curriculumId",curriculumId);
        dataMap.put("chapterId",chapterId);
        List<CurriculumChapterBo> dataList = chapterService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 章节新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CurriculumChapterBo chapterBo) {
        //新增章节信息
        chapterBo = chapterService.save(chapterBo);
        return ResponseEntity.ok(Utils.kv("data", chapterBo));
    }

    /**
     * 查询单个章节信息
     */
    @GetMapping(path = "/{chapterId}")
    public ResponseEntity selectOne(@PathVariable String chapterId) {
        //查询章节信息
        CurriculumChapterBo chapterBo = chapterService.selectChapter(chapterId);
        return ResponseEntity.ok(Utils.kv("data", chapterBo));
    }

    /**
     * 更新章节信息
     */
    @PutMapping(path = "/{chapterId}")
    public ResponseEntity update(@PathVariable String chapterId,
                                 @Valid @RequestBody CurriculumChapterBo chapterBo) {
        //更新章节信息
        chapterBo = chapterService.update(chapterBo);
        return ResponseEntity.ok(Utils.kv("data", chapterBo));
    }

    /**
     * 更新章节状态
     *
     * @param status
     * @param chapterId
     * @return
     */
    @PutMapping(path = "/updateStatus/{chapterId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("chapterId") String chapterId) {
        chapterService.updateStatus(chapterId, status);
        return ResponseEntity.ok(Utils.kv("data", chapterId));
    }

    /**
     * 删除章节信息
     */
    @DeleteMapping(path = "/{chapterId}")
    public ResponseEntity delete(@PathVariable String chapterId) {
        //删除章节信息
        String rtn = chapterService.delete(chapterId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
