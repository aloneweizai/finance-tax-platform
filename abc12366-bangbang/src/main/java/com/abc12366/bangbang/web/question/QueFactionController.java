package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.mapper.db2.QuestionFactionClassifyRoMapper;
import com.abc12366.bangbang.mapper.db2.QuestionFactionTagRoMapper;
import com.abc12366.bangbang.model.question.QuestionFactionClassify;
import com.abc12366.bangbang.model.question.QuestionFactionTag;
import com.abc12366.bangbang.model.question.bo.*;
import com.abc12366.bangbang.service.QueFactionHonorService;
import com.abc12366.bangbang.service.QueFactionService;
import com.abc12366.bangbang.service.QueFactionTaskService;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 邦派管理模块
 *
 * @author xieyanmao
 * @create 2017-09-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queFaction", headers = Constant.VERSION_HEAD + "=1")
public class QueFactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionController.class);

    @Autowired
    private QueFactionService queFactionService;

    @Autowired
    private QueFactionHonorService honorService;

    @Autowired
    private QueFactionTaskService taskService;

    @Autowired
    private QuestionFactionTagRoMapper tagRoMapper;

    @Autowired
    private QuestionFactionClassifyRoMapper classifyRoMapper;

    /**
     * 我管理的邦派列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionBo> dataList = queFactionService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 我加入的邦派列表查询
     */
    @GetMapping(path = "/selectListTj")
    public ResponseEntity selectListTj(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionListBo> dataList = queFactionService.selectListTj(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 优秀邦派列表查询
     */
    @GetMapping(path = "/selectListExcellent")
    public ResponseEntity selectListExcellent(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                       @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                              @RequestParam(value = "tag", required = false) String tag,
                                              @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//
//        queFactionService.autoCalculateFactionHonor();
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionListBo> dataList = queFactionService.selectListExcellent(dataMap);


        if(dataList != null){
            for(QuestionFactionListBo factionBo:dataList){
                List<QuestionFactionTag> tagList = tagRoMapper.selectList(factionBo.getFactionId());
                List<QuestionFactionClassify> classifyList = classifyRoMapper.selectList(factionBo.getFactionId());
                factionBo.setTagList(tagList);
                factionBo.setClassifyList(classifyList);
            }
        }

        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 潜力邦派列表查询
     */
    @GetMapping(path = "/selectListPotential")
    public ResponseEntity selectListPotential(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                              @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                              @RequestParam(value = "tag", required = false) String tag,
                                              @RequestParam(value = "classifyCode", required = false) String classifyCode) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tag", tag);//
        dataMap.put("classifyCode", classifyCode);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionListBo> dataList = queFactionService.selectListPotential(dataMap);


        if(dataList != null){
            for(QuestionFactionListBo factionBo:dataList){
                List<QuestionFactionTag> tagList = tagRoMapper.selectList(factionBo.getFactionId());
                List<QuestionFactionClassify> classifyList = classifyRoMapper.selectList(factionBo.getFactionId());
                factionBo.setTagList(tagList);
                factionBo.setClassifyList(classifyList);
            }
        }

        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionFactionBo factionBo) {
        //新增邦派信息
        factionBo = queFactionService.save(factionBo);
        return ResponseEntity.ok(Utils.kv("data", factionBo));
    }

    /**
     * 查询单个邦派信息
     */
    @GetMapping(path = "/{factionId}")
    public ResponseEntity selectOne(@PathVariable String factionId) {
        //查询单个邦派信息
        QuestionFactionBo factionBo = queFactionService.selectQuestionFaction(factionId);
        return ResponseEntity.ok(Utils.kv("data", factionBo));
    }

    /**
     * 查询(我管理的邦派)单个邦派信息
     */
    @GetMapping(path = "/selectQuestionFactionTj")
    public ResponseEntity selectQuestionFactionTj(@RequestParam(value = "factionId", required = false) String factionId) {
        //查询单个邦派信息
        QuestionFactionTjBo factionBo = queFactionService.selectQuestionFactionTj(factionId);
        return ResponseEntity.ok(Utils.kv("data", factionBo));
    }

    /**
     * 邦派动态列表查询
     */
    @GetMapping(path = "/selectdtListByFactionId")
    public ResponseEntity selectdtListByFactionId(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "factionId", required = false) String factionId) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionAnswerBo> dataList = queFactionService.selectdtListByFactionId(factionId);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 更新邦派信息
     */
    @PutMapping(path = "/{factionId}")
    public ResponseEntity update(@PathVariable String factionId,
                                 @Valid @RequestBody QuestionFactionBo factionBo) {
        //更新邦派信息
        factionBo = queFactionService.update(factionBo);
        return ResponseEntity.ok(Utils.kv("data", factionBo));
    }

    /**
     * 更新邦派信息(邦派设置，邦派公告)
     */
    @PutMapping(path = "/updateSelect")
    public ResponseEntity updateSelect(@Valid @RequestBody QuestionFactionBo factionBo) {
        //更新邦派信息(邦派设置，邦派公告)
        factionBo = queFactionService.updateSelect(factionBo);
        return ResponseEntity.ok(Utils.kv("data", factionBo));
    }

    /**
     * 更新邦派状态
     *
     * @param status
     * @param factionId
     * @return
     */
    @PutMapping(path = "/updateStatus/{factionId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("factionId") String factionId) {
        queFactionService.updateStatus(factionId, status);
        return ResponseEntity.ok(Utils.kv("data", factionId));
    }

    /**
     * 删除邦派信息
     */
    @DeleteMapping(path = "/{factionId}")
    public ResponseEntity delete(@PathVariable String factionId) {
        //删除邦派信息
        String rtn = queFactionService.delete(factionId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


    /**
     * 邦派排行列表查询
     */
    @GetMapping(path = "/selecFactionPhList")
    public ResponseEntity selecFactionPhList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "honorTime", required = false) String honorTime) {
        if(honorTime == null || "".equals(honorTime)){
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            Calendar c = Calendar.getInstance();

            //过去一月
            c.setTime(new Date());
            c.add(Calendar.MONTH, -1);
            Date m = c.getTime();
            honorTime = format.format(m);
        }
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionPhBo> dataList = honorService.selectList(honorTime);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派排行列表查询
     */
    @GetMapping(path = "/selecFactionljPhList")
    public ResponseEntity selecFactionljPhList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                              @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionPhBo> dataList = honorService.selectljList();
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派任务动态列表查询
     */
    @GetMapping(path = "/selectListdt")
    public ResponseEntity selectListdt(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                               @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionTaskBo> dataList = taskService.selectListdt();
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派任务情况查询
     */
    @GetMapping(path = "/selectTaskList")
    public ResponseEntity selectTaskList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                         @RequestParam(value = "factionId", required = false) String factionId,
                                             @RequestParam(value = "taskTime", required = false) String taskTime) {
        if(taskTime == null || "".equals(taskTime)){
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//            Calendar c = Calendar.getInstance();
//
//            //过去一月
//            c.setTime(new Date());
//            c.add(Calendar.MONTH, -1);
//            Date m = c.getTime();
            taskTime = format.format(new Date());
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionId);//邦派ID
        dataMap.put("taskTime", taskTime);//年月201711
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionTaskBo> dataList = taskService.selectTaskList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }


}
