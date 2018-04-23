package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.ExperienceLevelService;
import com.abc12366.uc.service.UserService;
import com.abc12366.uc.service.UserStatisService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User用户统计
 *
 * @author lizhongwei
 * @create 2017-11-21 3:18 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user/statis", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserStatisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatisController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ExperienceLevelService experienceLevelService;

    @Autowired
    private UserStatisService userStatisService;

    /**
     * 统计用户，统计维度为【月份】
     *
     * @param startTime  开始时间
     * @param endTime  结束时间
     * @return ResponseEntity
     */
    @GetMapping(path = "/month")
    public ResponseEntity statisUser(@RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime) {
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }

        List<UserStatisBO> list = userService.statisUserByMonth(map);
        PageInfo<UserStatisBO> pageInfo = new PageInfo<>(list);
//        LOGGER.info("{}", list);
        return (list == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户活跃度统计(概况)接口
     * @return ResponseEntity
     */
    @GetMapping(path = "/liveness")
    public ResponseEntity userLiveness(){
        LOGGER.info("查询用户活跃度概况统计");
        UserLivenessSurveyBO userLivenessSurveyBO = userService.userLivenessSurvey();
//        LOGGER.info("查询用户活跃度概况统计结果返回：{}", userLivenessSurveyBO);
        return ResponseEntity.ok(Utils.kv("dataList",userLivenessSurveyBO));
    }

    /**
     * 用户活跃度统计（详情）接口
     * @param type 时间类型
     * @param start 开始时间
     * @param end 结束时间
     * @return ResponseEntity
     */
    @GetMapping(path = "/liveness/detail")
    public ResponseEntity userLivenessDetail(@RequestParam String type,@RequestParam String start,@RequestParam String end){
        LOGGER.info("查询用户活跃度详情统计：{}:{}:{}", type,start,end);
        Object object = userService.userLivenessDetail(type, start, end);
//        LOGGER.info("查询用户活跃度详情统计结果返回：{}", object);
        return ResponseEntity.ok(Utils.kv("dataList",object));
    }

    /**
     * 用户活跃度统计详情中包含的用户信息接口
     * @param timeInterval 时间区间
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/liveness/detail/uinfo")
    public ResponseEntity userLivenessDetailUinfo(@RequestParam String timeInterval,
                                                  @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                  @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("查询用户活跃度详情包含的用户信息：{}:{}:{}", timeInterval,page,size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<UserListBO> dataList = userService.userLivenessDetailUinfo(timeInterval, page, size);
//        LOGGER.info("查询用户活跃度详情统计包含的用户信息结果返回：{}", dataList);
        PageInfo<UserListBO> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户经验值等级统计
     * @param year 年份
     * @return ResponseEntity
     */
    @GetMapping(path = "/explevel")
    public ResponseEntity userExpLevel(@RequestParam String year,
                                        @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("查询用户经验值等级统计：{}", year);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ExpLevelStatistic> expLevelStatisticList = userService.userExpLevel(year,page,size);
//        LOGGER.info("查询用户经验值等级统计结果返回：{}", expLevelStatisticList);
        PageInfo<ExpLevelStatistic> pageInfo = new PageInfo<>(expLevelStatisticList);
//        List<ExperienceLevelBO> experienceLevelBOList = experienceLevelService.selectList(null);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户会员等级统计
     * @param year 年份
     * @return ResponseEntity
     */
    @GetMapping(path = "/viplevel")
    public ResponseEntity userVipLevel(@RequestParam String year){
        LOGGER.info("查询会员等级统计：{}", year);
        List<VipLevelStatistic> vipLevelStatisticList = userService.userVip(year);
        LOGGER.info("查询会员等级统计结果返回：{}", vipLevelStatisticList);
        return ResponseEntity.ok(Utils.kv("dataList",vipLevelStatisticList));
    }

    /**
     * 查询用户会员统计新增会员的用户详情
     * @param year 年份
     * @param vipCode 会员编码
     * @return ResponseEntity
     */
    @GetMapping(path = "/viplevel/increase/uinfo")
    public ResponseEntity userVipIncreaseInfo(@RequestParam String year,
                                         @RequestParam String vipCode,
                                         @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                        @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("查询会员等级统计新增用户详情：{}：{}", year,vipCode);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<User> userList = userStatisService.userVipIncreaseInfo(year, vipCode);
//        LOGGER.info("查询会员等级统计新增用户详情：{}", userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 查询会员等级统计该等级全部用户详情
     * @param vipCode 会员编码
     * @return ResponseEntity
     */
    @GetMapping(path = "/viplevel/all/uinfo")
    public ResponseEntity userVipAllInfo(@RequestParam String vipCode,
                                         @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("查询会员等级统计该等级全部用户详情：{}", vipCode);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<User> userList = userStatisService.userVipAllInfo(vipCode);
//        LOGGER.info("查询会员等级统计该等级全部用户详情：{}", userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户流失率统计
     * @param yearTime 时间（年度）
     * @param months 流失间隔周期（1个月、2个月、3个月…12个月）
     * @return ResponseEntity
     */
    @GetMapping(path = "/loss")
    public ResponseEntity statisUserLossRate(@RequestParam(value = "yearTime", required = false) String yearTime,
                                         @RequestParam(value = "months", required = false) Integer months) {
        Map<String,Object> map = new HashMap<>();
        if (yearTime != null && !"".equals(yearTime)) {
            map.put("yearTime", DateUtils.strToDateMonth(yearTime));
        }
        if (months != null) {
            map.put("months", months);
            map.put("startTime", DateUtils.getAddMonth(DateUtils.strToDateMonth(yearTime),months-1));
            map.put("endTime", DateUtils.strToDateMonth(yearTime));
        }

        UserLossRateBO data = userService.statisUserLossRate(map);
//        PageInfo<UserLossRateBO> pageInfo = new PageInfo<>(list);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data",data));
    }

    /**
     * 用户留存率统计
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/reta")
    public ResponseEntity statisUserRetainedRate(@RequestParam(value = "startTime", required = true) String startTime,
                                                 @RequestParam(value = "endTime", required = true) String endTime) {
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", endTime);
        }
        List<UserRetainedRateListBO> list = userService.statisUserRetainedRate(map);
        PageInfo<UserRetainedRateListBO> pageInfo = new PageInfo<>(list);
//        LOGGER.info("{}", list);
        return (list == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户消费能力分析，用户列表
     * R表示最近一次消费间隔统计时间段截止日期的天数，F表示统计时间段内消费次数，M表示统计时间段内容消费总金额。
     * @param startDay R开始天数
     * @param endDay   R结束天数
     * @param startCount F开始次数
     * @param endCount F结束次数
     * @param startPrice M开始总金额
     * @param endPrice M结束总金额
     * @return
     */
    @GetMapping(path = "/consume")
    public ResponseEntity statisUserConsumeLevel(@RequestParam(value = "startDay", required = true) double startDay,
                                                 @RequestParam(value = "endDay", required = true) double endDay,
                                                 @RequestParam(value = "startCount", required = true) double startCount,
                                                 @RequestParam(value = "endCount", required = true) double endCount,
                                                 @RequestParam(value = "startPrice", required = true) double startPrice,
                                                 @RequestParam(value = "endPrice", required = true) double endPrice,
                                                 @RequestParam(value = "startTime", required = true) String startTime,
                                                 @RequestParam(value = "endTime", required = true) String endTime,
                                                 @RequestParam(value = "tradeMethod", required = true) String tradeMethod,
                                                 @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                                 @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize
                                                 ) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        Map<String,Object> map = new HashMap<>();
        map.put("startDay",startDay);
        map.put("endDay",endDay);
        map.put("startCount",startCount);
        map.put("endCount",endCount);
        map.put("startPrice",startPrice);
        map.put("endPrice",endPrice);
        map.put("tradeMethod", tradeMethod);
        map.put("orderStatus", "6");
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        List<UserExprotInfoBO> data = userService.statisUserConsumeLevel(map);
        PageInfo<UserExprotInfoBO> pageInfo = new PageInfo<>(data);
//        LOGGER.info("list{}", data);
        return (data == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户消费能力分析
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/consume/rfm")
    public ResponseEntity statisUserConsumeRFM(@RequestParam(value = "startTime", required = true) String startTime,
                                                 @RequestParam(value = "endTime", required = true) String endTime,
                                               @RequestParam(value = "tradeMethod", required = true) String tradeMethod) {
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        map.put("tradeMethod", tradeMethod);
        map.put("orderStatus", "6");
        UserRFMBO data = userService.statisUserRFM(map);
//        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data",data));
    }

    /**
     * 用户标签分析统计接口
     * @param type 时间类型 year，month，day
     * @param start 开始时间
     * @param end 结束时间
     * @param tagName 标签名
     * @return ResponseEntity
     */
    @GetMapping(path = "/tag")
    public ResponseEntity userTag(@RequestParam String type,
                                  @RequestParam String start,
                                  @RequestParam String end,
                                  @RequestParam String tagName){
        LOGGER.info("查询用户标签统计情况：{}：{}：{}：{}", type, start, end, tagName);
        List<TagUserStaticBO> tagUserStaticBOList = userStatisService.tag(type, start, end, tagName);
        return ResponseEntity.ok(Utils.kv("dataList",tagUserStaticBOList));
    }

    /**
     * 用户标签分析统计用户详情接口
     * @param type 时间类型 year，month，day
     * @param start 开始时间
     * @param end 结束时间
     * @param tagName 标签名
     * @param page 页码
     * @param size 每页数据数量
     * @return ResponseEntity
     */
    @GetMapping(path = "/tag/uinfo")
    public ResponseEntity userTagUinfo(@RequestParam String type,
                                        @RequestParam String start,
                                        @RequestParam String end,
                                        @RequestParam String tagName,
                                       @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                       @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("查询用户标签统计用户详情：{}：{}：{}：{}:{}:{}", type, start, end, tagName,page,size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<User> tagUserStaticBOList = userStatisService.userTagUinfo(type, start, end, tagName);
//        LOGGER.info("查询用户标签统计用户详情返回：{}", tagUserStaticBOList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) tagUserStaticBOList, "total", ((Page) tagUserStaticBOList).getTotal()));
    }

    /**
     * 用户区域统计接口
     * @param type 区域类型，country、province
     * @param start 开始时间
     * @param end 结束时间
     * @param province 省名
     * @return ResponseEntity
     */
    @GetMapping(path = "/region")
    public ResponseEntity region(@RequestParam String type,
                                  @RequestParam String start,
                                  @RequestParam String end,
                                  @RequestParam(required = false) String province,
                                  @RequestParam(required = false) String tagName
                                 ){
        LOGGER.info("用户区域统计：{}：{}：{}:{}:{}", type, start, end, province,tagName);
        List<RigionStatisBO> rigionStatisBOList = userStatisService.region(type, start, end, province,tagName);
//        LOGGER.info("用户区域统计返回：{}", rigionStatisBOList);
        return ResponseEntity.ok(Utils.kv("dataList",rigionStatisBOList));
    }

    /**
     * 用户区域统计查询用户详情接口
     * @param type 区域类型，country、province
     * @param tagName 标签名
     * @param timeInterval 时间区间
     * @param province 省名
     * @return ResponseEntity
     */
    @GetMapping(path = "/region/uinfo")
    public ResponseEntity regionUinfo(@RequestParam String type,
                                      @RequestParam(required = false) String tagName,
                                        @RequestParam(required = false) String timeInterval,
                                        @RequestParam String province,
                                      @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                      @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("用户区域统计查询用户详情：{}：{}：{}:{}", type, tagName,timeInterval, province);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<User> userList = userStatisService.regionUinfo(type, timeInterval, province,tagName);
//        LOGGER.info("用户区域统计查询用户详情返回：{}", userList);
        return (userList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) userList, "total", ((Page) userList).getTotal()));
    }

    /**
     * 用户年龄分布统计
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/age")
    public ResponseEntity statisUserAge(@RequestParam(value = "startTime", required = true) String startTime,
                                        @RequestParam(value = "endTime", required = true) String endTime) {
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        List<UserAgeBO> data = userService.statisUserAge(map);
        PageInfo<UserAgeBO> pageInfo = new PageInfo<>(data);
//        LOGGER.info("list{}", data);
        return (data == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户年龄分布统计-用户列表
     * @param startTime 开始时间
     * @param endTime   结束时间
     *       startNum和endNum为null 查询未知年龄用户
     * @return
     */
    @GetMapping(path = "/age/list")
    public ResponseEntity statisUserAgeList(@RequestParam(value = "startTime", required = true) String startTime,
                                            @RequestParam(value = "endTime", required = true) String endTime,
                                            @RequestParam(value = "startNum", required = false) Integer startAge,
                                            @RequestParam(value = "endNum", required = false) Integer endAge,
                                            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        List<UserBO> data = userService.statisUserAgeList(map, startAge, endAge);
        PageInfo<UserBO> pageInfo = new PageInfo<>(data);
        LOGGER.info("list{}", data);
        return (data == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }


    /**
     * 用户性别分布统计
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/sex")
    public ResponseEntity statisUserSex(@RequestParam(value = "startTime", required = true) String startTime,
                                        @RequestParam(value = "endTime", required = true) String endTime) {
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        List<UserSexBO> data = userService.statisUserSex(map);
        PageInfo<UserSexBO> pageInfo = new PageInfo<>(data);
//        LOGGER.info("list{}", data);
        return (data == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户性别分布统计-用户列表
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/sex/list")
    public ResponseEntity statisUserSexList(@RequestParam(value = "startTime", required = true) String startTime,
                                            @RequestParam(value = "endTime", required = true) String endTime,
                                            @RequestParam(value = "sex", required = false) Integer sex,
                                            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        map.put("sex",sex);
        List<UserBO> data = userService.statisUserSexList(map);
        PageInfo<UserBO> pageInfo = new PageInfo<>(data);
//        LOGGER.info("list{}", data);
        return (data == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 用户服务企业情况统计
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/bind")
    public ResponseEntity statisUserBind(@RequestParam(value = "startTime", required = true) String startTime,
                                         @RequestParam(value = "endTime", required = true) String endTime) {
        Map<String,Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        UserBindBO data = userService.statisUserBind(map);
//        LOGGER.info("list{}", data);
        return ResponseEntity.ok(Utils.kv("data",data));
    }

    /**
     * 用户服务企业情况统计-用户列表
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @GetMapping(path = "/bind/list")
    public ResponseEntity statisUserBindList(@RequestParam(value = "startTime", required = true) String startTime,
                                             @RequestParam(value = "endTime", required = true) String endTime,
                                             @RequestParam(value = "startNum", required = true) Integer startNum,
                                             @RequestParam(value = "endNum", required = true) Integer endNum,
                                             @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> map = new HashMap<>();
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }
        map.put("startNum", startNum);
        map.put("endNum", endNum);
        List<UserBO> data = userService.statisUserBindList(map);
        PageInfo<UserBO> pageInfo = new PageInfo<>(data);
//        LOGGER.info("list{}", data);
        return (data == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 企业绑定情况统计
     * @param type 类型,all、dzsb、hngs、hnds
     * @param start 开始时间
     * @param end 结束时间
     * @return ResponseEntity
     */
    @GetMapping(path = "/bind/count")
    public ResponseEntity bindCount(@RequestParam String type,
                                     @RequestParam String start,
                                     @RequestParam String end){
        LOGGER.info("企业绑定情况统计：{}：{}：{}", type,start,end);
        Object o = userStatisService.bindCount(type, start, end);
        return ResponseEntity.ok(Utils.kv("data", o));
    }

    /**
     * 企业绑定情况统计企业详情接口
     * @param type 类型,all、dzsb、hngs、hnds
     * @param timeInterval 时间区间
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/bind/count/info")
    public ResponseEntity bindCountInfo(@RequestParam String type,
                                        @RequestParam String timeInterval,
                                        @RequestParam(required = false,defaultValue = Constant.pageNum) int page,
                                        @RequestParam(required = false,defaultValue = Constant.pageSize) int size){
        LOGGER.info("企业绑定情况统计详情：{}：{}：{}:{}", type,timeInterval,page,size);
        List<BindCountInfo> bindCountInfoList = userStatisService.bindCountInfo(type,timeInterval,page,size);
        return (bindCountInfoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) bindCountInfoList, "total", ((Page) bindCountInfoList).getTotal()));
    }

    /**
     * 企业登录情况统计
     * @param type 类型,all、dzsb、hngs、hnds
     * @param start 开始时间
     * @param end 结束时间
     * @return ResponseEntity
     */
    @GetMapping(path = "/bind/login")
    public ResponseEntity bindLogin(@RequestParam String type,
                                    @RequestParam String start,
                                    @RequestParam String end){
        LOGGER.info("企业登录情况统计：{}：{}：{}", type,start,end);
        Object o = userStatisService.bindLogin(type, start, end);
        LOGGER.info("企业登录情况统计返回：{}");
        return ResponseEntity.ok(Utils.kv("data", o));
    }

    /**
     * 企业登录统计企业详情接口
     * @param type 类型,all、dzsb、hngs、hnds
     * @param timeInterval 时间区间
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/bind/login/info")
    public ResponseEntity bindLoginInfo(@RequestParam String type,
                                        @RequestParam String timeInterval,
                                        @RequestParam(required = false,defaultValue = Constant.pageNum) int page,
                                        @RequestParam(required = false,defaultValue = Constant.pageSize) int size){
        LOGGER.info("企业绑定情况统计详情：{}：{}：{}:{}", type,timeInterval,page,size);
        List<BindCountInfo> bindCountInfoList = userStatisService.bindLoginInfo(type, timeInterval, page, size);
        return (bindCountInfoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) bindCountInfoList, "total", ((Page) bindCountInfoList).getTotal()));
    }

    /**
     * 用户积分收支分析接口
     * @param start 开始时间
     * @param end 结束时间
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/point/analysis")
    public ResponseEntity pointAnalysis(@RequestParam String start,
                                        @RequestParam String end,
                                        @RequestParam(required = false,defaultValue = Constant.pageNum) int page,
                                        @RequestParam(required = false,defaultValue = Constant.pageSize) int size){
        LOGGER.info("用户积分收支分析：{}：{}：{}:{}",start,end,page,size);
        PointAnalysisBO pointAnalysisBO = userStatisService.pointAnalysis(start,end,page,size);
        return ResponseEntity.ok(Utils.kv("data",pointAnalysisBO));
    }

    /**
     * 用户积分收支分析规则日志详情接口
     * @param ruleId 规则id
     * @param timeInterval 时间区间
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/point/analysis/ruleinfo")
    public ResponseEntity pointAnalysisRuleinfo(@RequestParam String ruleId,
                                        @RequestParam String timeInterval,
                                        @RequestParam(required = false,defaultValue = Constant.pageNum) int page,
                                        @RequestParam(required = false,defaultValue = Constant.pageSize) int size){
        LOGGER.info("用户积分收支分析：{}：{}：{}:{}",ruleId,timeInterval,page,size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<PointRuleinfoBO> pointRuleinfoBOList = userStatisService.pointAnalysisRuleinfo(ruleId, timeInterval,page,size);
        return (pointRuleinfoBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) pointRuleinfoBOList, "total", ((Page) pointRuleinfoBOList).getTotal()));
    }
}
