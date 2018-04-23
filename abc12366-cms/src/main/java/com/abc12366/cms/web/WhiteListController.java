package com.abc12366.cms.web;

import com.abc12366.cms.model.questionnaire.WhiteList;
import com.abc12366.cms.model.questionnaire.bo.WhiteListBO;
import com.abc12366.cms.service.WhiteListService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 白名单控制类
 *
 * @author lizhongwei
 * @create 2017-06-14
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/whiteList", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class WhiteListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhiteListController.class);

    @Autowired
    private WhiteListService whiteListService;

    /**
     * 白名单列表查询
     *
     * @param questionId
     * @return
     */
    @GetMapping(path = "/{questionId}")
    public ResponseEntity selectList(@PathVariable("questionId") String questionId) {
        WhiteList whiteList = new WhiteList();
        whiteList.setQuestionId(questionId);
        List<WhiteList> whiteListList = whiteListService.selectList(whiteList);
        LOGGER.info("{}", whiteListList);
        return (whiteListList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", whiteListList));
    }


    /**
     * 白名单新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity submitWhiteList(@Valid @RequestBody WhiteListBO whiteListBO) {
        LOGGER.info("{}", whiteListBO);
        WhiteListBO bo = whiteListService.insert(whiteListBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 白名单删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {
        WhiteListBO whiteListBO = new WhiteListBO();
        whiteListBO.setId(id);
        whiteListService.delete(whiteListBO);
        return ResponseEntity.ok(Utils.kv());
    }

}
