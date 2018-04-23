package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.AnswerLogtjListBo;
import com.abc12366.cms.model.questionnaire.bo.AnswerLogBO;
import com.abc12366.cms.model.questionnaire.bo.AnswertjBO;
import com.abc12366.cms.service.AnswerLogService;
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
import java.util.*;

/**
 * 答题记录控制类
 *
 * @author lizhongwei
 * @create 2017-06-14
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/answer", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AnswerLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerLogController.class);

    @Autowired
    private AnswerLogService answerLogService;

    /**
     * 答题记录列表查询
     *
     * @param questionId
     * @return
     */
    @GetMapping(path = "/list/{questionId}")
    public ResponseEntity selectList(@PathVariable("questionId") String questionId,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                     @RequestParam(value = "endDate", defaultValue = "") String endDate) {
        AnswerLogBO answerLog = new AnswerLogBO();
        answerLog.setQuestionId(questionId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startDate != null && !"".equals(startDate)) {
                Date startTime = sdf.parse(startDate);
                answerLog.setStartDate(startTime);
            }
            if (endDate != null && !"".equals(endDate)) {
                Date endTime = sdf.parse(endDate);
                answerLog.setEndDate(endTime);
            }
        } catch (ParseException e) {
            LOGGER.error("时间类转换异常：{}", e);
            throw new RuntimeException("时间类型转换异常：{}", e);
        }
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);

        List<AnswerLogBO> dataList = answerLogService.selectList(answerLog);
        LOGGER.info("{}", dataList);
        return (dataList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 答题记录平均答题查询
     *
     * @param questionId
     * @return
     */
    @GetMapping(path = "/avg/{questionId}")
    public ResponseEntity answerAvg(@PathVariable("questionId") String questionId) {
        AnswerLogBO answerLog = new AnswerLogBO();
        answerLog.setQuestionId(questionId);
        AnswerLogBO answerLogBO = answerLogService.answerAvg(answerLog);
        LOGGER.info("{}", answerLogBO);
        return (answerLogBO == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("data", answerLogBO));
    }


    /**
     * 查询答题记录详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        AnswerLogBO answerLogBO = answerLogService.selectOne(id);
        LOGGER.info("{}", answerLogBO);
        return ResponseEntity.ok(Utils.kv("data", answerLogBO));
    }

    /**
     * 答题记录新增
     *
     * @return
     */
    @PostMapping(path = "/insert")
    public ResponseEntity submitAnswerLog(@Valid @RequestBody AnswerLogBO answerLogBO) {
        LOGGER.info("{}", answerLogBO);
        AnswerLogBO bo = answerLogService.insert(answerLogBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 答题记录批量新增
     *
     * @param answerLogBO
     * @return
     */
    @PutMapping(path = "/batch")
    public ResponseEntity batch(@Valid @RequestBody AnswerLogBO answerLogBO) {
        LOGGER.info("{}", answerLogBO);
        AnswerLogBO bo = answerLogService.batch(answerLogBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 答题记录修改
     *
     * @param answerLogBO
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}/{questionId}")
    public ResponseEntity update(@Valid @RequestBody AnswerLogBO answerLogBO, @PathVariable("id") String id) {
        LOGGER.info("{}", answerLogBO);
        answerLogBO.setId(id);
        AnswerLogBO bo = answerLogService.update(answerLogBO);
        LOGGER.info("{}", bo);
        return new ResponseEntity<>(bo, HttpStatus.OK);
    }

    /**
     * 答题记录删除
     *
     * @param questionId
     * @return
     */
    @DeleteMapping(path = "/{questionId}")
    public ResponseEntity delete(@PathVariable("questionId") String questionId, @Valid @RequestBody AnswerLogBO
            answerLogBO) {
        answerLogBO.setQuestionId(questionId);
        answerLogService.delete(answerLogBO);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/selecttj")
    public ResponseEntity selecttj(@RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "questionId", required = false) String questionId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("questionId", questionId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startTime != null && !"".equals(startTime)) {
                Date startTime1 = sdf.parse(startTime);
                dataMap.put("startTime", startTime1.getTime() / 1000);
            }else {
                dataMap.put("startTime", null);
            }
            if (endTime != null && !"".equals(endTime)) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(sdf.parse(endTime));
                calendar.add(Calendar.DATE, 1);
                dataMap.put("endTime", calendar.getTime().getTime() / 1000);
//                Date startTime2 = sdf.parse(endTime);
//                dataMap.put("endTime", startTime2.getTime() / 1000);
            }else {
                dataMap.put("endTime", null);
            }
        } catch (ParseException e) {
            LOGGER.error("时间类转换异常：{}", e);
            throw new RuntimeException("时间类型转换异常：{}", e);
        }
        AnswerLogtjListBo data = answerLogService.selecttj(dataMap);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }


    /**
     * 答题记录列表查询
     *
     * @param subjectsId
     * @return
     */
    @GetMapping(path = "/answerList/{subjectsId}")
    public ResponseEntity selectList(@PathVariable("subjectsId") String subjectsId) {
        List<AnswertjBO> dataList = answerLogService.selectListBysubjectsId(subjectsId);
        LOGGER.info("{}", dataList);
        return (dataList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    @GetMapping(path = "/selectdtcnt")
    public ResponseEntity selectdtcnt(@RequestParam(value = "userId", required = false) String userId,
                                      @RequestParam(value = "weixinId", required = false) String weixinId,
                                   @RequestParam(value = "questionId", required = false) String questionId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("questionId", questionId);
        dataMap.put("userId", userId);
        dataMap.put("weixinId", weixinId);
        int data = answerLogService.selectdtcnt(dataMap);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }

}
