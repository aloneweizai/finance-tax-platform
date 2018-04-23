package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.ReturnVisit;
import com.abc12366.bangbang.model.bo.ReturnVisitBO;
import com.abc12366.bangbang.service.ReturnVisitService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author lizhongwei
 * @Date 2017/9/21 13:39
 */
@RestController
@RequestMapping(path = "/visit", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ReturnVisitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnVisitController.class);


    @Autowired
    private ReturnVisitService returnVisitService;


    /**
    *  回访记录 分页查询
    */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "date", required = true) String date) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);

        ReturnVisitBO param = new ReturnVisitBO();
        if(date != null && !"".equals(date)) {
            param.setDate(date);
        }else{
            param.setDate(DateUtils.dateYearToString(new Date()));
        }
        param.setPhone(phone);
        param.setUserId(userId);
        List<ReturnVisit> list = returnVisitService.selectList(param);

        PageInfo<ReturnVisit> pageInfo = new PageInfo<>(list);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }


    /**
     *  回访记录 统计查询
     */
    @GetMapping(path = "/statis")
    public ResponseEntity selectStatisList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                           @RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "userId", required = false) String userId,
                                           @RequestParam(value = "date", required = true) String date) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        ReturnVisitBO param = new ReturnVisitBO();
        if(date != null && !"".equals(date)) {
            param.setDate(date);
        }
        param.setPhone(phone);
        param.setUserId(userId);
        List<ReturnVisitBO> list = returnVisitService.selectStatisList(param);

        PageInfo<ReturnVisitBO> pageInfo = new PageInfo<>(list);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /***
     * 添加回访记录接口
     */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody ReturnVisit ReturnVisit) {
        returnVisitService.add(ReturnVisit);
        return ResponseEntity.ok(Utils.kv("data", ReturnVisit));
    }

    /**
    * 删除回访记录 接口
    */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        returnVisitService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

}
