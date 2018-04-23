package com.abc12366.uc.web.gift;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.gift.Gift;
import com.abc12366.uc.model.gift.UamountLog;
import com.abc12366.uc.model.gift.UgiftLog;
import com.abc12366.uc.model.gift.bo.GiftBO;
import com.abc12366.uc.model.gift.bo.GiftCheckBO;
import com.abc12366.uc.model.gift.bo.GiftSendBO;
import com.abc12366.uc.model.gift.bo.UgiftApplyBO;
import com.abc12366.uc.service.gift.GiftService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 礼物控制器
 *
 * @author lizhongwei
 * @date 2017-12-18 2:51 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/gift", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class GiftController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftController.class);

    /**
     * 会员礼包服务
     */
    @Autowired
    private GiftService giftService;

    /**
     * 后台-礼物列表查询
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param name     礼物名称
     * @param status   状态
     * @return ResponseEntity {@linkplain Gift Gift}列表响应实体
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "status", required = false) String status) {
        Gift gift = new Gift();
        gift.setName(name);
        gift.setStatus(status);
        gift.setCategory(category);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<Gift> dataList = giftService.selectAdminList(gift);
        PageInfo<Gift> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 前台-礼物列表查询
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param name     礼物名称
     * @param status   状态
     * @return ResponseEntity {@linkplain Gift Gift}列表响应实体
     */
    @GetMapping(path = "/show")
    public ResponseEntity selectUserGiftList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                     pageSize,
                                             @RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "category", required = false) String category,
                                             @RequestParam(value = "status", required = false) String status) {
        Gift gift = new Gift();
        gift.setName(name);
        gift.setStatus(status);
        gift.setCategory(category);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<Gift> dataList = giftService.selectList(gift);
        PageInfo<Gift> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 查看礼物详情
     *
     * @param id 主键ID
     * @return ResponseEntity {@linkplain Gift Gift}响应实体
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable("id") String id) {
        Gift gift = giftService.selectById(id);
        LOGGER.info("{}", gift);
        return ResponseEntity.ok(Utils.kv("data", gift));
    }

    /**
     * 礼物删除
     *
     * @param id 礼物ID
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        giftService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 礼物修改
     *
     * @param gift {@linkplain Gift GiftUpdateBO}
     * @param id   礼物ID
     * @return ResponseEntity {@linkplain Gift Gift}响应实体
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Gift gift, @PathVariable("id") String id) {
        LOGGER.info("{}", gift, id);
        gift.setId(id);
        GiftBO giftBO = giftService.update(gift);
        LOGGER.info("{}", giftBO);
        return ResponseEntity.ok(Utils.kv("data", giftBO));
    }

    /**
     * 礼物新增
     *
     * @param bo {@linkplain GiftBO GiftBO}
     * @return ResponseEntity GiftBO实体
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody Gift bo) {
        LOGGER.info("{}", bo);
        Gift gift = giftService.insert(bo);
        LOGGER.info("{}", gift);
        return ResponseEntity.ok(Utils.kv("data", gift));
    }

    /**
     * 前台-用户的礼物申请列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param userId   用户ID
     * @return ResponseEntity {@linkplain Gift Gift}列表响应实体
     */
    @GetMapping(path = "/apply/user/list")
    public ResponseEntity selectUserGiftListById(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                         pageNum,
                                                 @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                         pageSize,
                                                 @RequestParam(value = "userId") String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UgiftApplyBO> dataList = giftService.selectUgiftApplyList(map);
        PageInfo<UgiftApplyBO> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return (dataList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 后台-礼物申请列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param name     收件人名称
     * @param applyId 申请单号
     * @param status   状态
     * @return ResponseEntity {@linkplain Gift Gift}列表响应实体
     */
    @GetMapping(path = "/apply/list")
    public ResponseEntity selectApplyList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                          @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                          @RequestParam(value = "status", required = false) String status,
                                          @RequestParam(value = "applyId", required = false) String applyId,
                                          @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("name", name);
        map.put("applyId", applyId);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UgiftApplyBO> dataList = giftService.selectUgiftApplyList(map);
        PageInfo<UgiftApplyBO> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return (dataList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 后台-查询礼包申请详情
     * @param applyId
     * @return
     */
    @GetMapping(path = "/apply/{applyId}")
    public ResponseEntity selectApplyList(@PathVariable("applyId") String applyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("applyId", applyId);
        UgiftApplyBO data = giftService.selectUgiftApplyBO(map);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }


    /**
     * 用户兑换礼物申请
     *
     * @return ResponseEntity {@linkplain Gift Gift}响应实体
     */
    @PostMapping(path = "/apply/user/{userId}")
    public ResponseEntity buyGift(@Valid @RequestBody UgiftApplyBO ugiftApplyBO,
                                  @PathVariable("userId") String userId) {
        LOGGER.info("{},{}", userId);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("ugiftApply",ugiftApplyBO);
        giftService.buyGift(map);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 管理员审核礼物申请
     *
     * @param giftCheckBO 礼物审核BO 礼物发货BO
     * @return ResponseEntity {@linkplain Gift Gift}响应实体
     */
    @PutMapping(path = "/apply/check")
    public ResponseEntity checkGiftBuy(@Valid @RequestBody GiftCheckBO giftCheckBO, HttpServletRequest request) {
        LOGGER.info("{}", giftCheckBO);
        giftService.checkGiftBuy(giftCheckBO, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 管理员发货
     *
     * @param giftSendBO 礼物审核BO
     * @return ResponseEntity {@linkplain Gift Gift}响应实体
     */
    @PutMapping(path = "/apply/send")
    public ResponseEntity sendGift(@Valid @RequestBody GiftSendBO giftSendBO) {
        LOGGER.info("{}", giftSendBO);
        giftService.sendGift(giftSendBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 用户收货
     *
     * @param applyId 申请单ID
     * @return 成功或失败
     */
    @PutMapping("/apply/receive/{applyId}")
    public ResponseEntity receiveApply(@PathVariable("applyId") String applyId) {
        LOGGER.info("{}", applyId);
        giftService.receiveApply(applyId);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 根据ID查找用户已领取礼物详情
     *
     * @param applyId 申请单ID
     * @param userId userId
     * @return ResponseEntity {@linkplain Gift Gift}响应实体
     */
    @GetMapping(path = "/apply/user/{userId}/{applyId}")
    public ResponseEntity selectGiftByGiftId(@PathVariable("applyId") String applyId,
                                             @PathVariable("userId") String userId) {
        LOGGER.info("{},{}", applyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("applyId", applyId);
        UgiftApplyBO data = giftService.selectUgiftApplyBO(map);
        LOGGER.info("{}", data);
        return ResponseEntity.ok(Utils.kv("data", data));
    }

    /**
     * 后台-根据用户ID查找礼包金额记录
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param userId   用户ID
     * @return ResponseEntity {@linkplain Gift Gift}列表响应实体
     */
    @GetMapping(path = "/uamount/list")
    public ResponseEntity selectUamountLogList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                       pageNum,
                                               @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                       pageSize,
                                               @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UamountLog> dataList = giftService.selectUamountLogList(map);
        PageInfo<UamountLog> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 查询申请单日志
     *
     * @param applyId 申请单ID
     * @return 日志列表
     */
    @GetMapping("/applylog/{applyId}")
    public ResponseEntity selectApplyLogList(@PathVariable(value = "applyId") String applyId) {
        LOGGER.info("{}", applyId);
        List<UgiftLog> logList = giftService.selectApplyLogList(applyId);
        return ResponseEntity.ok(Utils.kv("dataList", logList));
    }
}
