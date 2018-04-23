package com.abc12366.cms.web;

import com.abc12366.cms.model.questionnaire.bo.PrizeSetBO;
import com.abc12366.cms.service.PrizeSetService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 奖品控制类
 *
 * @author lizhongwei
 * @create 2017-06-17
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/prize", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class PrizeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeController.class);

    @Autowired
    private PrizeSetService prizeSetService;

    /**
     * 查询奖品详情
     *
     * @param questionId
     * @return
     */
    @GetMapping(path = "/{questionId}")
    public ResponseEntity<?> selectOne(@PathVariable String questionId) {
        LOGGER.info("{}", questionId);
        PrizeSetBO prizeSetBO = prizeSetService.selectOne(questionId);
        LOGGER.info("{}", prizeSetBO);
        return ResponseEntity.ok(Utils.kv("data", prizeSetBO));
    }

    /**
     * 奖品新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity submitPrize(@Valid @RequestBody PrizeSetBO prizeSetBO) {
        LOGGER.info("{}", prizeSetBO);
        PrizeSetBO bo = prizeSetService.insert(prizeSetBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 奖品修改
     *
     * @param prizeSetBO
     * @param questionId
     * @return
     */
    @PutMapping(path = "/{questionId}")
    public ResponseEntity update(@Valid @RequestBody PrizeSetBO prizeSetBO, @PathVariable("questionId") String
            questionId) {
        LOGGER.info("{}", prizeSetBO);
        prizeSetBO.setQuestionId(questionId);
        PrizeSetBO bo = prizeSetService.update(prizeSetBO);
        LOGGER.info("{}", bo);
        return new ResponseEntity<>(bo, HttpStatus.OK);
    }

    /**
     * 奖品删除
     *
     * @param questionId
     * @return
     */
    @DeleteMapping(path = "/{questionId}")
    public ResponseEntity update(@PathVariable("questionId") String questionId) {
        PrizeSetBO prizeSetBO = new PrizeSetBO();
        prizeSetBO.setQuestionId(questionId);
        prizeSetService.delete(prizeSetBO);
        return ResponseEntity.ok(Utils.kv());
    }

}
