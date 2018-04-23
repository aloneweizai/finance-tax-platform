package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.mapper.db2.CheatsTagRoMapper;
import com.abc12366.bangbang.model.question.bo.CheatsBo;
import com.abc12366.bangbang.model.question.bo.CheatsTagBo;
import com.abc12366.bangbang.model.question.bo.CheatstjydBo;
import com.abc12366.bangbang.service.CheatsService;
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
 * 秘籍管理模块
 *
 * @author xieyanmao
 * @create 2017-09-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/cheats", headers = Constant.VERSION_HEAD + "=1")
public class CheatsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheatsController.class);

    @Autowired
    private CheatsService cheatsService;

    @Autowired
    private CheatsTagRoMapper tagRoMapper;

    /**
     * 秘籍列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "tag", required = false) String tag,
                                     @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsBo> dataList = cheatsService.selectList(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 秘籍列表查询(最新)
     */
    @GetMapping(path = "/selectListNew")
    public ResponseEntity selectListNew(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "tag", required = false) String tag,
                                     @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsBo> dataList = cheatsService.selectList(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 推荐秘籍查询列表
     * @param page 页数
     * @param size 条数
     * @param title 标题
     * @param isImage 是否按照图片排序，true：是，false：否
     * @param tag 标签
     * @param classifyCode 秘籍分类
     * @return
     */
    @GetMapping(path = "/selectListRecommend")
    public ResponseEntity selectListRecommend(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                                @RequestParam(value = "title", required = false) String title,
                                                @RequestParam(value = "isImage", required = false) Boolean isImage,
                                                @RequestParam(value = "tag", required = false) String tag,
                                                @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        dataMap.put("isImage", isImage);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsBo> dataList = cheatsService.selectListRecommend(dataMap);
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询推荐阅读(标题)
     */
    @GetMapping(path = "/selectListRecommendTitle")
    public ResponseEntity selectListRecommendTitle(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                              @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                              @RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "tag", required = false) String tag,
                                              @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatstjydBo> dataList = cheatsService.selectListRecommendTitle(dataMap);
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询你可能感兴趣的文章(标题)
     */
    @GetMapping(path = "/selectListByTag")
    public ResponseEntity selectListByTag(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                   @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                                   @RequestParam(value = "title", required = false) String title,
                                                   @RequestParam(value = "tag", required = false) String tag,
                                                   @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatstjydBo> dataList = cheatsService.selectListByTag(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询热门秘籍
     */
    @GetMapping(path = "/selectListByBrowseNum")
    public ResponseEntity selectListByBrowseNum(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "tag", required = false) String tag,
                                     @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsBo> dataList = cheatsService.selectListByBrowseNum(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 秘籍新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CheatsBo cheatsBo) {
        //新增秘籍信息
        cheatsBo = cheatsService.save(cheatsBo);
        return ResponseEntity.ok(Utils.kv("data", cheatsBo));
    }

    /**
     * 查询单个秘籍信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询秘籍信息
        CheatsBo cheatsBo = cheatsService.selectCheats(id);
        return ResponseEntity.ok(Utils.kv("data", cheatsBo));
    }

    /**
     * 查询单个秘籍信息(
     */
    @GetMapping(path = "/selectCheats/{id}")
    public ResponseEntity selectCheats(@PathVariable String id) {
        //查询秘籍信息
        CheatsBo cheatsBo = cheatsService.selectCheats(id);
        return ResponseEntity.ok(Utils.kv("data", cheatsBo));
    }

    /**
     * 更新秘籍信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody CheatsBo cheatsBo) {
        //更新秘籍信息
        cheatsBo = cheatsService.update(cheatsBo);
        return ResponseEntity.ok(Utils.kv("data", cheatsBo));
    }

    /**
     * 更新秘籍状态
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("id") String id) {
        cheatsService.updateStatus(id, status);
        return ResponseEntity.ok(Utils.kv("data", id));
    }

    /**
     * 删除秘籍信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除问题信息
        String rtn = cheatsService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 查询热议标签
     */
    @GetMapping(path = "/selectTagList")
    public ResponseEntity selectTagList() {
        List<CheatsTagBo> dataList = cheatsService.selectTagList();
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 更新浏览量
     */
    @PutMapping(path = "/updateBrowseNum/{id}")
    public ResponseEntity updateBrowseNum(@PathVariable String id) {
        LOGGER.info("{}", id);
        //更新浏览量信息
        String rtn = cheatsService.updateBrowseNum(id);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


    /**
     * 查询我的文章
     */
    @GetMapping(path = "/selectMyCheatsList")
    public ResponseEntity selectMyCheatsList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                        @RequestParam(value = "isTip", required = false) String isTip,
                                        @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("isTip", isTip);//是否被举报，1为被举报
        dataMap.put("userId", userId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CheatsBo> dataList = cheatsService.selectMyCheatsList(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    @PutMapping(path = "/recommend/{id}/{isRecommend}")
    public ResponseEntity recommend(@PathVariable String id, @PathVariable Boolean isRecommend,@Valid @RequestBody CheatsBo cheatsBo) {
        cheatsService.recommend(id, isRecommend,cheatsBo);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 查询秘籍和话题总数
     */
    @GetMapping(path = "/selectCheatsAndQuestionCount")
    public ResponseEntity selectCheatsAndQuestionCount() {
        int count = cheatsService.selectCheatsAndQuestionCount();
        return ResponseEntity.ok(Utils.kv("data", count));

    }

}
