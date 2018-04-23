package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.*;
import com.abc12366.uc.mapper.db1.*;
import com.abc12366.uc.mapper.db2.*;
import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.VipLogBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.model.gift.UamountLog;
import com.abc12366.uc.model.order.*;
import com.abc12366.uc.model.order.bo.*;
import com.abc12366.uc.model.pay.RefundRes;
import com.abc12366.uc.model.pay.WxRefund;
import com.abc12366.uc.model.pay.WxRefundRsp;
import com.abc12366.uc.model.pay.bo.AliRefund;
import com.abc12366.uc.service.*;
import com.abc12366.uc.service.order.CouponService;
import com.abc12366.uc.service.order.OrderService;
import com.abc12366.uc.util.AliPayConfig;
import com.abc12366.uc.util.CharUtil;
import com.abc12366.uc.util.wx.SignUtil;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxMchConnectFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lizhongwei
 * @date 2017-10-19
 * @since 1.0.0
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRoMapper orderRoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrderProductRoMapper orderProductRoMapper;

    @Autowired
    private ProductRoMapper productRoMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepoMapper productRepoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PointsLogService pointsLogService;

    @Autowired
    private OrderProductspecMapper orderProductspecMapper;

    @Autowired
    private VipLogService vipLogService;

    @Autowired
    private DictRoMapper dictRoMapper;

    @Autowired
    private TradeLogMapper tradeLogMapper;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private TradeRoMapper tradeRoMapper;

    @Autowired
    private ExpressCompRoMapper expressCompRoMapper;

    @Autowired
    private TodoTaskService todoTaskService;

    @Autowired
    private PointsRuleService pointsRuleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TradeLogRoMapper tradeLogRoMapper;

    @Autowired
    private VipPrivilegeLevelRoMapper vipPrivilegeLevelRoMapper;

    @Autowired
    private UamountLogMapper uamountLogMapper;

    @Autowired
    private IMsgSendV2service msgSendV2Service;

    /**
     * 优惠劵查询操作
     */
    @Autowired
    private CouponService couponService;

    /**
     * 优惠劵查询操作
     */
    @Autowired
    private CouponRoMapper couponRoMapper;

    /**
     * 购买优惠赠送
     */
    @Autowired
    private OrderGiftMapper orderGiftMapper;

    @Autowired
    private OrderGiftRoMapper orderGiftRoMapper;

    @Override
    public List<OrderBO> selectList(OrderBO orderBO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return orderRoMapper.selectList(orderBO);
    }

    @Override
    public OrderBO selectByOrderNo(String orderNo) {
        OrderBO orderBO = orderRoMapper.selectById(orderNo);
        List<OrderGiftBO> orderGiftBOList = orderGiftRoMapper.selectByOrderNo(orderNo);
        if (orderGiftBOList != null && orderGiftBOList.size() > 0) {
            orderBO.setOrderGiftBOList(orderGiftBOList);
        }
        return orderBO;
    }


    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public OrderBO joinCart(OrderBO orderBO) {
        //判断产品数量
        Order order = new Order();
        BeanUtils.copyProperties(orderBO, order);
        order.setOrderNo(DateUtils.getDateToString());
        Date date = new Date();
        order.setCreateTime(date);
        order.setLastUpdate(date);
        order.setOrderStatus("0");
        int insert = orderMapper.insert(order);
        if (insert != 1) {
            LOGGER.info("加入购物车失败：{}", orderBO);
            throw new ServiceException(4138);
        } else {
            OrderBO temp = new OrderBO();
            BeanUtils.copyProperties(order, temp);
            return temp;
        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public OrderBO updateCart(OrderBO orderBO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderBO, order);

        Date date = new Date();
        order.setLastUpdate(date);
        int update = orderMapper.update(order);
        if (update != 1) {
            LOGGER.info("修改购物车信息失败：{}", orderBO);
            throw new ServiceException(4102);
        } else {
            OrderBO temp = new OrderBO();
            BeanUtils.copyProperties(order, temp);
            return temp;
        }
    }

    @Override
    public List<OrderBO> selectOrderList(OrderBO order, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return orderRoMapper.selectOrderList(order);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void deleteCart(OrderBO orderBO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderBO, order);
        int del = orderMapper.deleteByIdAndUserId(order);
        if (del != 1) {
            LOGGER.info("删除购物车信息失败：{}", orderBO);
            throw new ServiceException(4103);
        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public OrderBO submitOrder(OrderSubmitBO orderSubmitBO) {
        LOGGER.info("提交的订单信息：{}", orderSubmitBO);
        User user = userMapper.selectOne(orderSubmitBO.getUserId());
        orderSubmitBO.setNowVipLevel(user.getVipLevel());
        Date date = new Date();
        //根据是否需要寄送来判断地址是否填写
        if (orderSubmitBO.getIsShipping() != null && orderSubmitBO.getIsShipping() == 1) {
            if (orderSubmitBO.getConsignee() == null || "".equals(orderSubmitBO.getConsignee())
                    || orderSubmitBO.getContactNumber() == null && "".equals(orderSubmitBO.getContactNumber())
                    || orderSubmitBO.getShippingAddress() == null && "".equals(orderSubmitBO.getShippingAddress())) {
                LOGGER.info("需要寄送的商品必须填写联系人、联系方式、寄送地址：{}", orderSubmitBO);
                throw new ServiceException(4922);
            }
        }
        Order order = new Order();
        //加入订单与产品关系信息
        List<OrderProductBO> orderProductBOs = orderSubmitBO.getOrderProductBOList();
        if (orderProductBOs == null) {
            LOGGER.info("产品信息错误：{}", orderSubmitBO);
            throw new ServiceException(4166);
        } else {
            for (OrderProductBO orderProductBO : orderProductBOs) {
                //判断是否需要查询产品库存信息
                if (orderProductBO.getProductId() != null && !"".equals(orderProductBO.getProductId())) {
                    ProductBO prBO = productRoMapper.selectBOById(orderProductBO.getProductId());
                    if (prBO == null) {
                        throw new ServiceException(4903, "商品信息错误");
                    }
                    if (prBO.getStock() == null) {
                        throw new ServiceException(4903, "商品库存信息异常");
                    }
                    if (prBO.getStock() < orderProductBO.getNum()) {
                        LOGGER.info("商品库存不足：{}", prBO);
                        throw new ServiceException(4903);
                    }
                }

                BeanUtils.copyProperties(orderSubmitBO, order);
                order.setOrderStatus("2");
                String orderNo = DateUtils.getDateToString();
                order.setOrderNo(orderNo);

                order.setCreateTime(date);
                order.setLastUpdate(date);
                order.setIsInvoice(false);

                //判断是否使用优惠劵
                if (orderSubmitBO.getUseCouponId() != null && !"".equals(orderSubmitBO.getUseCouponId())) {
                    //判断优惠券类型是否可以用
                    CouponOrderBO couponOrderBO = new CouponOrderBO();
                    couponOrderBO.setUseCouponId(orderSubmitBO.getUseCouponId());
                    couponOrderBO.setUserId(orderSubmitBO.getUserId());
                    couponOrderBO.setOrderNo(orderNo);
                    couponOrderBO.setCategoryId(orderProductBO.getTradingChannels());
                    couponOrderBO.setAmount(orderSubmitBO.getTotalPrice());
                    couponOrderBO.setOperation("1");
                    //优惠劵设置已冻结
                    LOGGER.info("使用的优惠卷信息：{}", couponOrderBO);
                    CouponUser couponUser = couponRoMapper.selectUserCouponById(orderSubmitBO.getUseCouponId());
                    if (orderProductBO.getTradingChannels() != null
                            && !couponUser.getCategoryIds().contains(CouponServiceImpl.ALL)
                            && !couponUser.getCategoryIds().contains(orderProductBO.getTradingChannels())) {
                        LOGGER.info("商品类目与优惠券商品类目不一致，优惠券无法使用");
                        throw new ServiceException(7138);
                    }

                    couponOrderBO.setStatus("3");
                    order.setTotalPrice(couponService.userUseCoupon(couponOrderBO));
                }

                if (StringUtils.isNotEmpty(orderProductBO.getTradingChannels()) && "CSKT".equals(orderProductBO.getTradingChannels())) {
                    List<OrderGiftBO> orderGiftBOList = orderSubmitBO.getOrderGiftBOList();
                    if (orderGiftBOList != null && orderGiftBOList.size() > 0) {

                        for (OrderGiftBO orderGiftBO : orderGiftBOList) {
                            LOGGER.info("购买优惠赠送：{}", orderGiftBO);
                            OrderGift gift = orderGiftRoMapper.selectCurriculumGift(orderGiftBO.getId());
                            if (gift != null) {
                                gift.setGiftId(orderNo);
                                int gInt = orderGiftMapper.insert(gift);
                                if (gInt != 1) {
                                    LOGGER.info("购买优惠赠送新增失败：{}", gift);
                                    throw new ServiceException(7151);
                                }
                            }
                        }
                    }
                }
                int insert = orderMapper.insert(order);
                if (insert != 1) {
                    LOGGER.info("提交产品订单失败：{}", orderSubmitBO);
                    throw new ServiceException(4139);
                }

                orderProductBO.setOrderNo(order.getOrderNo());
                orderProductBO.setCreateTime(date);
                orderProductBO.setLastUpdate(date);
                OrderProduct orderProduct = new OrderProduct();
                BeanUtils.copyProperties(orderProductBO, orderProduct);
                int opInsert = orderProductMapper.insert(orderProduct);
                if (opInsert != 1) {
                    LOGGER.info("提交订单与产品关系信息失败：{}", orderProduct);
                    throw new ServiceException(4167);
                }
                if (orderSubmitBO.getRemark() != null) {
                    insertOrderLog(orderSubmitBO.getUserId(), order.getOrderNo(), "2", "用户下单；" + orderSubmitBO.getRemark(), "0");
                } else {
                    insertOrderLog(orderSubmitBO.getUserId(), order.getOrderNo(), "2", "用户下单；", "0");
                }
            }
        }
        //新增交易流水号
        String tradeNo = DateUtils.getJYLSH();
        TradeLog tradeLog = new TradeLog();
        tradeLog.setLastUpdate(date);
        tradeLog.setCreateTime(date);
        tradeLog.setTradeNo(tradeNo);
        LOGGER.info("新增交易日志记录：{}" + tradeNo);
        int logInsert = tradeLogMapper.insert(tradeLog);
        if (logInsert != 1) {
            LOGGER.info("新增交易日志记录失败：{}", tradeNo);
            throw new ServiceException(4919);
        }
        Trade trade = new Trade();
        trade.setTradeNo(tradeNo);
        trade.setOrderNo(order.getOrderNo());
        trade.setCreateTime(date);
        trade.setLastUpdate(date);
        int tradeInsert = tradeMapper.insert(trade);
        if (tradeInsert != 1) {
            LOGGER.info("新增交易日志与订单关系失败：{}", tradeNo);
            throw new ServiceException(4920);
        }

        OrderBO temp = new OrderBO();
        BeanUtils.copyProperties(order, temp);
        TradeBO tradeBO = new TradeBO();
        BeanUtils.copyProperties(trade, tradeBO);
        temp.setTradeBO(tradeBO);

        return temp;

    }

    /**
     * 处理人民币购买服务
     */
    /*@Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    void operationMoneyServiceOrder(OrderBO orderBO, Order order, OrderProductBO orderProductBO, String orderStatus) {
        orderProductBO.setOrderNo(orderBO.getOrderNo());
        orderBO.setOrderStatus(orderStatus);
        BeanUtils.copyProperties(orderBO, order);
        int insert = orderMapper.insert(order);
        if (insert != 1) {
            LOGGER.info("提交产品订单失败：{}", orderBO);
            throw new ServiceException(4139);
        }
        orderProductBO.setOrderNo(orderBO.getOrderNo());
        OrderProduct orderProduct = new OrderProduct();
        BeanUtils.copyProperties(orderProductBO, orderProduct);

        int opInsert = orderProductMapper.insert(orderProduct);
        if (opInsert != 1) {
            LOGGER.info("提交订单与产品关系信息失败：{}", orderProduct);
            throw new ServiceException(4167);
        }
    }*/

    /**
     * 处理人民币充值积分订单
     */
    /*@Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    void operationMoneyRechargeOrder(Order order, OrderProductBO orderProductBO, String orderStatus) {
        // 查询用户信息
        order.setOrderStatus(orderStatus);
        orderProductBO.setOrderNo(order.getOrderNo());
        int insert = orderMapper.insert(order);
        if (insert != 1) {
            LOGGER.info("提交产品订单失败：{}", order);
            throw new ServiceException(4139);
        }
        orderProductBO.setOrderNo(order.getOrderNo());
        OrderProduct orderProduct = new OrderProduct();
        BeanUtils.copyProperties(orderProductBO, orderProduct);

        int opInsert = orderProductMapper.insert(orderProduct);
        if (opInsert != 1) {
            LOGGER.info("提交订单与产品关系信息失败：{}", orderProduct);
            throw new ServiceException(4167);
        }
    }*/

    /**
     * 加入订单日志
     */
    private void insertOrderLog(String userId, String orderNo, String status, String remark, String logType) {
        //加入订单日志信息
        OrderLog orderLog = new OrderLog();
        orderLog.setId(Utils.uuid());
        orderLog.setAction(selectFieldValue(status));
        orderLog.setOrderNo(orderNo);
        orderLog.setCreateTime(new Date());
        orderLog.setCreateUser(userId);
        orderLog.setRemark(remark);
        orderLog.setLogType(logType);
        int logInsert = orderLogMapper.insert(orderLog);
        if (logInsert != 1) {
            LOGGER.info("订单日志新增失败：{}", orderLog);
            throw new ServiceException(4901);
        }
    }

    private String selectFieldValue(String value) {
        Dict dict = new Dict();
        dict.setDictId("orderStatus");
        dict.setFieldValue(value);
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldKey() : "";
    }

    @Override
    public List<OrderBO> selectCartList(OrderBO order) {
        return orderRoMapper.selectCartList(order);
    }

    @Override
    public void submitCart(Order order) {
        orderRoMapper.selectOrder(order);
        //验证产品信息
        //将购物车状态修改为新订单状态
        order.setOrderStatus("1");
        orderMapper.update(order);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void deleteOrder(OrderBO orderBO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderBO, order);
        //查询订单状态
        OrderBO bo = orderRoMapper.selectOrder(order);
        if (bo == null) {
            LOGGER.info("订单信息不存在：{}", orderBO);
            throw new ServiceException(4134);
        }
        //订单状态，2：待付款，3：付款中，4：付款成功，5：已发货，6：已完成，7：已结束，8：付款失败，9：已退单，11：已删除
        if ("7".equals(bo.getOrderStatus()) || "2".equals(bo.getOrderStatus())) {
            order.setOrderStatus("11");
            int del = orderMapper.update(order);
            if (del != 1) {
                LOGGER.info("删除失败：{}", orderBO);
                throw new ServiceException(4103);
            }
            insertOrderLog(orderBO.getUserId(), orderBO.getOrderNo(), "11", "用户删除订单", "0");
        } else {
            LOGGER.info("订单只有在未付款或已结束可以删除：{}", orderBO);
            throw new ServiceException(4140);
        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public OrderBO cancelOrder(OrderCancelBO orderCancelBO) {
        OrderBO bo = orderRoMapper.selectWebByOrderNo(orderCancelBO.getOrderNo());
        if (bo == null) {
            LOGGER.info("订单信息不存在：{}", orderCancelBO);
            throw new ServiceException(4134);
        }
        if (!"2".equals(bo.getOrderStatus())) {
            LOGGER.info("只有待支付可以取消订单：{}", orderCancelBO);
            throw new ServiceException(4189);
        }
        orderCancelBO.setOrderStatus("7");
        Order order = new Order();
        BeanUtils.copyProperties(orderCancelBO, order);
        int update = orderMapper.update(order);
        if (update != 1) {
            LOGGER.info("修改失败：{}", order);
            throw new ServiceException(4102);
        }
        //查询订单和交易记录对应关系
        Trade trade = tradeRoMapper.selectOrderNo(bo.getOrderNo());
        if (trade != null) {
            TradeLog tradeLog = new TradeLog();
            tradeLog.setTradeNo(trade.getTradeNo());
            tradeLog.setTradeStatus("4");
            //更新交易记录信息
            tradeLogMapper.update(tradeLog);
        }
        LOGGER.info("获取优惠劵信息");
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderCancelBO.getOrderNo());
        map.put("userId", orderCancelBO.getUserId());
        CouponUser couponUser = couponService.selectCouponUser(map);
        if (couponUser != null) {
            LOGGER.info("优惠劵取消");
            List<OrderProductBO> orderProductBOs = bo.getOrderProductBOList();
            for (OrderProductBO orderProductBO : orderProductBOs) {
                CouponOrderBO couponOrderBO = new CouponOrderBO();
                couponOrderBO.setUseCouponId(couponUser.getId());
                couponOrderBO.setUserId(orderCancelBO.getUserId());
                couponOrderBO.setOrderNo(orderCancelBO.getOrderNo());
                couponOrderBO.setCategoryId(orderProductBO.getTradingChannels());
                couponOrderBO.setAmount(bo.getTotalPrice());
                couponOrderBO.setOperation("0");
                //优惠劵设置已领取
                couponOrderBO.setStatus("1");
                couponService.userUseCoupon(couponOrderBO);
            }
        }
        insertOrderLog(bo.getUserId(), bo.getOrderNo(), "7", "用户取消订单", "0");
        return bo;
    }

    @Override
    public List<OrderBO> selectUserAllOrderList(OrderBO order, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return orderRoMapper.selectUserAllOrderList(order);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void paymentOrder(OrderPayBO orderPayBO, String type, HttpServletRequest request) {
        String tradeNo = orderPayBO.getTradeNo();
        //根据交易流水号查询订单信息
        List<OrderBO> orderBOList = orderRoMapper.selectByTradeNo(tradeNo);

        for (OrderBO orderBO : orderBOList) {
            if (orderBO != null) {
                OrderProductBO pBO = new OrderProductBO();
                String orderNo = orderBO.getOrderNo();
                String userId = orderBO.getUserId();
                pBO.setOrderNo(orderNo);
                List<OrderProductBO> orderProductBOs = orderProductRoMapper.selectByOrderNo(pBO);

                LOGGER.info("获取优惠劵信息");
                Map<String, Object> map = new HashMap<>();
                map.put("orderNo", orderBO.getOrderNo());
                map.put("userId", orderBO.getUserId());
                CouponUser couponUser = couponService.selectCouponUser(map);

                for (OrderProductBO orderProductBO : orderProductBOs) {
                    int isPay = orderPayBO.getIsPay();
                    Order order = new Order();
                    orderBO.setLastUpdate(new Date());
                    BeanUtils.copyProperties(orderBO, order);
                    //订单状态，2：待付款，3：付款中，4：付款成功，5：已发货，6：已完成，7：已结束，8：付款失败，9：已退单
                    //UC商城:UCSC，财税课堂:CSKT，积分充值:JFCZ，会员充值:HYCZ
                    String trading = orderProductBO.getTradingChannels();
                    order.setPayMethod(orderPayBO.getPayMethod());
                    if ("RMB".equals(type)) {
                        //支付状态，1：支付中，2：支付成功，3：支付失败，
                        if (isPay == 1) {
                            order.setOrderStatus("3");
                            //修改订单状态
                            updOrder(order);
                            insertOrderLog(userId, orderNo, "3", "用户付款中", "0");
                        } else if (isPay == 2) {
                            if (!"2".equals(orderBO.getOrderStatus()) && !"3".equals(orderBO.getOrderStatus())) {
                                LOGGER.warn("订单只有在待支付或支付中才能进行正常支付：{}", orderBO.getOrderStatus());
                                throw new ServiceException(4925);
                            }
                            //判断是否需要查询产品库存信息
                            setTodoTask(orderBO);
                            if ("UCSC".equals(trading)) {
                                updateStock(orderBO, orderProductBO);
                                order.setOrderStatus("4");
                                //修改订单状态
                                updOrder(order);

                                insertGivePoints(orderBO, orderBO.getGiftPoints());
                                insertOrderLog(userId, orderNo, order.getOrderStatus(), "用户付款成功", "0");
                            } else if ("HYCZ".equals(trading)) {
                                order.setOrderStatus("6");
                                //修改订单状态
                                updOrder(order);

                                insertPoints(orderBO, orderBO.getGiftPoints());

                                LOGGER.info("插入会员日志: {}", orderNo);
                                insertVipLog(orderNo, userId, orderProductBO.getSpecInfo());

                                LOGGER.info("查询是否为会员服务订单，支付成功则更新会员状态: {}", orderNo);
                                userService.updateUserVipInfo(userId, orderProductBO.getSpecInfo(), true);

                                insertOrderLog(userId, orderNo, order.getOrderStatus(), "用户付款成功，完成订单", "0");
                                //发送消息
                                sendMemberMsg(orderProductBO, order, request);
                            } else if ("CSKT".equals(trading)) {
                                order.setOrderStatus("6");
                                //修改订单状态
                                updOrder(order);

                                insertPoints(orderBO, orderBO.getGiftPoints());
                                //付款成功，购买优惠赠送
                                opertionGift(orderBO, request);

                                insertOrderLog(userId, orderNo, order.getOrderStatus(), "用户付款成功，完成订单", "0");
                            } else if ("JFCZ".equals(trading)) {
                                order.setOrderStatus("6");
                                //修改订单状态
                                updOrder(order);
                                insertPoints(orderBO, orderBO.getGiftPoints());
                                insertOrderLog(userId, orderNo, order.getOrderStatus(), "用户付款成功，完成订单", "0");
                                //发送消息
                                sendPointsMsg(orderProductBO, order, request);
                            }
                        } else if (isPay == 3) {
                            order.setOrderStatus("2");
                            //修改订单状态
                            updOrder(order);
                            insertOrderLog(userId, orderNo, "2", "等待用户付款", "0");
                        }
                    } else if ("POINTS".equals(type)) {
                        if (!"2".equals(orderBO.getOrderStatus()) && !"3".equals(orderBO.getOrderStatus())) {
                            LOGGER.warn("订单只有在待支付或支付中才能进行正常支付：{}", orderBO.getOrderStatus());
                            throw new ServiceException(4925);
                        }
                        //UC商城:UCSC，财税课堂:CSKT，积分充值:JFCZ，会员充值:HYCZ
                        //判断是否需要查询产品库存信息
                        if ("UCSC".equals(trading)) {
                            updateStock(orderBO, orderProductBO);
                            order.setOrderStatus("4");
                            //修改订单状态
                            updOrder(order);
                            insertDeductPoints(orderBO);
                            insertOrderLog(userId, orderNo, "4", "用户付款成功", "0");
                        } else if ("HYCZ".equals(trading)) {
                            order.setOrderStatus("6");
                            //修改订单状态
                            updOrder(order);
                            insertDeductPoints(orderBO);

                            LOGGER.info("插入会员日志: {}", orderNo);
                            insertVipLog(orderNo, userId, orderProductBO.getSpecInfo());

                            LOGGER.info("查询是否为会员服务订单，支付成功则更新会员状态: {}", orderNo);
                            userService.updateUserVipInfo(userId, orderProductBO.getSpecInfo(), true);

                            insertOrderLog(userId, orderNo, "6", "用户付款成功，完成订单", "0");
                            sendMemberMsg(orderProductBO, order, request);
                        } else if ("CSKT".equals(trading)) {
                            order.setOrderStatus("6");
                            //修改订单状态
                            updOrder(order);
                            //付款成功，购买优惠赠送
                            opertionGift(orderBO, request);
                            insertDeductPoints(orderBO);
                            insertOrderLog(userId, orderNo, "6", "用户付款成功，完成订单", "0");
                        }
                        insertTradeLog(order, tradeNo);
                    }
                    if (couponUser != null) {
                        LOGGER.info("使用过优惠券，对优惠券状态进行修改");
                        CouponOrderBO couponOrderBO = new CouponOrderBO();
                        couponOrderBO.setUseCouponId(couponUser.getId());
                        couponOrderBO.setUserId(orderBO.getUserId());
                        couponOrderBO.setOrderNo(orderBO.getOrderNo());
                        couponOrderBO.setCategoryId(orderProductBO.getTradingChannels());
                        couponOrderBO.setAmount(orderBO.getTotalPrice());
                        couponOrderBO.setOperation("0");
                        //优惠劵设置已领取
                        couponOrderBO.setStatus("2");
                        couponService.userUseCoupon(couponOrderBO);
                    }
                }
            }
        }
    }

    /**
     * 操作购买优惠活动
     */
    private void opertionGift(OrderBO orderBO, HttpServletRequest request) {
        List<OrderGiftBO> orderGiftBOList = orderGiftRoMapper.selectByOrderNo(orderBO.getOrderNo());
        if (orderGiftBOList != null && orderGiftBOList.size() > 0) {
            //操作类型：POINTS-积分，COUPON-优惠券，VIP-会员，AMOUNT-礼包金额
            for (OrderGiftBO bo : orderGiftBOList) {
                if (StringUtils.isNotEmpty(bo.getOperType())) {
                    if ("POINTS".equals(bo.getOperType())) {
                        insertGivePoints(orderBO, Integer.parseInt(bo.getOperValue()));
                    } else if ("COUPON".equals(bo.getOperType())) {
                        try{
                            couponService.userCollectCoupon(orderBO.getUserId(), bo.getOperValue(), request);
                        }catch (ServiceException e){
                            LOGGER.error("购买赠送优惠卷失败: {}", e);
                        }
                    } else if ("VIP".equals(bo.getOperType())) {
                        User temp = userMapper.selectOne(orderBO.getUserId());
                        //赠送会员不能小于当前会员等级
                        if (temp != null && bo.getOperValue().compareTo(temp.getVipLevel()) >= 0) {
                            userService.updateUserVipInfo(orderBO.getUserId(), bo.getOperValue(), false);
                            LOGGER.info("插入会员日志: {}", bo.getOperValue());
                            insertVipLog(orderBO.getOrderNo(), orderBO.getUserId(), bo.getOperValue());
                        }
                    } else if ("AMOUNT".equals(bo.getOperType())) {
                        String userId = orderBO.getUserId();
                        //查询会员礼包业务
                        User temp = userMapper.selectOne(userId);
                        double usable;

                        UamountLog uamountLog = new UamountLog();
                        uamountLog.setId(Utils.uuid());
                        uamountLog.setBusinessId(MessageConstant.HYLB_CODE);
                        uamountLog.setUserId(userId);
                        uamountLog.setCreateTime(new Date());
                        uamountLog.setRemark("购买优惠赠送，获得礼包金额");
                        //赠送金额
                        double income = Double.parseDouble(bo.getOperValue());
                        double amount = 0;
                        if (temp.getAmount() != null) {
                            amount = temp.getAmount();
                        }
                        usable = amount + income;
                        uamountLog.setIncome(income);
                        uamountLog.setUsable(usable);
                        //插入礼包金额记录
                        uamountLogMapper.insert(uamountLog);
                        //修改礼包金额

                        User user = new User();
                        user.setId(userId);
                        user.setLastUpdate(new Date());
                        //会员礼包金额
                        user.setAmount(usable);
                        userMapper.update(user);
                    }
                }
            }
        }
    }

    private void updOrder(Order order) {
        int update = orderMapper.update(order);
        if (update != 1) {
            LOGGER.warn("修改失败，参数：{}", order);
            throw new ServiceException(4102);
        }
    }

    /**
     * 加入消费赠送积分规则
     */
    private void setTodoTask(OrderBO order) {

        double amount = order.getTotalPrice();
        String userId = order.getUserId();
        todoTaskService.doTask(userId, TaskConstant.SYS_TASK_FIRST_CONSUME_CODE);
        //时间暂停1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (amount >= 1000 && amount < 3000) {
            todoTaskService.doTask(userId, TaskConstant.SYS_TASK_CONSUME_BEYOND_1000_CODE);
        }

        if (amount >= 3000 && amount < 5000) {
            todoTaskService.doTask(userId, TaskConstant.SYS_TASK_CONSUME_BEYOND_3000_CODE);
        }
        if (amount >= 5000) {
            todoTaskService.doTask(userId, TaskConstant.SYS_TASK_CONSUME_BEYOND_5000_CODE);
        }
    }

    /**
     * 修改库存
     */
    private void updateStock(OrderBO orderBO, OrderProductBO orderProductBO) {
        //查询产品库存信息
        ProductBO prBO = productRoMapper.selectBOById(orderProductBO.getProductId());
        if (prBO == null) {
            LOGGER.info("商品库存查询失败");
            throw new ServiceException(4102, "商品库存查询失败");
        }
        orderProductBO.setOrderNo(orderBO.getOrderNo());
        //减去Product库存数量
        int num = orderProductBO.getNum();
        int stock = prBO.getStock() - num;
        if (stock < 0) {
            LOGGER.info("库存不足,请联系管理员：{}", stock);
            orderBO.setRemark(orderProductBO.getName() + "库存不足,请联系客服。");
        }
        prBO.setStock(stock);
        Product product = new Product();
        BeanUtils.copyProperties(prBO, product);
        productMapper.update(product);
        //库存表数据处理
        Date date = new Date();
        ProductRepo repo = new ProductRepo();
        repo.setId(Utils.uuid());
        repo.setGoodsId(prBO.getGoodsId());
        repo.setProductId(prBO.getId());
        repo.setOutcome(num);
        repo.setStock(stock);
        repo.setCreateTime(date);
        repo.setLastUpdate(date);
        repo.setRemark(orderBO.getOrderNo());
        repo.setOptionUser(orderBO.getUserId());
        productRepoMapper.insert(repo);
    }

    @Override
    public void selectStock(String tradeNo) {
        LOGGER.info("根据交易流水号判断库存{}", tradeNo);
        List<OrderBO> orderBOList = orderRoMapper.selectByTradeNo(tradeNo);

        for (OrderBO orderBO : orderBOList) {
            OrderProductBO pBO = new OrderProductBO();
            String orderNo = orderBO.getOrderNo();
            pBO.setOrderNo(orderNo);
            List<OrderProductBO> orderProductBOs = orderProductRoMapper.selectByOrderNo(pBO);
            for (OrderProductBO orderProductBO : orderProductBOs) {
                //查询产品库存信息
                if (orderProductBO.getProductId() != null && !"".equals(orderProductBO.getProductId())) {
                    ProductBO prBO = productRoMapper.selectBOById(orderProductBO.getProductId());
                    orderProductBO.setOrderNo(orderBO.getOrderNo());
                    //减去Product库存数量
                    int num = orderProductBO.getNum();
                    int stock = prBO.getStock() - num;
                    if (stock < 0) {
                        LOGGER.info("库存不足,请联系管理员：{}", stock);
                        orderBO.setRemark(orderProductBO.getName() + "库存不足,请联系客服。");
                        throw new ServiceException(4905);
                    }
                }
            }
        }
    }

    /**
     * 购买会员，消息发送
     */
    private void sendMemberMsg(OrderProductBO orderProductBO, Order order, HttpServletRequest request) {
        //2018-03-06
        User user = userMapper.selectOne(order.getUserId());
        String content = RemindConstant.BUYING_MEMBERS_PREFIX.replaceAll("\\{#DATA.VIP\\}", orderProductBO.getName());

        Map<String, String> map = new HashMap<>();
        map.put("first", "亲爱的" + user.getUsername() + "，恭喜您成功升级为 VIP 会员！");
        map.put("remark", "感恩您的参与和支持，谢谢！。");
        map.put("keyword1", user.getVipLevelName());
        map.put("keyword2", orderProductBO.getName());
        map.put("keyword3", DateUtils.dateToStr(new Date()));
        String templateId = "GrA5UnnYg39Rhs2nyDzwoFiYfmZh5sFkNXTZWGGmrkY";

        MessageSendBo messageSendBo = new MessageSendBo();
        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
        messageSendBo.setBusinessId(order.getOrderNo());
        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/member/member_rights.html'>"
                + MessageConstant.VIEW_DETAILS + "</a>");
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);

        List<String> userIds = new ArrayList<>();
        userIds.add(order.getUserId());
        messageSendBo.setUserIds(userIds);

        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    /**
     * 积分充值，消息发送
     */
    private void sendPointsMsg(OrderProductBO orderProductBO, Order order, HttpServletRequest request) {
        User user = userMapper.selectOne(order.getUserId());
        //组装web消息  2018-03-06
        String content = RemindConstant.INTEGRAL_RECHARGE.replaceAll("\\{#DATA.POINT\\}", String.valueOf(user
                .getPoints()));

        //组装微信消息
        Map<String, String> map = new HashMap<>();
        map.put("first", "亲爱的" + user.getUsername() + "，你已兑换成功。");
        map.put("remark", "感谢你的使用。");
        map.put("keyword1", orderProductBO.getName());
        map.put("keyword2", String.valueOf(order.getGiftPoints()));
        String templateId = "mfSWjnagZEzWLYz1Xp8LfQXKLos2fBE7QFoShCwGJkU";

        MessageSendBo messageSendBo = new MessageSendBo();
        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
        messageSendBo.setBusinessId(order.getOrderNo());
        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/pointsExchange/points.php'>"
                + MessageConstant.VIEW_DETAILS + "</a>");
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);

        List<String> userIds = new ArrayList<>();
        userIds.add(order.getUserId());
        messageSendBo.setUserIds(userIds);

        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    /**
     * 插入交易流水记录
     */
    private void insertTradeLog(Order order, String tradeNo) {
        //加入交易日志
        TradeLog tradeLog = new TradeLog();
        tradeLog.setTradeNo(tradeNo);
        tradeLog.setAliTrandeNo(tradeNo);
        tradeLog.setTradeStatus("2");
        tradeLog.setTradeType("1");
        tradeLog.setAmount(order.getTotalPrice());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        tradeLog.setTradeTime(now);
        tradeLog.setCreateTime(now);
        tradeLog.setLastUpdate(now);
        tradeLog.setPayMethod(order.getPayMethod());
        tradeLogMapper.update(tradeLog);
    }

    private void insertVipLog(String orderNo, String userId, String memberLevel) {
        VipLogBO vipLogBO = new VipLogBO();
        vipLogBO.setLevelId(memberLevel);
        vipLogBO.setSource(orderNo);
        vipLogBO.setUserId(userId);
        vipLogService.insert(vipLogBO,1);
    }

    /**
     * 购买CSKT赠送的积分
     */
    private void insertGivePoints(OrderBO orderBO, Integer points) {
        if (orderBO != null && points != null && points > 0) {
            //如果积分规则为空则返回
            PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_ORDER_CODE);
            if (pointsRuleBO == null) {
                return;
            }
            PointsLogBO pointsLog = new PointsLogBO();
            pointsLog.setUserId(orderBO.getUserId());
            pointsLog.setRuleId(pointsRuleBO.getId());
            pointsLog.setId(Utils.uuid());
            pointsLog.setIncome(points);
            pointsLog.setRemark("购买赠送积分 - 订单号：" + orderBO.getOrderNo());
            pointsLog.setLogType("ORDER_INCOME");
            pointsLogService.insert(pointsLog);
        }
    }

    /**
     * 插入可获得的积分
     */
    private void insertPoints(OrderBO orderBO, Integer points) {
        if (orderBO != null && points != null && points > 0) {
            //如果积分规则为空则返回
            PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_ORDER_CODE);
            if (pointsRuleBO == null) {
                return;
            }

            PointsLogBO pointsLog = new PointsLogBO();
            pointsLog.setUserId(orderBO.getUserId());
            pointsLog.setRuleId(pointsRuleBO.getId());
            pointsLog.setId(Utils.uuid());
            pointsLog.setIncome(points);
            pointsLog.setRemark("用户下单 - 订单号：" + orderBO.getOrderNo());
            pointsLog.setLogType("ORDER_INCOME");
            pointsLogService.insertByConsume(pointsLog, orderBO.getNowVipLevel());
        }
    }

    /**
     * 插入所扣去的积分
     */
    private void insertDeductPoints(OrderBO orderBO) {
        //如果积分规则为空则返回
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_EXCHANGE_CODE);
        if (pointsRuleBO == null) {
            return;
        }
        PointsLogBO pointsLog = new PointsLogBO();
        pointsLog.setUserId(orderBO.getUserId());
        pointsLog.setRuleId(pointsRuleBO.getId());
        pointsLog.setOutgo(orderBO.getTotalPrice().intValue());
        pointsLog.setLogType("POINTS_RECHARGE");
        pointsLog.setRemark("积分兑换 - 订单号：" + orderBO.getOrderNo());
        pointsLogService.insert(pointsLog);
    }

    @Override
    public List<OrderListBO> selectExprotOrder(Order order) {
        List<OrderBO> orderBOList = orderRoMapper.selectExprotOrder(order);
        List<OrderBO> orderDataList = new ArrayList<>(orderBOList);
        List<OrderListBO> orderListBOList = new ArrayList<>();

        for (OrderBO bo : orderBOList) {
            OrderListBO orderListBO = new OrderListBO();
            orderListBO.setOrderNo(bo.getOrderNo());
            //收货地址
            String address = bo.getShippingAddress();
            String phone = bo.getContactNumber();
            String consignee = bo.getConsignee();
            orderListBO.setAddress(address);
            orderListBO.setPhone(phone);
            orderListBO.setFullName(bo.getConsignee());
            boolean isAlike = false;
            StringBuilder goodsName = new StringBuilder();
            goodsName.append("");
            int num = 0;
            if (address != null && !"".equals(address) && phone != null && !"".equals(phone)) {
                for (OrderBO data : orderDataList) {
                    String addressBuffer = data.getShippingAddress();
                    String phoneTemp = data.getContactNumber();
                    //有地址和电话相同的
                    if (address.equals(addressBuffer) && phone.equals(phoneTemp)) {
                        isAlike = true;
                        //寄托货物信息合并
                        if (data.getOrderProductBOList() != null) {
                            List<OrderProductBO> orderProductBOList = data.getOrderProductBOList();
                            for (OrderProductBO orderProductBO : orderProductBOList) {
                                goodsName.append(orderProductBO.getName());
                                goodsName.append(" ");
                                goodsName.append(orderProductBO.getSpecInfo());
                            }
                            goodsName.append(";");
                        }
                        num++;
                    }
                }
            }
            //没有地址和电话相同的
            if (!isAlike) {
                //寄托货物
                if (bo.getOrderProductBOList() != null) {
                    List<OrderProductBO> orderProductBOList = bo.getOrderProductBOList();
                    for (OrderProductBO orderProductBO : orderProductBOList) {
                        goodsName.append(orderProductBO.getName());
                        goodsName.append(" ").append(orderProductBO.getSpecInfo());
                    }
                    goodsName.append(";");
                }
            }
            orderListBO.setFullName(consignee);
            orderListBO.setGoodsName(goodsName.toString());
            //寄托数量
            orderListBO.setNum(num);
            //支付方式
            orderListBO.setPayMethod(bo.getPayMethod());

            orderListBOList.add(orderListBO);
        }
        return orderListBOList;
    }

    @Override
    public void selectImportOrder(List<OrderBO> orderBOList, String expressCompId, HttpServletRequest request) {
        for (OrderBO bo : orderBOList) {
            Order data = orderRoMapper.selectByPrimaryKey(bo.getOrderNo());
            if (data == null) {
                LOGGER.warn("订单数据不存在：{}", bo);
                throw new ServiceException(4102, "订单数据不存在");
            }
            if (data.getIsShipping() == 2) {
                LOGGER.warn("该订单不需要寄送：{}", bo);
                throw new ServiceException(4102, bo.getOrderNo() + "该订单不需要寄送");
            }
            if (bo.getExpressNo() != null && CharUtil.isChinese(bo.getExpressNo())) {
                LOGGER.warn("运单号不能存在中文：{}", bo);
                throw new ServiceException(4102, bo.getExpressNo() + "运单号不能存在中文");
            }
            Order order = new Order();
            BeanUtils.copyProperties(bo, order);
            order.setOrderStatus("5");
            order.setExpressCompId(expressCompId);
            order.setLastUpdate(new Date());
            //修改订单状态
            updOrder(order);
            insertOrderLog(data.getUserId(), order.getOrderNo(), "5", "管理员已发货", "0");

            //组装web消息
            ExpressComp expressComp = expressCompRoMapper.selectByPrimaryKey(expressCompId);
            if (expressComp == null) {
                LOGGER.warn("物流公司查询失败：{}", order.getExpressCompId());
                throw new ServiceException(4102, "物流公司查询失败");
            }
            String content = RemindConstant.DELIVER_GOODS_PREFIX + order.getOrderNo() + RemindConstant
                    .DELIVER_GOODS_YDH + expressComp.getCompName() + "+" + order.getExpressNo() + RemindConstant.SUFFIX;

            //发送微信消息
            Map<String, String> map = new HashMap<>();
            map.put("first", "你有新的订单提醒：");
            map.put("remark", "详情请登录财税平台查看。");
            map.put("keyword1", data.getOrderNo());
            map.put("keyword2", Utils.getAdminInfo().getNickname());
            map.put("keyword3", DateUtils.dateToStr(new Date()));
            String templateId = "mfSWjnagZEzWLYz1Xp8LfQXKLos2fBE7QFoShCwGJkU";

            MessageSendBo messageSendBo = new MessageSendBo();
            messageSendBo.setType(MessageConstant.SYS_MESSAGE);
            messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
            messageSendBo.setBusinessId(order.getOrderNo());
            messageSendBo.setWebMsg(content);
            messageSendBo.setPhoneMsg(content);
            messageSendBo.setTemplateid(templateId);
            messageSendBo.setDataList(map);
            messageSendBo.setWxNoChargeVip(true);
            messageSendBo.setMoblieNoChargeVip(true);

            List<String> userIds = new ArrayList<>();
            userIds.add(order.getUserId());
            messageSendBo.setUserIds(userIds);

            msgSendV2Service.sendMsgV2(messageSendBo);
        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void sendOrder(OrderOperationBO orderOperationBO, HttpServletRequest request) {
        //订单状态，2：待支付，3：支付中，4：待发货，5：待收货，6：已完成，7：已取消
        orderOperationBO.setOrderStatus("5");
        Order order = new Order();
        BeanUtils.copyProperties(orderOperationBO, order);
        //修改订单状态
        updOrder(order);
        insertOrderLog(Utils.getAdminId(), order.getOrderNo(), "5", orderOperationBO.getRemark(), "0");

        //发送普通业务消息
        ExpressComp expressComp = null;
        if (order.getExpressCompId() != null && !"".equals(order.getExpressCompId())) {
            expressComp = expressCompRoMapper.selectByPrimaryKey(order.getExpressCompId());
        }
        MessageSendBo messageSendBo = new MessageSendBo();
        String content;
        if (expressComp != null) {
            content = RemindConstant.DELIVER_GOODS_PREFIX + expressComp.getCompName() + "+" + order.getExpressNo() +
                    RemindConstant.SUFFIX;
        } else {
            content = RemindConstant.DELIVER_GOODS_PREFIX_NO + order.getOrderNo() + RemindConstant.SUFFIX;
            messageSendBo.setSkipUrl
                    ("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderDetail/" + order.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
        }

        Map<String, String> map = new HashMap<>();
        map.put("first", "你有新的订单提醒：");
        map.put("remark", "详情请登录财税平台查看。");
        map.put("keyword1", order.getOrderNo());
        map.put("keyword2", Utils.getAdminInfo().getNickname());
        map.put("keyword3", DateUtils.dateToStr(new Date()));
        String templateId = "mfSWjnagZEzWLYz1Xp8LfQXKLos2fBE7QFoShCwGJkU";

        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
        messageSendBo.setBusinessId(order.getOrderNo());
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);
        messageSendBo.setWxNoChargeVip(true);
        messageSendBo.setMoblieNoChargeVip(true);

        List<String> userIds = new ArrayList<>();
        userIds.add(order.getUserId());
        messageSendBo.setUserIds(userIds);

        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void invalidOrder(OrderOperationBO orderOperationBO) {
        orderOperationBO.setOrderStatus("6");
        Order order = new Order();
        BeanUtils.copyProperties(orderOperationBO, order);
        //修改订单状态
        updOrder(order);
    }

    @Override
    public void automaticReceipt() {
        Date date = DateUtils.getAddDate(Constant.ORDER_RECEIPT_DAYS);
        //查询15天之前未确认的订单
        List<Order> orderList = orderRoMapper.selectReceiptOrderByDate(date);
        for (Order order : orderList) {
            order.setOrderStatus("6");
            orderMapper.update(order);
            insertOrderLog("", order.getOrderNo(), "6", "系统自动确认收货", "0");
        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void automaticCancel() {
        Date date = DateUtils.getAddTime(Constant.ORDER_CANCEL_TIME);
        //查询两个小时未支付的订单，自动取消
        List<Order> orderList = orderRoMapper.selectCancelOrderByDate(date);
        for (Order order : orderList) {
            order.setOrderStatus("7");
            orderMapper.update(order);
            LOGGER.info("获取优惠劵信息");
            Map<String, Object> map = new HashMap<>();
            map.put("orderNo", order.getOrderNo());
            map.put("userId", order.getUserId());
            CouponUser couponUser = couponService.selectCouponUser(map);
            if (couponUser != null) {
                OrderProductBO pBO = new OrderProductBO();
                pBO.setOrderNo(order.getOrderNo());
                List<OrderProductBO> orderProductBOs = orderProductRoMapper.selectByOrderNo(pBO);
                for (OrderProductBO orderProductBO : orderProductBOs) {
                    CouponOrderBO couponOrderBO = new CouponOrderBO();
                    couponOrderBO.setUseCouponId(couponUser.getCouponId());
                    couponOrderBO.setUserId(order.getUserId());
                    couponOrderBO.setOrderNo(order.getOrderNo());
                    couponOrderBO.setCategoryId(orderProductBO.getCategoryId());
                    couponOrderBO.setAmount(order.getTotalPrice());
                    couponOrderBO.setOperation("0");
                    //优惠劵设置已领取
                    couponOrderBO.setStatus("1");
                    LOGGER.info("优惠劵取消");
                    couponService.userUseCoupon(couponOrderBO);
                }
            }
            insertOrderLog("", order.getOrderNo(), "7", "系统自动取消订单", "0");
        }
    }

    @Override
    public OrderBO selectOrderByGoodsIdAndUserId(Map<String, Object> map) {
        return orderRoMapper.selectOrderByGoodsIdAndUserId(map);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public OrderUpdateBO updateOrder(OrderUpdateBO orderUpdateBO) {
        Order order = new Order();
        order.setOrderNo(orderUpdateBO.getOrderNo());
        order.setUserId(orderUpdateBO.getUserId());
        order.setRecommendUser(orderUpdateBO.getRecommendUser());
        //修改订单状态
        updOrder(order);
        return orderUpdateBO;
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void confirmOrder(Order order) {
        order.setOrderStatus("6");
        //修改订单状态
        updOrder(order);
        insertOrderLog(order.getUserId(), order.getOrderNo(), "6", "用户确认收货", "0");
    }

    @Override
    public List<OrderBO> selectOrderListByInvoice(OrderBO order, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return orderRoMapper.selectOrderListByInvoice(order);
    }

    @Override
    public List<OrderBO> selectCurriculumOrderList(OrderBO orderBO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return orderRoMapper.selectCurriculumOrderList(orderBO);
    }

    @Override
    public Integer selectTodoListCount() {
        return orderRoMapper.selectTodoListCount();
    }

    @Override
    public OrderStatBO orderStat() {
        return orderRoMapper.orderStat();
    }

    @Override
    public OrderBO selectByOrderNoAdmin(String orderNo) {
        OrderBO orderBO = orderRoMapper.selectByOrderNoAdmin(orderNo);
        List<OrderGiftBO> orderGiftBOList = orderGiftRoMapper.selectByOrderNo(orderNo);
        if (orderGiftBOList != null && orderGiftBOList.size() > 0) {
            orderBO.setOrderGiftBOList(orderGiftBOList);
        }
        return orderBO;
    }

    @Override
    public OrderTradeBO selectOrderTrade(String tradeNo) {

        return orderRoMapper.selectOrderTrade(tradeNo);
    }

    @Override
    public void adminDeleteOrder(OrderBO orderBO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderBO, order);
        //查询订单状态
        OrderBO bo = orderRoMapper.selectOrderByAdmin(order);
        if (bo == null) {
            LOGGER.info("订单信息不存在：{}", orderBO);
            throw new ServiceException(4134);
        }
        //订单状态，2：待付款，3：付款中，4：付款成功，5：已发货，6：已完成，7：已结束，8：付款失败，9：已退单，11：已删除
        if ("11".equals(bo.getOrderStatus())) {
            int del = orderMapper.delete(order.getOrderNo());
            if (del != 1) {
                LOGGER.info("删除失败：{}", orderBO);
                throw new ServiceException(4103);
            }
            //订单删除成功之后，删除订单与产品对应关系

            List<OrderProductBO> orderProductBOs = bo.getOrderProductBOList();
            if (orderProductBOs == null) {
                LOGGER.info("产品信息错误：{}", orderBO);
                throw new ServiceException(4166);
            } else {
                for (OrderProductBO orderProductBO : orderProductBOs) {
                    int opDel = orderProductMapper.delete(orderProductBO.getOrderNo());
                    if (opDel != 1) {
                        LOGGER.info("删除订单与产品关系信息失败：{}", orderBO);
                        throw new ServiceException(4168);
                    }
                    orderProductspecMapper.deleteByOrderNo(orderProductBO.getOrderNo());
                }
            }
            insertOrderLog(orderBO.getUserId(), orderBO.getOrderNo(), "10", "管理员删除用户订单", "0");
        } else {
            LOGGER.info("订单只有在前台用户删除后，管理员才可以进行删除：{}", orderBO);
            throw new ServiceException(4140);
        }
    }


    @Override
    public List<OrderStatisBO> statisOrderByStatus(Map<String, Object> map) {
        return orderRoMapper.statisOrderByStatus(map);
    }

    @Override
    public List<OrderStatisBO> statisOrderByMonth(Map<String, Object> map) {
        return orderRoMapper.statisOrderByMonth(map);
    }

    @Transactional("db1TxManager")
    @Override
    public void updateOrderReturn(Map<String, Object> map, HttpServletRequest httpServletRequest) {
        //OrderProduct orderProduct = orderProductRoMapper.selectByOrderNoAndGoodsId(map);
        String orderNo = (String) map.get("orderNo");
        String goodsId = (String) map.get("goodsId");
        VipLogBO vipLogBO = (VipLogBO) map.get("vipLogBO");
        OrderBO orderBO = orderRoMapper.selectById(orderNo);
        if (orderBO != null) {
            List<OrderProductBO> list = orderBO.getOrderProductBOList();
            for (OrderProductBO orderProductBO : list) {
                if (orderProductBO != null) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrderNo(orderNo);
                    orderProduct.setGoodsId(goodsId);
                    orderProduct.setIsReturn("0");
                    int update = orderProductMapper.updateByOrderNo(orderProduct);
                    if (update != 1) {
                        LOGGER.info("修改异常：{}", orderProductBO);
                        throw new ServiceException(4102);
                    }

                    refund(orderBO, orderProductBO, vipLogBO, httpServletRequest);
                }
            }
        }
    }

    @Override
    public OrderBO selectOrderDetail(String orderNo) {
        OrderBO orderBO = orderRoMapper.selectOrderDetail(orderNo);
        List<OrderGiftBO> orderGiftBOList = orderGiftRoMapper.selectByOrderNo(orderNo);
        if (orderGiftBOList != null && orderGiftBOList.size() > 0) {
            orderBO.setOrderGiftBOList(orderGiftBOList);
        }
        return orderBO;
    }

    @Override
    public OrderBO selectWebByOrderNo(String orderNo) {
        OrderBO orderBO = orderRoMapper.selectWebByOrderNo(orderNo);
        List<OrderGiftBO> orderGiftBOList = orderGiftRoMapper.selectByOrderNo(orderNo);
        if (orderGiftBOList != null && orderGiftBOList.size() > 0) {
            orderBO.setOrderGiftBOList(orderGiftBOList);
        }
        return orderBO;
    }

    @Override
    public OrderBO selectWeChatByOrderNo(String orderNo) {
        return orderRoMapper.selectWeChatByOrderNo(orderNo);
    }

    @Override
    public List<OrderBO> selectMyOrderList(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return orderRoMapper.selectMyOrderList(map);
    }

    @Override
    public Double selectMyOrderMoney(Map<String, Object> map) {
        return orderRoMapper.selectMyOrderMoney(map);
    }

    /**
     * 发票作废
     */
//    public void invoiceCancel(OrderBO orderBO) {
//        // 发票作废
//        InvoiceBO invoiceBO = orderBO.getInvoiceBO();
//        if (invoiceBO != null) {
//
//            DzfpGetReq req = new DzfpGetReq();
//            List<InvoiceXm> dataList = new ArrayList<>();
//            if ("1".equals(invoiceBO.getProperty())) { // 纸质发票
//                LOGGER.info("该会员订购已开具纸质发票，不能退订：{}");
//                throw new ServiceException(4102, "该会员订购已开具纸质发票，不能退订");
//            } else if ("2".equals(invoiceBO.getProperty())) { // 电子发票
//                if("1".equals(invoiceBO.getStatus())){
//                    LOGGER.info("发票申请还在待审批状态，不能进行退单");
//                    throw new ServiceException(4102, "发票申请还在待审批状态，不能进行退单");
//                }
//                if ("1".equals(invoiceBO.getName())) { // 个人发票
//                    req.setGmf_mc("个人");
//                } else {
//                    req.setGmf_mc(invoiceBO.getNsrmc());
//                    req.setGmf_nsrsbh(invoiceBO.getNsrsbh());
//                    req.setGmf_dzdh(invoiceBO.getAddress());
//                    req.setGmf_yhzh(invoiceBO.getBank());
//                    req.setGmf_sjh(invoiceBO.getPhone());
//                }
//                req.setFpqqlsh(DateUtils.getFPQQLSH());
//                req.setKplx("1");
//                req.setZsfs("0");
//                req.setKpr(Utils.getAdminInfo().getNickname());
//                req.setHylx("0");
//                req.setYfp_dm(invoiceBO.getInvoiceCode());
//                req.setYfp_hm(invoiceBO.getInvoiceNo());
//                req.setGmf_dzyx(invoiceBO.getEmail());
//
//                InvoiceXm xm = new InvoiceXm();
//                xm.setSpbm("3040201000000000000");
//                xm.setFphxz("0");
//                xm.setXmmc(selectFieldValue("invoicecontent", invoiceBO.getContent()));
//                xm.setXmsl(-1.00);
//                xm.setTotalAmt(-invoiceBO.getAmount());
//                dataList.add(xm);
//
//                StringBuilder buffer = new StringBuilder();
//                List<OrderProductBO> productBOs = orderBO.getOrderProductBOList();
//                if (productBOs != null && productBOs.size() > 0) {
//                    for (OrderProductBO pBO : productBOs) {
//                        if (pBO.getTradingChannels() != null && "CSKT".equals(pBO.getTradingChannels())) {
//                            buffer.append("培训课程,");
//                        } else {
//                            buffer.append(pBO.getName());
//                            buffer.append(",");
//                        }
//                    }
//                }
//                if (buffer.length() > 0) {
//                    req.setBz(buffer.deleteCharAt(buffer.length() - 1).toString());
//                }
//                req.setInvoiceXms(dataList);
//            }
//            Einvocie einvocie = null;
//            try {
//                einvocie = (Einvocie) DzfpClient.doSender("DFXJ1001", req.tosendXml(), Einvocie.class);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            if (einvocie != null) {
//
//                if ("0000".equals(einvocie.getReturnCode())) { // 更新作废状态
//                    InvoiceDetail id = invoiceDetailRoMapper.selectByInvoiceNo(invoiceBO.getInvoiceNo());
////                    if (id == null) {
////                        throw new ServiceException(4102, "查找发票详情错误");
////                    }
//                    if (id != null) {
//                        id.setStatus("3");
//                        id.setLastUpdate(new Date());
//                        id.setSpUrl(einvocie.getSP_URL());
//                        id.setPdfUrl(einvocie.getPDF_URL());
//                        invoiceDetailMapper.update(id);
//                    }
//                } else {
//                    throw new ServiceException(einvocie.getReturnCode(), einvocie.getReturnMessage());
//                }
//            }
//        }
//    }

    /**
     * 退积分退金额
     */
    @Transactional("db1TxManager")
    public void refund(OrderBO orderBO, OrderProductBO orderProductBO, VipLogBO vipLogBO, HttpServletRequest httpServletRequest) {
        //成交价格
        double dealPrice = orderBO.getTotalPrice();
        //判断是RMB、积分
        if ("RMB".equals(orderBO.getTradeMethod())) {
            // 查询交易日志中支付成功的订单
            List<Trade> tradeList = tradeRoMapper.selectList(orderBO.getOrderNo());
            if (tradeList == null || tradeList.size() == 0) {
                LOGGER.info("交易记录无法找到：{}", tradeList);
                throw new ServiceException(4102, "交易记录无法找到");
            }
            if (tradeList.size() > 1) {
                LOGGER.info("无法重复提交退单：{}", tradeList);
                throw new ServiceException(4102, "无法重复提交退单");
            }
            if ("ALIPAY".equals(orderBO.getPayMethod())) {
                TradeLog log = new TradeLog();
                log.setTradeNo(tradeList.get(0).getTradeNo());
                log.setTradeType("1");
                log.setPayMethod("ALIPAY");

                TradeLog tradeLog = tradeLogRoMapper.selectTradeLog(log);
                if (tradeLog == null) {
                    LOGGER.info("交易记录无法找到：{}", tradeList);
                    throw new ServiceException(4102, "交易流水记录无法找到");
                }
                if (tradeLog.getAmount() >= dealPrice) {
                    AliRefund refund = new AliRefund();
                    refund.setOut_trade_no(tradeLog.getTradeNo());
                    refund.setTrade_no(tradeLog.getAliTrandeNo());
                    refund.setRefund_amount(String.valueOf(dealPrice));
                    refund.setRefund_reason("会员充值退款");
                    String out_request_no = log.getTradeNo();
                    refund.setOut_request_no(out_request_no);

                    try {
                        //扣除订单获得的积分
                        User user = optUserAndOrder(orderBO, orderProductBO, vipLogBO);

                        AlipayClient alipayClient = AliPayConfig.getInstance();
                        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
                        request.setBizContent(AliPayConfig.toCharsetJsonStr(refund));
                        AlipayTradeRefundResponse response = alipayClient.execute(request);
                        LOGGER.info("支付宝退款支付宝返回信息{}", JSON.toJSONString(response));
                        if (response.isSuccess()) {

                            JSONObject object = JSON.parseObject(response.getBody());
                            RefundRes refundRes = JSON.parseObject(object.getString("alipay_trade_refund_response"), RefundRes.class);

                            LOGGER.info("支付宝退款成功,插入退款流水记录");
                            String tradeNo = DateUtils.getJYLSH();
                            insertTrade(orderBO, tradeNo,refundRes.getTrade_no(),refundRes.getRefund_fee(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(refundRes.getGmt_refund_pay()),"ALIPAY");

                            sendReturnMessage(orderBO, httpServletRequest, refundRes.getRefund_fee(), user);
                        } else {
                            LOGGER.error("支付宝退款失败：");
                            throw new ServiceException(9999, response.getSubMsg());

                        }
                    } catch (ServiceException e) {
                        LOGGER.error("支付宝退款失败：", e);
                        throw new ServiceException(9999, e.getBodyStatus().getMessage());
                    } catch (Exception e) {
                        LOGGER.error("支付宝退款失败：", e);
                        throw new ServiceException(9999, e.getMessage());
                    }
                } else {
                    throw new ServiceException(7146);
                }

            } else if ("WEIXIN".equals(orderBO.getPayMethod())) {
                TradeLog log = new TradeLog();
                log.setTradeNo(tradeList.get(0).getTradeNo());
                log.setTradeStatus("1");
                log.setPayMethod("WEIXIN");
                TradeLog tradeLog = tradeLogRoMapper.selectTradeLog(log);
                if (tradeLog == null) {
                    LOGGER.info("交易记录无法找到：{}", tradeList);
                    throw new ServiceException(4102, "交易流水记录无法找到");
                }
                if (tradeLog.getAmount() >= dealPrice) {
                    WxRefund wxRefund = new WxRefund();
                    wxRefund.setTransaction_id(tradeLog.getAliTrandeNo());
                    wxRefund.setOut_trade_no(tradeLog.getTradeNo());
                    //商户退款单号
                    String tradeNo = DateUtils.getJYLSH();
                    wxRefund.setOut_refund_no(tradeNo);
                    int price = (int)(dealPrice*100);
                    wxRefund.setRefund_fee(String.valueOf(price));
                    wxRefund.setTotal_fee(String.valueOf(price));
                    wxRefund.setNotify_url(SpringCtxHolder.getProperty("abc.mch_refund"));

                    User user = optUserAndOrder(orderBO, orderProductBO, vipLogBO);


                    //微信退款
                    wxRefund.setAppid(SpringCtxHolder.getProperty("abc.appid")).setMch_id(SpringCtxHolder.getProperty("abc.mch_id"))
                            .setNonce_str(SignUtil.getRandomString(30)).setSign(SignUtil.signKey(wxRefund));

                    WxRefundRsp wxrefundrsp = WxMchConnectFactory.post(WechatUrl.WXREFUND, null, wxRefund, WxRefundRsp.class);
                    LOGGER.info("微信退款返回信息{}", JSON.toJSONString(wxrefundrsp));
                    if ("SUCCESS".equals(wxrefundrsp.getReturn_code())) {
                        if ("SUCCESS".equals(wxrefundrsp.getResult_code())) {
                            LOGGER.info("微信退款成功,插入退款流水记录");
                            insertTrade(orderBO, tradeNo,wxrefundrsp.getRefund_id(),String.valueOf(Double.parseDouble(wxrefundrsp.getTotal_fee()) / 100),new Date(),"WEIXIN");

                            sendReturnMessage(orderBO, httpServletRequest, String.valueOf(Double.parseDouble(wxrefundrsp.getTotal_fee()) / 100), user);
                        }else{
                            LOGGER.error("微信退款失败：", wxrefundrsp.getErr_code_des());
                            throw new ServiceException(9999, wxrefundrsp.getReturn_msg());
                        }
                    }else {
                        LOGGER.error("微信退款失败：", wxrefundrsp.getReturn_msg());
                        throw new ServiceException(9999, wxrefundrsp.getReturn_msg());
                    }

                }
            }else {
                throw new ServiceException(4956);
            }
        } else if ("POINTS".equals(orderBO.getTradeMethod())) {

            // 插入订单日志-已退款
            String remark;
            if (vipLogBO.getSource() != null) {
                remark = "已完成退款。退款积分为：" + dealPrice + "；回退到该订单：" + vipLogBO.getSource();
            } else {
                remark = "已完成退款。退款积分为：" + dealPrice;
            }
            insertLog(orderBO.getOrderNo(), "9", Utils.getAdminId(), remark, "0");

            //将订单状态改成已结束
            orderBO.setOrderStatus("9");
            Order order = new Order();
            BeanUtils.copyProperties(orderBO, order);
            orderMapper.update(order);

            //修改用户信息
            User user = userMapper.selectOne(orderBO.getUserId());
            updateVipInfo(vipLogBO, user, orderBO.getOrderNo(), orderProductBO.getSpecInfo());

            LOGGER.info("积分退款成功,插入退款流水记录");
            insertTrade(orderBO, dealPrice);
            //退积分
            insertReturnPointsLog(order, dealPrice, 0);
        } else {
            throw new ServiceException(4957);
        }
    }

    public User optUserAndOrder(OrderBO orderBO, OrderProductBO orderProductBO, VipLogBO vipLogBO) {
        //扣除订单获得的积分
        if(orderBO.getGiftPoints() != null && orderBO.getGiftPoints() != 0){
            LOGGER.info("扣除订单获得的积分");
            Order order = new Order();
            BeanUtils.copyProperties(orderBO, order);
            insertReturnPoints(order, 0d, order.getGiftPoints());
        }

        // 插入订单日志-已完成
        String remark;
        if (vipLogBO.getSource() != null) {
            remark = "已完成退款。回退到该订单：" + vipLogBO.getSource();
        } else {
            remark = "已完成退款。";
        }
        insertLog(orderBO.getOrderNo(), "9", Utils.getAdminId(), remark, "0");

        //将订单状态改成已退单
        orderBO.setOrderStatus("9");
        Order order = new Order();
        BeanUtils.copyProperties(orderBO, order);
        orderMapper.update(order);
        User user = userMapper.selectOne(orderBO.getUserId());
        updateVipInfo(vipLogBO, user, orderBO.getOrderNo(), orderProductBO.getSpecInfo());
        return user;
    }

    /**
     * 插入支付宝交易记录
     *
     * @throws ParseException
     */
    public void insertTrade(OrderBO orderBO,String tradeNo, String aliTrandeNo,String refundFee,Date gmtRefundPay,String payMethod){
        Trade trade = new Trade();
        trade.setOrderNo(orderBO.getOrderNo());
        trade.setTradeNo(tradeNo);
        Date date = new Date();
        trade.setCreateTime(date);
        trade.setLastUpdate(date);
        tradeMapper.insert(trade);

        TradeLog tradeLog = new TradeLog();
        tradeLog.setTradeNo(tradeNo);
        tradeLog.setAliTrandeNo(aliTrandeNo);
        tradeLog.setTradeStatus("2");
        tradeLog.setTradeType("2");
        tradeLog.setAmount(Double.parseDouble(refundFee));
        tradeLog.setTradeTime(gmtRefundPay);
        tradeLog.setCreateTime(date);
        tradeLog.setLastUpdate(date);
        tradeLog.setPayMethod(payMethod);
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    /**
     * 插入积分交易记录
     */
    public void insertTrade(OrderBO orderBO, double dealPrice) {
        String tradeNo = DateUtils.getJYLSH();
        Trade trade = new Trade();
        trade.setOrderNo(orderBO.getOrderNo());
        trade.setTradeNo(tradeNo);
        Date date = new Date();
        trade.setCreateTime(date);
        trade.setLastUpdate(date);
        tradeMapper.insert(trade);

        TradeLog tradeLog = new TradeLog();
        tradeLog.setTradeNo(tradeNo);
        tradeLog.setAliTrandeNo(orderBO.getOrderNo());
        tradeLog.setTradeStatus("2");
        tradeLog.setTradeType("2");
        tradeLog.setAmount(dealPrice);
        tradeLog.setCreateTime(date);
        tradeLog.setLastUpdate(date);
        tradeLog.setTradeTime(date);
        tradeLog.setPayMethod("POINTS");
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    /**
     * 设置会员礼包金额，修改用户信息
     */
    public void updateVipInfo(VipLogBO vipLogBO, User user, String orderNo, String vipLevel) {
        LOGGER.info("OrderServiceImpl.updateVipInfo------Start");
        //查询会员礼包业务
        VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
        obj.setLevelId(vipLevel.trim().toUpperCase());
        obj.setPrivilegeId(MessageConstant.HYLB_CODE);

        //礼包扣除金额
        double usable;
        //查看会员礼包是否启用
        VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);
        if (findObj != null && findObj.getStatus()) {
            UamountLog uamountLog = new UamountLog();
            uamountLog.setId(Utils.uuid());
            uamountLog.setBusinessId(MessageConstant.HYLB_CODE);
            uamountLog.setUserId(user.getId());
            uamountLog.setCreateTime(new Date());
            uamountLog.setRemark("会员退订，扣除礼包金额；会员商品订单号：" + orderNo);
            //赠送金额
            double income = Double.parseDouble(findObj.getVal1());
            double amount = 0;
            if (user.getAmount() != null) {
                amount = user.getAmount();
            }
            usable = amount - income;
            if (usable < 0) {
                throw new ServiceException(4635, "该用户礼包金额不足，不能退单");
            }
            uamountLog.setOutgo(income);
            uamountLog.setUsable(usable);
            //插入礼包金额记录
            uamountLogMapper.insert(uamountLog);
            user.setAmount(usable);
        }
        //更新会员日志
        vipLogBO.setSource("会员退订");
        vipLogService.insert(vipLogBO,2);
        //修改用户信息
        user.setVipLevel(vipLogBO.getLevelId());
        user.setVipExpireDate(vipLogBO.getVipExpireDate());
        userMapper.update(user);
        LOGGER.info("OrderServiceImpl.updateVipInfo------End");
    }

    public void sendReturnMessage(OrderBO orderBO, HttpServletRequest httpServletRequest, String refundFee, User user) {
        String content = RemindConstant.REFUND_PREFIX + refundFee + RemindConstant.REFUND_SUFFIX + orderBO.getOrderNo();
        Map<String, String> map = new HashMap<>();
        map.put("first", "您好，欢迎使用财税平台");
        map.put("remark", "感谢使用财税平台，祝您生活愉快！");
        map.put("keyword1", content);
        map.put("keyword2", refundFee);
        map.put("keyword3", DateUtils.dateToStr(new Date()));
        String templateId = "NkWLcHrxI0it-LZm9yuFinPpSVJFtbUCDxyvxXSKsaM";

        MessageSendBo messageSendBo = new MessageSendBo();
        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
        messageSendBo.setBusinessId(orderBO.getOrderNo());
        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderDetail/" + orderBO.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);

        List<String> userIds = new ArrayList<>();
        userIds.add(orderBO.getUserId());
        messageSendBo.setUserIds(userIds);

        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    private void insertLog(String orderNo, String status, String userId, String remark, String logType) {
        // 插入订单日志
        OrderLog ol = new OrderLog.Builder()
                .id(Utils.uuid())
                .orderNo(orderNo)
                .action(selectFieldValue("orderStatus", status))
                .createTime(new Timestamp(System.currentTimeMillis()))
                .createUser(userId)
                .remark(remark)
                .logType(logType)
                .build();
        orderLogMapper.insert(ol);
    }

    private String selectFieldValue(String fieldValue, String value) {
        Dict dict = new Dict();
        dict.setDictId(fieldValue);
        dict.setFieldValue(value);
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldKey() : "";
    }

    /**
     * 插入积分日志-用户积分购买
     */
    private void insertReturnPointsLog(Order orderBO, Double amount, int outgo) {
        //如果积分规则为空则返回
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_ORDER_RETURN_CODE);
        if (pointsRuleBO == null) {
            return;
        }
        PointsLogBO pointsLog = new PointsLogBO();
        pointsLog.setUserId(orderBO.getUserId());
        pointsLog.setRuleId(pointsRuleBO.getId());
        pointsLog.setId(Utils.uuid());
        //成交总积分 - 赠送积分
        pointsLog.setIncome(amount.intValue());
        pointsLog.setOutgo(outgo);
        pointsLog.setRemark("用户退单- 订单号：" + orderBO.getOrderNo());
        pointsLog.setLogType("ORDER_EXCHANGE");
        pointsLogService.insert(pointsLog);
    }

    /**
     * * 插入积分日志-用金钱购买
     *
     * @param orderBO 订单对象
     * @param amount  收入
     * @param outgo   支出
     */
    private void insertReturnPoints(Order orderBO, Double amount, int outgo) {
        //如果积分规则为空则返回
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_ORDER_RETURN_CODE);
        if (pointsRuleBO == null) {
            return;
        }
        PointsLogBO pointsLog = new PointsLogBO();
        pointsLog.setUserId(orderBO.getUserId());
        pointsLog.setRuleId(pointsRuleBO.getId());
        pointsLog.setId(Utils.uuid());
        //成交总积分 - 赠送积分
        pointsLog.setIncome(amount.intValue());
        pointsLog.setOutgo(outgo);
        pointsLog.setRemark("用户退单- 订单号：" + orderBO.getOrderNo());
        pointsLog.setLogType("ORDER_EXCHANGE");
        pointsLogService.insertByConsume(pointsLog, orderBO.getNowVipLevel());
    }
}
