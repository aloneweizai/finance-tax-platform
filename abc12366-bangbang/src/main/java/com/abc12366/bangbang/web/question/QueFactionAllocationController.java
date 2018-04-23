package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationManageBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationsBo;
import com.abc12366.bangbang.service.QueFactionAllocationService;
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
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邦派奖励分配管理模块
 *
 * @author xieyanmao
 * @create 2017-10-24
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queFacAllocation", headers = Constant.VERSION_HEAD + "=1")
public class QueFactionAllocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionAllocationController.class);

    @Autowired
    private QueFactionAllocationService queFactionAllocationService;

    /**
     * 邦派奖励分配列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "factionId", required = false) String factionId,
                                     @RequestParam(value = "state", required = false) String state) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionId);//
        dataMap.put("state", state);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionAllocationBo> dataList = queFactionAllocationService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派奖励分配新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionFactionAllocationBo factionAllocationBo) {
        //新增邦派成员信息
        factionAllocationBo = queFactionAllocationService.save(factionAllocationBo);
        return ResponseEntity.ok(Utils.kv("data", factionAllocationBo));
    }

    /**
     * 查询单个邦派奖励分配信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        //查询单个邦派奖励分配信息
        QuestionFactionAllocationBo factionAllocationBo = queFactionAllocationService.selectFactionAllocation(id);
        return ResponseEntity.ok(Utils.kv("data", factionAllocationBo));
    }

    /**
     * 更新邦派奖励分配信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id,
                                 @Valid @RequestBody QuestionFactionAllocationBo factionAllocationBo) {
        //更新邦派奖励分配信息
        factionAllocationBo = queFactionAllocationService.update(factionAllocationBo);
        return ResponseEntity.ok(Utils.kv("data", factionAllocationBo));
    }

    /**
     * 删除邦派奖励分配信息
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        //删除邦派奖励分配信息
        String rtn = queFactionAllocationService.delete(id);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 我管理的邦派成员奖励分配
     */
    @GetMapping(path = "/allocationList")
    public ResponseEntity selectAllocationList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                           @RequestParam(value = "factionId", required = false) String factionId,
                                           @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionId);
        dataMap.put("status", status);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionAllocationsBo> dataList = queFactionAllocationService.selectAllocationList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }


    /**
     * 运营管理系统邦派成员奖励分配
     */
    @GetMapping(path = "/manageList")
    public ResponseEntity selectManageList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                        @RequestParam(value = "factionName", required = false) String factionName,
                                        @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionName", factionName);
        dataMap.put("status", status);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionAllocationManageBo> dataList = queFactionAllocationService.selectManageList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /*
    * 分配审核 接口
    */
    @PutMapping(path = "/audit")
    public ResponseEntity audit(@RequestBody List<QuestionFactionAllocationManageBo> records, HttpServletRequest request) {
        queFactionAllocationService.audit(records, request);
        return ResponseEntity.ok(Utils.kv());
    }


}
