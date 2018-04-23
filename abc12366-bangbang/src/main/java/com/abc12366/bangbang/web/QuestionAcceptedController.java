package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.question.QuestionAccepted;
import com.abc12366.bangbang.model.question.bo.QuestionAcceptedBO;
import com.abc12366.bangbang.service.QuestionAcceptedService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author lizhongwei
 * @Date 2017/9/21 13:39
 * 问题受理控制类
 */
@RestController
@RequestMapping(path = "/accepted", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionAcceptedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionAcceptedController.class);


    @Autowired
    private QuestionAcceptedService questionAcceptedService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 问题受理 分页查询-后台
     *
     * @param page   页数
     * @param size   条数
     * @param userId 用户ID
     * @param nsrsbh 纳税人识别号
     * @param name   名字
     * @param date   时间
     * @return
     */
    @GetMapping(path = "/admin/list")
    public ResponseEntity selectAdminList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                          @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                          @RequestParam(value = "userId", required = false) String userId,
                                          @RequestParam(value = "nsrsbh", required = false) String nsrsbh,
                                          @RequestParam(value = "phone", required = false) String phone,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "date", required = false) String date) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        QuestionAcceptedBO param = new QuestionAcceptedBO();
        if (date != null && !"".equals(date)) {
            param.setDate(date);
        } else {
            param.setDate(DateUtils.dateYearToString(new Date()));
        }
        param.setNsrsbh(nsrsbh);
        param.setName(name);
        param.setUserId(userId);
        param.setPhone(phone);
        List<QuestionAccepted> list = questionAcceptedService.selectAdminList(param);

        PageInfo<QuestionAccepted> pageInfo = new PageInfo<>(list);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 问题受理 分页查询
     * * @param page  页数
     *
     * @param size   条数
     * @param userId 用户ID
     * @param nsrsbh 纳税人识别号
     * @param name   名字
     * @param date   时间
     * @return
     */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId", required = false) String userId,
                                     @RequestParam(value = "nsrsbh", required = false) String nsrsbh,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "date", required = false) String date) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        QuestionAcceptedBO param = new QuestionAcceptedBO();
        if (date != null && !"".equals(date)) {
            param.setDate(date);
        } else {
            param.setDate(DateUtils.dateYearToString(new Date()));
        }
        param.setNsrsbh(nsrsbh);
        param.setName(name);
        param.setUserId(userId);
        List<QuestionAccepted> list = questionAcceptedService.selectList(param);

        PageInfo<QuestionAccepted> pageInfo = new PageInfo<>(list);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 问题受理 统计查询
     *
     * @param page   页数
     * @param size   条数
     * @param userId 用户ID
     * @param date   时间
     * @return
     */
    @GetMapping(path = "/statis")
    public ResponseEntity selectStatisList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                           @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                           @RequestParam(value = "userId", required = false) String userId,
                                           @RequestParam(value = "date", required = false) String date) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        QuestionAcceptedBO param = new QuestionAcceptedBO();
        if (date != null && !"".equals(date)) {
            param.setDate(date);
        }
        param.setUserId(userId);
        List<QuestionAcceptedBO> list = questionAcceptedService.selectStatisList(param);

        PageInfo<QuestionAcceptedBO> pageInfo = new PageInfo<>(list);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 问题受理 统计查询(缓存)
     *
     * @param userId 用户ID
     * @param date   时间
     * @return
     */
    @GetMapping(path = "/statiss")
    public ResponseEntity selectStatisLists(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "date", required = false) String date) {
        if (redisTemplate.hasKey("Bangb_Accepted")) {
            List<QuestionAcceptedBO> list = JSONArray.parseArray(redisTemplate.opsForValue().get("Bangb_Accepted"),
                    QuestionAcceptedBO.class);
            LOGGER.info("从Redis获取数据:" + JSONArray.toJSONString(list));
            return (list == null) ?
                    ResponseEntity.ok(Utils.kv()) :
                    ResponseEntity.ok(Utils.kv("dataList", list, "total", list.size()));
        } else {
            PageHelper.startPage(0, 0, true).pageSizeZero(true).reasonable(true);
            QuestionAcceptedBO param = new QuestionAcceptedBO();
            if (date != null && !"".equals(date)) {
                param.setDate(date);
            }
            param.setUserId(userId);
            List<QuestionAcceptedBO> list = questionAcceptedService.selectStatisList(param);
            redisTemplate.opsForValue().set("Bangb_Accepted", JSONArray.toJSONString(list), RedisConstant
                    .DAY_1, TimeUnit.DAYS);

            PageInfo<QuestionAcceptedBO> pageInfo = new PageInfo<>(list);

            return (list == null) ?
                    ResponseEntity.ok(Utils.kv()) :
                    ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
        }

    }

    /***
     * 添加问题受理接口
     */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionAccepted QuestionAccepted) {
        questionAcceptedService.add(QuestionAccepted);
        redisTemplate.delete("Bangb_Accepted");
        return ResponseEntity.ok(Utils.kv("data", QuestionAccepted));
    }

    /**
     * 删除问题受理 接口
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        questionAcceptedService.delete(id);
        redisTemplate.delete("Bangb_Accepted");
        return ResponseEntity.ok(Utils.kv());
    }

}
