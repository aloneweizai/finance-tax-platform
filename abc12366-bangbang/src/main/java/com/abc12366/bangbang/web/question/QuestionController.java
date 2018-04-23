package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.mapper.db2.QuestionTagRoMapper;
import com.abc12366.bangbang.model.bo.TopicRecommendParamBO;
import com.abc12366.bangbang.model.question.QuestionInvite;
import com.abc12366.bangbang.model.question.QuestionTag;
import com.abc12366.bangbang.model.question.bo.*;
import com.abc12366.bangbang.service.QuestionService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 帮帮问题管理模块
 *
 * @author xieyanmao
 * @create 2017-09-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/questionbb", headers = Constant.VERSION_HEAD + "=1")
public class QuestionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionTagRoMapper tagRoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 问题列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "tag", required = false) String tag,
                                     @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);
        dataMap.put("tag", tag);
        dataMap.put("classifyCode", classifyCode);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectList(dataMap);
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 问题列表查询(最新)
     */
    @GetMapping(path = "/selectListNew")
    public ResponseEntity selectListNew(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "tag", required = false) String tag,
                                        @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);
        dataMap.put("tag", tag);
        dataMap.put("classifyCode", classifyCode);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectList(dataMap);
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 推荐问题
     */
    @GetMapping(path = "/selectListRecommend")
    public ResponseEntity selectListRecommend(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                              @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                              @RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "tag", required = false) String tag,
                                              @RequestParam(value = "classifyCode", required = false) String
                                                          classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);
        dataMap.put("tag", tag);
        dataMap.put("classifyCode", classifyCode);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectListRecommend(dataMap);
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));
    }

    /**
     * 高悬赏问题
     */
    @GetMapping(path = "/selectListByPoints")
    public ResponseEntity selectListByPoints(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                             @RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "tag", required = false) String tag,
                                             @RequestParam(value = "classifyCode", required = false) String
                                                         classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectListByPoints(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询热门问题
     */
    @GetMapping(path = "/selectListByBrowseNum")
    public ResponseEntity selectListByBrowseNum(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                        size,
                                                @RequestParam(value = "title", required = false) String title,
                                                @RequestParam(value = "tag", required = false) String tag,
                                                @RequestParam(value = "classifyCode", required = false) String
                                                            classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectListByBrowseNum(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询热门问题
     */
    @GetMapping(path = "/selectListByBrowseNumForqt")
    public ResponseEntity selectListByBrowseNumForqt(@RequestParam(value = "page", defaultValue = Constant.pageNum)
                                                                 int page,
                                                     @RequestParam(value = "size", defaultValue = Constant.pageSize)
                                                             int size,
                                                     @RequestParam(value = "title", required = false) String title,
                                                     @RequestParam(value = "tag", required = false) String tag,
                                                     @RequestParam(value = "classifyCode", required = false) String
                                                                 classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//s
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectListByBrowseNum(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询等你回答的问题
     */
    @GetMapping(path = "/selectListWait")
    public ResponseEntity selectListWait(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                         @RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "userId", required = false) String userId,
                                         @RequestParam(value = "tag", required = false) String tag,
                                         @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("userId", userId);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectListWait(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 查询已解决的问题
     */
    @GetMapping(path = "/selectListAccept")
    public ResponseEntity selectListAccept(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                           @RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "tag", required = false) String tag,
                                           @RequestParam(value = "classifyCode", required = false) String
                                                       classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", title);//
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectListAccept(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 帮友热议列表查询
     */
    @GetMapping(path = "/selectListry")
    public ResponseEntity selectListry(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                       @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        Map<String, Object> dataMap = new HashMap<>();
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionryBo> dataList = questionService.selectListry(dataMap);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));

    }

    /**
     * 帮友热议列表查询
     */
    @GetMapping(path = "/selectListryForqt")
    public ResponseEntity selectListryForqt() {

        if (redisTemplate.hasKey("CMS_SelectListryForqt")) {
            List<QuestionryBo> dataList = JSONArray.parseArray(redisTemplate.opsForValue().get
                    ("CMS_SelectListryForqt"), QuestionryBo.class);
            LOGGER.info("从Redis获取数据:" + JSONArray.toJSONString(dataList));
            return ResponseEntity.ok(Utils.kv("dataList", dataList, "total", dataList.size()));
        } else {
            Map<String, Object> dataMap = new HashMap<>();
            PageHelper.startPage(1, 9, true).pageSizeZero(true).reasonable(true);
            List<QuestionryBo> dataList = questionService.selectListry(dataMap);
            redisTemplate.opsForValue().set("CMS_SelectListryForqt", JSONArray.toJSONString(dataList), RedisConstant
                    .DAY_1, TimeUnit.DAYS);
            return (dataList == null) ?
                    ResponseEntity.ok(Utils.kv()) :
                    ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                            ()));
        }

    }

    /**
     * 问题新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionBo questionBo, HttpServletRequest request) {
        //新增问题信息
        questionBo = questionService.save(questionBo, request);
        redisTemplate.delete("CMS_SelectListryForqt");
        return ResponseEntity.ok(Utils.kv("data", questionBo));
    }

    /**
     * 查询单个问题信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询问题信息
        QuestionBo questionBo = questionService.selectQuestion(id);
        return ResponseEntity.ok(Utils.kv("data", questionBo));
    }

    /**
     * 查询单个问题信息(
     */
    @GetMapping(path = "/selectQuestion/{id}")
    public ResponseEntity selectQuestion(@PathVariable String id) {
        //查询问题信息
        QuestionBo questionBo = questionService.selectQuestion(id);
        return ResponseEntity.ok(Utils.kv("data", questionBo));
    }

    /**
     * 更新问题信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody QuestionBo questionBo) {
        //更新问题信息
        questionBo = questionService.update(questionBo);
        redisTemplate.delete("CMS_SelectListryForqt");
        return ResponseEntity.ok(Utils.kv("data", questionBo));
    }

    /**
     * 更新问题状态
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("id") String id) {
        questionService.updateStatus(id, status);
        redisTemplate.delete("CMS_SelectListryForqt");
        return ResponseEntity.ok(Utils.kv("data", id));
    }

    /**
     * 删除问题信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除问题信息
        String rtn = questionService.delete(id);
        redisTemplate.delete("CMS_SelectListryForqt");
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 查询热议标签
     */
    @GetMapping(path = "/selectTagList")
    public ResponseEntity selectTagList() {
        List<QuestionTagBo> dataList = questionService.selectTagList();
        return ResponseEntity.ok(Utils.kv("dataList", dataList));

    }

    /**
     * 更新浏览量
     */
    @PutMapping(path = "/updateBrowseNum/{id}")
    public ResponseEntity updateBrowseNum(@PathVariable String id) {
        LOGGER.info("{}", id);
        //更新浏览量信息
        String rtn = questionService.updateBrowseNum(id);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 话题推荐管理
     */
    @GetMapping(path = "/selectListTopicRecommend")
    public ResponseEntity topicRecommendList(TopicRecommendParamBO param) {
        PageHelper.startPage(param.getPage(), param.getSize(), true).pageSizeZero(true).reasonable(true);
        List<RecommendBo> dataList = questionService.selectList(param);
//        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
        return (dataList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal
                        ()));
    }

    @PutMapping(path = "/recommend/{id}/{isRecommend}")
    public ResponseEntity recommend(@PathVariable String id, @PathVariable Boolean isRecommend) {
        questionService.recommend(id, isRecommend);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 查询举报列表
     */
    @GetMapping(path = "/selectTipList/{userId}")
    public ResponseEntity selectTipList(@PathVariable String userId,
                                        @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionjbBo> questionBoList = questionService.selectTipList(userId);
        return (questionBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) questionBoList, "total", ((Page) questionBoList).getTotal
                        ()));
    }

    /**
     * 查询邀我回答的列表
     */
    @GetMapping(path = "/selectInviteList/{userId}")
    public ResponseEntity selectInviteList(@PathVariable String userId,
                                           @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> questionBoList = questionService.selectInviteList(userId);
        if (questionBoList != null) {
            for (QuestionBo questionBo : questionBoList) {
                if (questionBo.getId() != null) {
                    List<QuestionTag> tagList = tagRoMapper.selectList(questionBo.getId());
                    questionBo.setTagList(tagList);
                }
            }
        }
        return (questionBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) questionBoList, "total", ((Page) questionBoList).getTotal
                        ()));
    }

    /**
     * 查询我的提问
     */
    @GetMapping(path = "/selectMyQuestionList")
    public ResponseEntity selectMyQuestionList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                               @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                               @RequestParam(value = "isAccept", required = false) String isAccept,
                                               @RequestParam(value = "isTip", required = false) String isTip,
                                               @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("isAccept", isAccept);//是否被采纳，1为被采纳
        dataMap.put("isTip", isTip);//是否被举报，1为被举报
        dataMap.put("userId", userId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> dataList = questionService.selectMyQuestionList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 我的帮帮
     */
    @GetMapping(path = "/selectMybangbang/{userId}")
    public ResponseEntity selectMybangbang(@PathVariable String userId) {
        MyQuestionTjBo myQuestionTjBo = questionService.selectMybangbang(userId);
        return ResponseEntity.ok(Utils.kv("data", myQuestionTjBo));
    }

    /**
     * 查询我管理的话题
     */
    @GetMapping(path = "/selectMyManageQuesList/{userId}")
    public ResponseEntity selectMyManageQuesList(@PathVariable String userId,
                                                 @RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                         page,
                                                 @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                             size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionBo> questionBoList = questionService.selectMyManageQuesList(userId);
        if (questionBoList != null) {
            for (QuestionBo questionBo : questionBoList) {
                if (questionBo.getId() != null) {
                    List<QuestionTag> tagList = tagRoMapper.selectList(questionBo.getId());
                    questionBo.setTagList(tagList);
                }
            }
        }
        return (questionBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) questionBoList, "total", ((Page) questionBoList).getTotal
                        ()));
    }

    /**
     * 根据问题ID查询相关标签
     */
    @GetMapping(path = "/selectTagList/{id}")
    public ResponseEntity selectTagList(@PathVariable String id,
                                        @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", id, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionTag> questionTagList = questionService.selectTagList(id);
        return (questionTagList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) questionTagList, "total", ((Page) questionTagList)
                        .getTotal
                        ()));
    }

    /**
     * 给问题打标签
     */
    @PutMapping(path = "/updateQuesTag")
    public ResponseEntity updateQuesTag(@RequestBody QuestionTagListBo questionTagListBo) {
        //给问题打标签
        questionTagListBo = questionService.updateQuesTag(questionTagListBo);
        return ResponseEntity.ok(Utils.kv("data", questionTagListBo));
    }

    /**
     * 更新问题为已读
     */
    @PutMapping(path = "/updateIsRead")
    public ResponseEntity updateIsRead(@RequestBody QuestionInvite invite) {
        //更新问题为已读
        invite = questionService.updateIsRead(invite);
        return ResponseEntity.ok(Utils.kv("data", invite));
    }

    /**
     * 查询我的动态
     */
    @GetMapping(path = "/selectQcDtList/{userId}")
    public ResponseEntity selectQcDtList(@PathVariable String userId,
                                         @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}:{}", userId, page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionDtBo> questionBoList = questionService.selectQcDtList(userId);
        return (questionBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) questionBoList, "total", ((Page) questionBoList).getTotal
                        ()));
    }

}
