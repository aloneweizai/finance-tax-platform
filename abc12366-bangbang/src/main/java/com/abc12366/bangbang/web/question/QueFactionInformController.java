package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionFactionInformBo;
import com.abc12366.bangbang.service.QueFactionInformService;
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
 * 邦派通知管理模块
 *
 * @author xieyanmao
 * @create 2017-10-24
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queFacInform", headers = Constant.VERSION_HEAD + "=1")
public class QueFactionInformController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionInformController.class);

    @Autowired
    private QueFactionInformService queFactionInformService;

    /**
     * 邦派通知列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "factionId", required = false) String factionId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionInformBo> dataList = queFactionInformService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派通知新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionFactionInformBo factionInformBo) {
        //新增邦派通知信息
        factionInformBo = queFactionInformService.save(factionInformBo);
        return ResponseEntity.ok(Utils.kv("data", factionInformBo));
    }

    /**
     * 查询单个邦派通知信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询单个邦派通知信息
        QuestionFactionInformBo factionInformBo = queFactionInformService.selectFactionInform(id);
        return ResponseEntity.ok(Utils.kv("data", factionInformBo));
    }

    /**
     * 更新邦派通知信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody QuestionFactionInformBo factionInformBo) {
        //更新邦派通知信息
        factionInformBo = queFactionInformService.update(factionInformBo);
        return ResponseEntity.ok(Utils.kv("data", factionInformBo));
    }

    /**
     * 删除邦派通知信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除邦派通知信息
        String rtn = queFactionInformService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
