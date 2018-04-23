package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.IdsBo;
import com.abc12366.cms.model.bo.TopicBo;
import com.abc12366.cms.model.bo.TopicListBo;
import com.abc12366.cms.service.TopicService;
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
 * 专题管理模块
 *
 * @author xieyanmao
 * @create 2017-05-08
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/topic", headers = Constant.VERSION_HEAD + "=1")
public class TopicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    /**
     * 查询专题列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "siteId", required = false) String siteId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("siteId", siteId);//站点ID
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TopicBo> dataList = topicService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 根据模板查询专题信息
     */
    @GetMapping(path = "/selectListBytplContent")
    public ResponseEntity selectListBytplContent(@RequestParam(value = "startTime", required = false) String startTime,
                                                 @RequestParam(value = "endTime", required = false) String endTime,
                                                 @RequestParam(value = "tplContent", required = false) String
                                                             tplContent) {
        //查询专题信息
        Map<String, Object> dataMap = new HashMap<>();
        List<TopicBo> dataList = topicService.selectListBytplContent(tplContent);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 新增专题信息
     */
    @PostMapping
    public ResponseEntity save(@RequestBody TopicBo topicBo) {
        LOGGER.info("{}", topicBo);
        //新增专题信息
        topicBo = topicService.save(topicBo);
        LOGGER.info("{}", topicBo);
        return ResponseEntity.ok(Utils.kv("data", topicBo));
    }

    /**
     * 查询单个专题信息
     */
    @GetMapping(path = "/{topicId}")
    public ResponseEntity selectOne(@PathVariable String topicId) {
        LOGGER.info("{}", topicId);
        //查询专题信息
        TopicBo topicBo = topicService.selectTopic(topicId);
        LOGGER.info("{}", topicBo);
        return ResponseEntity.ok(Utils.kv("data", topicBo));
    }

    /**
     * 更新专题信息
     */
    @PutMapping(path = "/{topicId}")
    public ResponseEntity update(@PathVariable String topicId,
                                 @Valid @RequestBody TopicBo topicBo) {

        LOGGER.info("{}", topicBo);
        //更新专题信息
        topicBo = topicService.update(topicBo);
        LOGGER.info("{}", topicBo);
        return ResponseEntity.ok(Utils.kv("data", topicBo));
    }

    /**
     * 批量更新专题信息
     */
    @PutMapping(path = "/updateList")
    public ResponseEntity updateList(@Valid @RequestBody TopicListBo topicListBo) {

        LOGGER.info("{}", topicListBo);
        //更新评论信息
        topicListBo = topicService.updateList(topicListBo);
        LOGGER.info("{}", topicListBo);
        return ResponseEntity.ok(Utils.kv("data", topicListBo));
    }

    /**
     * 删除专题信息
     */
    @DeleteMapping(path = "/{topicId}")
    public ResponseEntity delete(@PathVariable String topicId) {
        LOGGER.info("{}", topicId);
        //删除专题信息
        String rtn = topicService.delete(topicId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 批量删除专题信息
     */
    @PostMapping(path = "/deleteList")
    public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //批量删除专题信息
        String rtn = topicService.deleteList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }


}
