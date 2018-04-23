package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.UserBsrl;
import com.abc12366.uc.model.bo.UserBsrlBO;
import com.abc12366.uc.service.UserBsrlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户办理日历
 *
 * @author lizhongwei
 * @create 2018-01-02 3:18 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user/bsrl", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserBsrlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBsrlController.class);

    @Autowired
    private UserBsrlService userBsrlService;

    /**
     *  列表查询
     * @param startTime  开始时间
     * @param endTime  结束时间
     * @param page  页数
     * @param size  条数
     * @param sbyf  申报月份
     * @return
     */
    @GetMapping
    public ResponseEntity statisUser(@RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "sbyf", required = false) String sbyf,
                                     @RequestParam(value = "sbnf", required = false) String sbnf){
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        map.put("sbyf", sbyf);
        map.put("sbnf", sbnf);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<UserBsrl> list = userBsrlService.selectList(map);
        PageInfo<UserBsrl> pageInfo = new PageInfo<>(list);
        LOGGER.info("{}", list);
        return (list == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 单个查询
     * @param id id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable("id") String id) {
        UserBsrlBO data = userBsrlService.selectById(id);
        LOGGER.info("data{}", data);
        return ResponseEntity.ok(Utils.kv("data",data));
    }

    /**
     * 新增办理日历
     * @param userBsrlBO 办理日历对象
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody UserBsrlBO userBsrlBO) {
        LOGGER.info("{}", userBsrlBO);
        UserBsrlBO bo = userBsrlService.insert(userBsrlBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 修改办理日历
     *
     * @param userBsrlBO 办理日历对象
     */
    @PutMapping
    public ResponseEntity update(@Valid @RequestBody UserBsrlBO userBsrlBO) {
        LOGGER.info("{}", userBsrlBO);
        UserBsrlBO bo = userBsrlService.update(userBsrlBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 删除办理日历
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        userBsrlService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }
}
