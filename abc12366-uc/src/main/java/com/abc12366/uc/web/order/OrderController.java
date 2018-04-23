package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.Admin;
import com.abc12366.uc.model.bo.UserBO;
import com.abc12366.uc.model.bo.VipLogBO;
import com.abc12366.uc.model.order.Order;
import com.abc12366.uc.model.order.bo.*;
import com.abc12366.uc.service.order.OrderService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单控制类
 *
 * @author lizhongwei
 * @date 2017-05-16
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/order", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表管理
     *
     * @param pageNum     页数
     * @param pageSize    条数
     * @param orderNo     订单号
     * @param username    用户名
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param orderStatus 订单状态
     * @param phone       电话号码
     * @return 订单列表
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "orderNo", required = false) String orderNo,
                                     @RequestParam(value = "orderStatus", required = false) String orderStatus,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO orderBO = new OrderBO();
        UserBO user = new UserBO();
        user.setUsername(username);
        user.setPhone(phone);
        orderBO.setUser(user);
        orderBO.setOrderNo(orderNo);
        orderBO.setOrderStatus(orderStatus);
        if (startTime != null && !"".equals(startTime)) {
            orderBO.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            orderBO.setEndTime(DateUtils.strToDate(endTime));
        }

        List<OrderBO> orderList = orderService.selectList(orderBO, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderList);
        LOGGER.info("{}", orderList);
        return (orderList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo
                        .getTotal()));
    }

    /**
     * 已完成订单列表查询
     *
     * @param pageNum     页数
     * @param pageSize    条数
     * @param name        商品名称
     * @param tradeMethod 交易方式
     * @param status      状态
     * @param userId      用户ID
     * @param isInvoice   是否开发票
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @return 订单列表
     */
    @GetMapping(path = "/user")
    public ResponseEntity selectUserOrderList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                          pageNum,
                                              @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                      pageSize,
                                              @RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "tradeMethod", required = false) String tradeMethod,
                                              @RequestParam(value = "status", required = true) String status,
                                              @RequestParam(value = "userId", required = true) String userId,
                                              @RequestParam(value = "isReturn", required = false) Boolean isReturn,
                                              @RequestParam(value = "isInvoice", required = false) Boolean isInvoice,
                                              @RequestParam(value = "startTime", required = false) String startTime,
                                              @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO order = new OrderBO();
        UserBO user = new UserBO();
        user.setId(userId);
        order.setUser(user);

        GoodsBO goodsBO = new GoodsBO();
        goodsBO.setName(name);
        String data[] = status.split(",");
        order.setStatus(data);
        order.setTradeMethod(tradeMethod);
        order.setIsInvoice(isInvoice);
        //查询可退还列表,true：查，false：不查
        if (isReturn != null && isReturn) {
            order.setIsReturn(isReturn);
        }
        if (startTime != null && !"".equals(startTime)) {
            order.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            order.setEndTime(DateUtils.strToDate(endTime));
        }
        List<OrderBO> orderBOs = orderService.selectOrderList(order, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderBOs);
        LOGGER.info("{}", orderBOs);
        return (orderBOs == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo
                        .getTotal()));
    }

    /**
     * 用户所有订单查询，未开票的订单
     *
     * @param pageNum   页数
     * @param pageSize  条数
     * @param name      商品名称
     * @param userId    用户ID
     * @param goodsType 商品类型
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单列表
     */
    @GetMapping(path = "/user/all")
    public ResponseEntity selectUserAllOrderList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                             pageNum,
                                                 @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                         pageSize,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "userId", required = true) String userId,
                                                 @RequestParam(value = "goodsType", required = false) String goodsType,
                                                 @RequestParam(value = "startTime", required = false) String startTime,
                                                 @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO order = new OrderBO();
        UserBO user = new UserBO();
        user.setId(userId);
        order.setUser(user);

        GoodsBO goodsBO = new GoodsBO();
        goodsBO.setName(name);
        order.setIsInvoice(false);
        order.setGoodsType(goodsType);
        if (startTime != null && !"".equals(startTime)) {
            order.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            order.setEndTime(DateUtils.strToDate(endTime));
        }
        List<OrderBO> orderBOs = orderService.selectUserAllOrderList(order, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderBOs);
        LOGGER.info("{}", orderBOs);
        return (orderBOs == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo
                        .getTotal()));
    }

    /**
     * 用户所有订单查询,未开票和已开票的订单
     *
     * @param pageNum   页数
     * @param pageSize  条数
     * @param name      商品名称
     * @param userId    用户ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单列表
     */
    @GetMapping(path = "/user/all/invoice")
    public ResponseEntity selectOrderListByInvoice(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                               pageNum,
                                                   @RequestParam(value = "size", defaultValue = Constant.pageSize)
                                                           int pageSize,
                                                   @RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "userId", required = true) String userId,
                                                   @RequestParam(value = "startTime", required = false) String
                                                               startTime,
                                                   @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO order = new OrderBO();
        UserBO user = new UserBO();
        user.setId(userId);
        order.setUser(user);

        GoodsBO goodsBO = new GoodsBO();
        goodsBO.setName(name);
        if (startTime != null && !"".equals(startTime)) {
            order.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            order.setEndTime(DateUtils.strToDate(endTime));
        }

        List<OrderBO> orderBOs = orderService.selectOrderListByInvoice(order, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderBOs);
        LOGGER.info("{}", orderBOs);
        return (orderBOs == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo
                        .getTotal()));
    }


    /**
     * 用户所有订单查询,根据状态分类
     *
     * @param pageNum   页数
     * @param pageSize  条数
     * @param name      商品名称
     * @param userId    用户ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单列表
     */
    @GetMapping(path = "/user/list")
    public ResponseEntity selectUserOrderList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                               pageNum,
                                                   @RequestParam(value = "size", defaultValue = Constant.pageSize)
                                                           int pageSize,
                                                   @RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "userId", required = true) String userId,
                                                   @RequestParam(value = "status", required = true) String status,
                                                   @RequestParam(value = "startTime", required = false) String
                                                               startTime,
                                                   @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO order = new OrderBO();
        UserBO user = new UserBO();
        user.setId(userId);
        order.setUser(user);

        GoodsBO goodsBO = new GoodsBO();
        goodsBO.setName(name);
        if (startTime != null && !"".equals(startTime)) {
            order.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            order.setEndTime(DateUtils.strToDate(endTime));
        }
        order.setStatus(status.split(","));
        List<OrderBO> orderBOs = orderService.selectOrderListByInvoice(order, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderBOs);
        LOGGER.info("{}", orderBOs);
        return (orderBOs == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", JSON.toJSONString(pageInfo.getList()), "total", pageInfo
                        .getTotal()));
    }



    /**
     * 课程订单查询
     *
     * @param pageNum   页数
     * @param pageSize  条数
     * @param goodsId   商品ID
     * @param nickname  用户昵称
     * @param username  用户名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 订单列表
     */
    @GetMapping(path = "/curriculum")
    public ResponseEntity selectCurriculumOrderList(@RequestParam(value = "page", defaultValue = Constant.pageNum)
                                                                int pageNum,
                                                    @RequestParam(value = "size", defaultValue = Constant.pageSize)
                                                            int pageSize,
                                                    @RequestParam(value = "goodsId", required = true) String goodsId,
                                                    @RequestParam(value = "nickname", required = false) String nickname,
                                                    @RequestParam(value = "username", required = false) String username,
                                                    @RequestParam(value = "startTime", required = false) String
                                                                startTime,
                                                    @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO orderBO = new OrderBO();
        UserBO user = new UserBO();
        user.setNickname(nickname);
        user.setUsername(username);
        orderBO.setUser(user);
        orderBO.setGoodsId(goodsId);
        if (startTime != null && !"".equals(startTime)) {
            orderBO.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            orderBO.setEndTime(DateUtils.strToDate(endTime));
        }

        List<OrderBO> orderList = orderService.selectCurriculumOrderList(orderBO, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderList);
        LOGGER.info("{}", orderList);
        return (orderList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }


    /**
     * 查询订单详情-后台
     *
     * @param orderNo 订单号
     * @return 订单详情
     */
    @GetMapping(path = "/admin/select/{orderNo}")
    public ResponseEntity selectByOrderNoAdmin(@PathVariable String orderNo) {
        LOGGER.info("{}", orderNo);
        OrderBO orderBO = orderService.selectByOrderNoAdmin(orderNo);
        LOGGER.info("{}", orderBO);
        return ResponseEntity.ok(Utils.kv("data", orderBO));
    }

    /**
     * 查询订单详情
     *
     * @param orderNo 订单号
     * @return 订单详情
     */
    @GetMapping(path = "/select/{orderNo}")
    public ResponseEntity selectByOrderNo(@PathVariable String orderNo) {
        LOGGER.info("{}", orderNo);
        OrderBO orderBO = orderService.selectByOrderNo(orderNo);
        LOGGER.info("{}", orderBO);
        return ResponseEntity.ok(Utils.kv("data", orderBO));
    }

    /**
     * 前台-查询订单详情
     *
     * @param orderNo 订单号
     * @return 订单详情
     */
    @GetMapping(path = "/web/{orderNo}")
    public ResponseEntity selectWebByOrderNo(@PathVariable String orderNo) {
        LOGGER.info("{}", orderNo);
        OrderBO orderBO = orderService.selectWebByOrderNo(orderNo);
        LOGGER.info("{}", orderBO);
        return ResponseEntity.ok(Utils.kv("data", orderBO));
    }

    /**
     * 微信端-查询订单详情
     *
     * @param orderNo 订单号
     * @return 订单详情
     */
    @GetMapping(path = "/wechat/{orderNo}")
    public ResponseEntity selectWeChatByOrderNo(@PathVariable String orderNo) {
        LOGGER.info("{}", orderNo);
        OrderBO orderBO = orderService.selectWeChatByOrderNo(orderNo);
        LOGGER.info("{}", orderBO);
        return ResponseEntity.ok(Utils.kv("data", orderBO));
    }

    /**
     * 查询订单详情信息
     *
     * @param orderNo 订单号
     * @return 订单详情
     */
    @GetMapping(path = "/detail/{orderNo}")
    public ResponseEntity selectOrderDetail(@PathVariable String orderNo) {
        LOGGER.info("{}", orderNo);
        OrderBO orderBO = orderService.selectOrderDetail(orderNo);
        LOGGER.info("{}", orderBO);
        return ResponseEntity.ok(Utils.kv("data", orderBO));
    }


    /**
     * 根据交易流水号查询订单合并内容
     * @param tradeNo 订单号
     * @return 订单详情
     */
    @GetMapping(path = "/select/trade/{tradeNo}")
    public ResponseEntity selectByTradeNo(@PathVariable String tradeNo) {
        LOGGER.info("{}", tradeNo);
        OrderTradeBO orderTradeBO = orderService.selectOrderTrade(tradeNo);
        LOGGER.info("{}", orderTradeBO);
        return ResponseEntity.ok(Utils.kv("data", orderTradeBO));
    }

    /**
     * 导出订单信息
     * @return 订单列表
     */
    @GetMapping(path = "/export")
    public ResponseEntity exportOrder() {
        Order order = new Order();
        order.setOrderStatus("4");
        List<OrderListBO> orderListBOList = orderService.selectExprotOrder(order);
        LOGGER.info("{}", orderListBOList);
        return ResponseEntity.ok(Utils.kv("dataList", orderListBOList));
    }

    /**
     * 导入订单信息
     */
    @PostMapping(path = "/import/{expressCompId}")
    public ResponseEntity importOrder(@Valid @RequestBody List<OrderBO> orderBOList,
                                      @PathVariable String expressCompId,
                                      HttpServletRequest request) {
        LOGGER.info("{}", orderBOList);
        orderService.selectImportOrder(orderBOList, expressCompId, request);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 用户下单
     * @param orderSubmitBO 订单信息
     * @param userId        用户 ID
     * @return 订单信息
     */
    @PostMapping(path = "/submit/{userId}")
    public ResponseEntity submitOrder(@Valid @RequestBody OrderSubmitBO orderSubmitBO, @PathVariable String
            userId) {
        LOGGER.info("{}", orderSubmitBO);
        orderSubmitBO.setUserId(userId);
        OrderBO bo = orderService.submitOrder(orderSubmitBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 用户修改订单
     * @param orderUpdateBO 订单信息
     * @param userId        用户ID
     * @return 订单信息
     */
    @PostMapping(path = "/update/{userId}")
    public ResponseEntity updateOrder(@Valid @RequestBody OrderUpdateBO orderUpdateBO, @PathVariable String
            userId) {
        LOGGER.info("{}", orderUpdateBO);
        orderUpdateBO.setUserId(userId);
        OrderUpdateBO bo = orderService.updateOrder(orderUpdateBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 用户将订单改为支付中
     * @param orderPayBO 支付信息
     * @return 订单信息
     */
    @PostMapping(path = "/payment")
    public ResponseEntity paymentOrderFictitious(@Valid @RequestBody OrderPayBO orderPayBO, HttpServletRequest
            request) {
        LOGGER.info("{}{}", orderPayBO);
        orderService.paymentOrder(orderPayBO, "RMB", request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 用户交易积分订单
     * @param orderPayBO 支付信息
     * @return 订单信息
     */
    @PostMapping(path = "/paypoints")
    public ResponseEntity paymentOrder(@Valid @RequestBody OrderPayBO orderPayBO, HttpServletRequest request) {
        LOGGER.info("{}{}", orderPayBO);
        orderPayBO.setPayMethod("POINTS");
        orderService.paymentOrder(orderPayBO, "POINTS", request);

        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 用户确认收货
     * @param orderNo 订单号
     * @param userId  用户ID
     */
    @PostMapping(path = "/confirm/{orderNo}/{userId}")
    public ResponseEntity confirmOrder(@PathVariable String orderNo, @PathVariable String userId) {
        LOGGER.info("{}{}", orderNo, userId);
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        orderService.confirmOrder(order);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 订单发货
     *
     * @param orderOperationBO 订单操作信息
     */
    @PostMapping(path = "/send")
    public ResponseEntity sendOrder(@Valid @RequestBody OrderOperationBO orderOperationBO, HttpServletRequest request) {
        LOGGER.info("{}", orderOperationBO);
        orderService.sendOrder(orderOperationBO, request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 订单作废
     * @param orderOperationBO 订单操作信息
     */
    @PostMapping(path = "/invalid")
    public ResponseEntity invalidOrder(@Valid @RequestBody OrderOperationBO orderOperationBO) {
        LOGGER.info("{}", orderOperationBO);
        orderService.invalidOrder(orderOperationBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 用户取消订单
     * @param orderCancelBO 订单取消信息
     * @return 订单信息
     */
    @PostMapping(path = "/cancel")
    public ResponseEntity cancelOrder(@Valid @RequestBody OrderCancelBO orderCancelBO) {
        LOGGER.info("{}", orderCancelBO);
        OrderBO bo = orderService.cancelOrder(orderCancelBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 删除订单-前台用户
     *
     * @param userId 用户ID
     * @param id    ID
     */
    @DeleteMapping(path = "/delete/{userId}/{id}")
    public ResponseEntity deleteOrder(@PathVariable String userId, @PathVariable String id) {
        OrderBO orderBO = new OrderBO();
        orderBO.setOrderNo(id);
        orderBO.setUserId(userId);
        orderService.deleteOrder(orderBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 删除订单-后台管理员
     * @param id id
     */
    @DeleteMapping(path = "/admin/delete/{userId}/{id}")
    public ResponseEntity adminDeleteOrder(@PathVariable String id) {
        OrderBO orderBO = new OrderBO();
        orderBO.setOrderNo(id);
        orderService.adminDeleteOrder(orderBO);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 根据GoodsId和UserId查询订单信息
     *
     * @param goodsId 商品 ID
     * @param userId  用户ID
     */
    @GetMapping(path = "/goods")
    public ResponseEntity selectOrderByGoodsIdAndUserId(@RequestParam(value = "goodsId", required = true) String
                                                                    goodsId,
                                                        @RequestParam(value = "userId", required = true) String
                                                                userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("goodsId", goodsId);
        LOGGER.info("{}", map);
        OrderBO bo = orderService.selectOrderByGoodsIdAndUserId(map);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 统计订单，统计维度为【订单状态】
     *
     * @param tradeMethod 交易方式
     * @param startTime   开始时间
     * @param endTime     结束时间
     */
    @GetMapping(path = "/status/statis")
    public ResponseEntity statisOrderByStatus(@RequestParam(value = "tradeMethod", required = true) String tradeMethod,
                                              @RequestParam(value = "startTime", required = false) String startTime,
                                              @RequestParam(value = "endTime", required = false) String endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("tradeMethod", tradeMethod);
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }

        List<OrderStatisBO> orderList = orderService.statisOrderByStatus(map);
        PageInfo<OrderStatisBO> pageInfo = new PageInfo<>(orderList);
        LOGGER.info("{}", orderList);
        return (orderList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 统计订单，统计维度为【月份】
     *
     * @param tradeMethod 交易方式
     * @param startTime   开始时间
     * @param endTime     结束时间
     */
    @GetMapping(path = "/month/statis")
    public ResponseEntity statisOrder(@RequestParam(value = "tradeMethod", required = true) String tradeMethod,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("tradeMethod", tradeMethod);
        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }

        List<OrderStatisBO> orderList = orderService.statisOrderByMonth(map);
        PageInfo<OrderStatisBO> pageInfo = new PageInfo<>(orderList);
        LOGGER.info("{}", orderList);
        return (orderList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }



    /**
     * 会员订购订单直接退货
     *
     * @param id 订单ID
     * @return 订单信息
     */
    @PutMapping(path = "/return/{id}/{goodsId}")
    public ResponseEntity cancelOrder(@PathVariable String id,@PathVariable String goodsId,@Valid @RequestBody VipLogBO vipLogBO,HttpServletRequest httpServletRequest) {
        LOGGER.info("{}", id);
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",id);
        map.put("goodsId",goodsId);
        map.put("vipLogBO",vipLogBO);
        orderService.updateOrderReturn(map,httpServletRequest);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 我的销售订单列表
     *
     * @param pageNum     页数
     * @param pageSize    条数
     * @param orderNo     订单号
     * @param username    用户名
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param orderStatus 订单状态
     * @param phone       电话号码
     * @return 订单列表
     */
    @GetMapping(path = "/myOrder")
    public ResponseEntity selectMyOrderList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                            @RequestParam(value = "orderNo", required = false) String orderNo,
                                            @RequestParam(value = "orderStatus", required = false) String orderStatus,
                                            @RequestParam(value = "username", required = false) String username,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "tradingChannels", required = false) String tradingChannels,
                                            @RequestParam(value = "recommendUser", required = false) String recommendUser,
                                            @RequestParam(value = "recommendPhone", required = false) String recommendPhone,
                                            @RequestParam(value = "startTime", required = false) String startTime,
                                            @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        /*OrderBO orderBO = new OrderBO();
        UserBO user = new UserBO();
        user.setUsername(username);
        user.setPhone(phone);
        orderBO.setUser(user);
        orderBO.setOrderNo(orderNo);
        orderBO.setOrderStatus(orderStatus);*/
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("phone",phone);
        map.put("orderNo",orderNo);
        map.put("orderStatus",orderStatus);
        map.put("tradingChannels",tradingChannels);
        map.put("recommendUser",recommendUser);
        map.put("recommendPhone",recommendPhone);

        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }

        List<OrderBO> orderList = orderService.selectMyOrderList(map, pageNum, pageSize);
        PageInfo<OrderBO> pageInfo = new PageInfo<>(orderList);
        LOGGER.info("{}", orderList);
        return (orderList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo
                        .getTotal()));
    }

    /**
     * 我的销售订单金额统计
     *
     * @param pageNum     页数
     * @param pageSize    条数
     * @param orderNo     订单号
     * @param username    用户名
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param orderStatus 订单状态
     * @param phone       电话号码
     * @return 订单列表
     */
    @GetMapping(path = "/myOrder/money")
    public ResponseEntity selectMyOrderMoney(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                            @RequestParam(value = "orderNo", required = false) String orderNo,
                                            @RequestParam(value = "orderStatus", required = false) String orderStatus,
                                            @RequestParam(value = "username", required = false) String username,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "tradeMethod", required = false) String tradeMethod,
                                            @RequestParam(value = "tradingChannels", required = false) String tradingChannels,
                                            @RequestParam(value = "recommendUser", required = false) String recommendUser,
                                            @RequestParam(value = "recommendPhone", required = false) String recommendPhone,
                                            @RequestParam(value = "startTime", required = false) String startTime,
                                            @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        /*OrderBO orderBO = new OrderBO();
        UserBO user = new UserBO();
        user.setUsername(username);
        user.setPhone(phone);
        orderBO.setUser(user);
        orderBO.setOrderNo(orderNo);
        orderBO.setOrderStatus(orderStatus);*/
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("phone",phone);
        map.put("orderNo",orderNo);
        map.put("orderStatus",orderStatus);
        map.put("tradingChannels",tradingChannels);
        map.put("tradeMethod",tradeMethod);
        map.put("recommendUser",recommendUser);
        map.put("recommendPhone",recommendPhone);

        if (startTime != null && !"".equals(startTime)) {
            map.put("startTime", DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            map.put("endTime", DateUtils.strToDate(endTime));
        }

        Double myOrderMoney = orderService.selectMyOrderMoney(map);
        if(myOrderMoney != null){
            BigDecimal b = new BigDecimal(myOrderMoney);
            return ResponseEntity.ok(Utils.kv("data",b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        }else{
            BigDecimal b = new BigDecimal(0d);
            return ResponseEntity.ok(Utils.kv("data", b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        }
    }
}
