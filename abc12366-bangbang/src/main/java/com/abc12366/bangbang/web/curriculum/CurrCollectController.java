package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumCollectBo;
import com.abc12366.bangbang.service.CurrCollectService;
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
import java.util.List;

/**
 * 课程收藏管理
 * User: xieyanmao
 * Date: 2017-08-21
 * Time: 17:23
 */
@RestController
@RequestMapping(path = "/currCollect", headers = Constant.VERSION_HEAD + "=1")
public class CurrCollectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrCollectController.class);

    @Autowired
    private CurrCollectService collectService;

    /**
     * 新增课程收藏
     */
    @PostMapping(path = "/{curriculumId}")
    public ResponseEntity insert(@PathVariable String curriculumId, HttpServletRequest request) {
        LOGGER.info("{}:{}", curriculumId, request);
        CurriculumCollectBo collectBO = collectService.insert(curriculumId, request);
        return ResponseEntity.ok(Utils.kv("data", collectBO));
    }

    /**
     * 删除课程收藏
     */
    @DeleteMapping(path = "/{curriculumId}")
    public ResponseEntity delete(@PathVariable String curriculumId, HttpServletRequest request) {
        LOGGER.info("{}:{}", curriculumId, request);
        collectService.delete(curriculumId, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 查询课程收藏列表
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity selectList(@PathVariable String userId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumCollectBo> collectBOList = collectService.selectList(userId);
        return (collectBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) collectBOList, "total", ((Page) collectBOList).getTotal
                        ()));
    }

    /**
     * 查询课程是否已收藏
     */
    @GetMapping(path = "/count/{curriculumId}")
    public ResponseEntity selectExist(@PathVariable String curriculumId,HttpServletRequest request) {
        LOGGER.info("{}", curriculumId);
        int count = Integer.parseInt(collectService.selectExist(curriculumId,request));
        return ResponseEntity.ok(Utils.kv("data", count));
    }
}
