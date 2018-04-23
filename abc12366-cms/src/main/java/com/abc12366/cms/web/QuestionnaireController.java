package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.SubjectsdtxxtjBo;
import com.abc12366.cms.model.questionnaire.bo.QuestionnaireBO;
import com.abc12366.cms.service.QuestionnaireService;
import com.abc12366.cms.service.SubjectsService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷控制类
 *
 * @author lizhongwei
 * @create 2017-06-15
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/question", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionnaireController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionnaireController.class);

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private SubjectsService subjectsService;

    /**
     * 问卷列表查询
     *
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {

        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);

        QuestionnaireBO questionnaire = new QuestionnaireBO();
        List<QuestionnaireBO> list = questionnaireService.selectList(questionnaire);
        LOGGER.info("{}", list);
        return (list == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }


    /**
     * 查询问卷详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        QuestionnaireBO questionnaireBO = questionnaireService.selectOne(id);
        LOGGER.info("{}", questionnaireBO);
        return ResponseEntity.ok(Utils.kv("data", questionnaireBO));
    }

    /**
     * 问卷新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity submitQuestionnaire(@Valid @RequestBody QuestionnaireBO questionnaireBO) {
        LOGGER.info("{}", questionnaireBO);
        QuestionnaireBO bo = questionnaireService.insert(questionnaireBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 问卷修改
     *
     * @param questionnaireBO
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody QuestionnaireBO questionnaireBO, @PathVariable("id") String id) {
        LOGGER.info("{}", questionnaireBO);
        questionnaireBO.setId(id);
        QuestionnaireBO bo = questionnaireService.update(questionnaireBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 问卷状态修改
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping(path = "/status/{id}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("id") String id) {
        LOGGER.info("{}", status);
        questionnaireService.updateStatus(id, status);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 问卷皮肤路径修改
     *
     * @param skinUrl
     * @param id
     * @return
     */
    @PutMapping(path = "/skinUrl/{id}")
    public ResponseEntity updateSkinUrl(@Valid @RequestBody String skinUrl, @PathVariable("id") String id) {
        LOGGER.info("{}", skinUrl);
        questionnaireService.updateSkinUrl(id, skinUrl);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 问卷状态修改
     *
     * @param id
     * @return
     */
    @PutMapping(path = "/access/{id}")
    public ResponseEntity updateAccess(@PathVariable("id") String id) {
        questionnaireService.updateAccessRate(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 问卷状态修改
     *
     * @param id
     * @return
     */
    @PutMapping(path = "/recovery/{id}")
    public ResponseEntity updateRecoveryRate(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        questionnaireService.updateRecoveryRate(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 问卷回收率
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/recovery/{id}")
    public ResponseEntity recoveryRate(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        QuestionnaireBO questionnaireBO = questionnaireService.selectAccessNum(id);
//        Integer recoveryRate = questionnaireBO.getRecoveryRate();
//        Integer accessRate = questionnaireBO.getAccessRate();
//        String rate = accuracy(recoveryRate,accessRate,3);
        return ResponseEntity.ok(Utils.kv("data", questionnaireBO));
    }

    /*public static String accuracy(double recoveryRate, double accessRate, int scale){
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = recoveryRate / accessRate * 100;
        return df.format(accuracy_num)+"%";
    }*/

    /**
     * 问卷删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {
        QuestionnaireBO questionnaireBO = new QuestionnaireBO();
        questionnaireBO.setId(id);
        questionnaireService.delete(questionnaireBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 问卷拷贝
     *
     * @return
     */
    @PostMapping(path = "/copy")
    public ResponseEntity copy(@Valid @RequestBody QuestionnaireBO questionnaireBO) {
        LOGGER.info("{}", questionnaireBO);
        QuestionnaireBO bo = questionnaireService.copy(questionnaireBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 问卷答题统计
     *
     * @return
     */
    @GetMapping(path = "/selectdttj")
    public ResponseEntity<?> selectdttj(@RequestParam(value = "startTime", required = false) String startTime,
                                        @RequestParam(value = "endTime", required = false) String endTime,
                                        @RequestParam(value = "questionId", required = false) String questionId) {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("questionId", questionId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startTime != null && !"".equals(startTime)) {
                Date startTime1 = sdf.parse(startTime);
                dataMap.put("startTime", startTime1.getTime() / 1000);
            }
            if (endTime != null && !"".equals(endTime)) {
                Date startTime2 = sdf.parse(endTime);
                dataMap.put("endTime", startTime2.getTime() / 1000);
            }
        } catch (ParseException e) {
            LOGGER.error("时间类转换异常：{}", e);
            throw new RuntimeException("时间类型转换异常：{}", e);
        }
        List<SubjectsdtxxtjBo> subjectsList = subjectsService.selectListdttj(dataMap);
        LOGGER.info("{}", subjectsList);
        return ResponseEntity.ok(Utils.kv("dataList", subjectsList));
    }

}
